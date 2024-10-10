package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.User;
import enums.UserRole;
import model.UserModel;
import service.impl.AuthServiceImpl;
import service.impl.UserServiceImpl;
import utils.PasswordUtil;

public class AuthServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(AuthServlet.class);
	private UserServiceImpl userServiceImpl;
	private AuthServiceImpl authServiceImpl;

	@Override
	public void init() throws ServletException {
		this.userServiceImpl = new UserServiceImpl();
		this.authServiceImpl = new AuthServiceImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String action = req.getParameter("action");
		if (session == null || session.getAttribute("loggedInUser") == null) {
			if (action == null || action.isEmpty()) {
				this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
			} else if ("register".equalsIgnoreCase(action)) {
				this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
			} else {
				this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
			}
		} else {
			res.sendRedirect(req.getContextPath() + "/views/article/index.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("login".equals(action)) {
			login(req, res);
		} else if ("register".equals(action)) {
			register(req, res);
		} else if ("logout".equals(action)) {
			logout(req, res);
		}
	}

	protected void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String first_name = req.getParameter("first_name");
		String second_name = req.getParameter("second_name");
		String birth_dateStr = req.getParameter("birth_date").trim(); // Ensure no extra spaces
		String password = PasswordUtil.hashPassword(req.getParameter("password"));
		String role = req.getParameter("role");

		UserModel model = new UserModel();

		if (this.userServiceImpl.userAlreadyExist(email)) {
			model.setError("Email already exists.");
			req.setAttribute("model", model);
			this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
		} else {
			if (role == null)
				role = "CONTRIBUTOR";

			logger.info("name  " + first_name);
			logger.info("second_name  " + second_name);
			logger.info("birth_date  " + birth_dateStr);
			logger.info("password  " + password);
			logger.info("role  " + role);

			LocalDate birth_date = null;
			// Force the date format to English
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

			try {
				birth_date = LocalDate.parse(birth_dateStr, dateFormat);
			} catch (DateTimeParseException e) {
				model.setError("Invalid date format. Please use dd-MMM-yyyy.");
				req.setAttribute("model", model);
				this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
				return; // Stop processing if the date is invalid
			}

			User newUser = new User();
			newUser.setFirstName(first_name);
			newUser.setSecond_name(second_name);
			newUser.setEmail(email);
			newUser.setBirthDate(birth_date);
			newUser.setPassword(password);
			newUser.setRole(UserRole.valueOf(role.toUpperCase()));

			User user = this.authServiceImpl.register(newUser);

			model.setUser(user);
			if (user != null) {
				req.setAttribute("model", model);
				this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
			} else {
				model.setError("An error occurred while registering, please try again.");
				req.setAttribute("model", model);
				this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
			}
		}
	}

	protected void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String plainPassword = req.getParameter("password");

		UserModel userModel = new UserModel();
		userModel.setError(email);

		if (this.userServiceImpl.userAlreadyExist(email)) {
			User user = userServiceImpl.getUserByEmail(email);
			String hashedPassword = user.getPassword();

			if (PasswordUtil.verifyPassword(hashedPassword, plainPassword)) {
				HttpSession session = req.getSession();
				session.setAttribute("loggedInUser", user);

				res.sendRedirect(req.getContextPath() + "/views/article/index.jsp");
			} else {
				userModel.setError("Invalid password");
				req.setAttribute("model", userModel);
				this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
			}
		} else {
			userModel.setError("User not found, with email:" + email);
			req.setAttribute("model", userModel);
			this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
		}
	}

	protected void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		res.sendRedirect(req.getContextPath() + "/views/auth/login.jsp"); // Redirect to login page
	}

}
