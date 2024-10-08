package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Comment;

public class CommentServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

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
		Comment comment = new Comment();

		comment.setContent(content);

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			// Begin the transaction
			transaction = session.beginTransaction();

			// Save the entity
			session.save(comment);

			// Commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Roll back the transaction if it failed
			}
			e.printStackTrace(); // Log the error or handle it appropriately
		} finally {
			session.close(); // Ensure the session is closed
		}
	}

}
