package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.impl.CommentServiceImpl;

public class CommentServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private CommentServiceImpl commentService;

	@Override
	public void init() throws ServletException {
		commentService = new CommentServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getPathInfo();
		if (action != null)
			action = action.substring(1, action.length());
		resp.getWriter().println(action);

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
		default:
			break;
		}

	}

	private void store(HttpServletRequest req, HttpServletResponse resp) {

		String content = req.getParameter("comment_content");
		String articleId = req.getParameter("article_id");

		HttpSession session = req.getSession();
		int userId = (int) session.getAttribute("user_id");

		commentService.post(content, articleId, userId);

	}

}
