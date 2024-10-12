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
import entity.User;
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

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("loggedInUser");

		if (user == null || user.getRole() == null || !user.getRole().toString().equals("ADMIN")) {
			resp.sendRedirect(req.getContextPath());
			return;
		}

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
			else if (action.equals("pending")) {
				filter = "pending";
			}

		}

		List<Comment> comments = commentService.getAll(page, filter);

		long total = commentService.getCommentSize();
		int totalPages = (int) Math.ceil((double) total / 5);

		req.setAttribute("comments", comments);
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("totalPages", totalPages);

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
			update(req, resp);
			break;
		case "delete":
			delete(req, resp);
			break;
		case "status/update":
			updateStatus(req, resp);
			break;
		}

	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("comment_id");
		String content = req.getParameter("content");
		String articleId = req.getParameter("article_id");
		String userId = req.getParameter("user_id");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loggedInUser");

		if (articleId == null || (!articleId.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(articleId) > 0)) {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			return;
		}

		if (userId == null || (!userId.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(userId) > 0)) {
			resp.sendRedirect(req.getContextPath() + "/article?action=detail&id=" + articleId);
			return;
		}

		if (user == null || user.getId() == null || user.getId() != Long.parseLong(userId)) {
			resp.sendRedirect(req.getContextPath() + "/article?action=detail&id=" + articleId);
			return;
		}

		try {
			commentService.update(idParam, content);
			req.setAttribute("successMessage", "Comment updated successfully");

		} catch (Exception e) {
			req.setAttribute("errorMessage", "error: " + e.getMessage());
		}

		if (articleId == null || (!idParam.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(idParam) > 0)) {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			return;
		}

		resp.sendRedirect(req.getContextPath() + "/article?action=detail&id=" + articleId);

	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("comment_id");
		String articleId = req.getParameter("article_id");
		String userId = req.getParameter("user_id");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loggedInUser");

		if (articleId == null || (!articleId.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(articleId) > 0)) {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			return;
		}

		if (userId == null || (!userId.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(userId) > 0)) {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			return;
		}

		if (user == null || user.getId() == null || user.getId() != Long.parseLong(userId)) {
			resp.sendRedirect(req.getContextPath());
			return;
		}

		try {
			commentService.delete(idParam);
			req.setAttribute("successMessage", "Comment deleted successfully");

		} catch (Exception e) {
			req.setAttribute("errorMessage", "error: " + e.getMessage());
		}

		resp.sendRedirect(req.getContextPath() + "/article?action=detail&id=" + articleId);

	}

	private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("comment_id");
		String status = req.getParameter("status");

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null || user.getRole() == null || !user.getRole().toString().equals("ADMIN")) {
			resp.sendRedirect(req.getContextPath());
			return;
		}

		if (!user.getRole().toString().equals("ADMIN")) {
			resp.sendRedirect(req.getContextPath());
			return;
		}

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
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			System.err.println("User not logged in");
			resp.sendRedirect(req.getContextPath() + "/auth");
			return;
		}

		try {
			commentService.post(content, articleId, Integer.parseInt(user.getId().toString()));
			req.setAttribute("successMessage", "Comment posted successfully please wait for the admin approval");
		} catch (Exception e) {
			e.printStackTrace(); // Add this line to print the full stack trace
			System.err.println("Error posting comment: " + e.getMessage()); // Add this line for debugging
			req.setAttribute("errorMessage", "Failed to post comment: " + e.getMessage());
			log.warning("error: " + e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			return;
		}

		resp.sendRedirect(req.getContextPath() + "/article?action=detail&id=" + articleId);
	}

}
