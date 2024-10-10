package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Comment;
import service.impl.CommentServiceImpl;

public class CommentServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CommentServlet.class.getName());
	private CommentServiceImpl commentService;

	@Override
	public void init() throws ServletException {
		commentService = new CommentServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getPathInfo();
		String filter = "all";
		String pageParam = req.getParameter("page");
		int page = 1;

		if (pageParam != null && (pageParam.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(pageParam) > 0))
			page = Integer.parseInt(pageParam);

		if (action != null) {
			action = action.substring(1, action.length());
			if (action.equals("approved"))
				filter = "approved";
			else if (action.equals("denied"))
				filter = "denied";

		}

		List<Comment> comments = commentService.getAll(page, filter);

		req.setAttribute("comments", comments);

		req.getRequestDispatcher("/views/comment/index.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getPathInfo();
		if (action != null)
			action = action.substring(1, action.length());

		switch (action) {
		case "store":
			store(req, resp);
			break;
		case "update":
			break;
		case "delete":
			break;
		case "status/update":
			updateStatus(req, resp);
			break;
		}

	}

	private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("comment_id");
		String status = req.getParameter("status");

		try {
			commentService.approveOrDenieComment(idParam, status);
			req.setAttribute("successMessage", "Comment Status Changed Successfully");
		} catch (Exception e) {
			log.warning("error: " + e.getMessage());
			req.setAttribute("errorMessage", "error: " + e.getMessage());
		}

		resp.sendRedirect(req.getContextPath() + "/comment");

	}

	private void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content = req.getParameter("comment_content");
		String articleId = req.getParameter("article_id");

		HttpSession session = req.getSession();
		Integer userId = (Integer) session.getAttribute("user_id");

		System.out.println(
				"Received request - Content: " + content + ", ArticleId: " + articleId + ", UserId: " + userId);

		if (userId == null) {
			System.err.println("User not logged in");
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		try {
			commentService.post(content, articleId, userId);
			System.out.println("Comment posted successfully"); // Add this line for debugging
		} catch (Exception e) {
			e.printStackTrace(); // Add this line to print the full stack trace
			System.err.println("Error posting comment: " + e.getMessage()); // Add this line for debugging
			req.setAttribute("errorMessage", "Failed to post comment: " + e.getMessage());
			log.warning("error: " + e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			return;
		}

		resp.sendRedirect(req.getContextPath() + "/article/" + articleId);
	}

}
