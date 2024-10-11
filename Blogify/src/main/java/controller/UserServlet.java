package controller;

import entity.User;
import enums.UserRole;
import model.UserModel;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class UserServlet extends HttpServlet {
    private UserServiceImpl userService;

    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRole() == null || !loggedInUser.getRole().toString().equals("ADMIN")) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        String action = request.getParameter("action");
        try {
            if (action == null ) {action = "list";}
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "list":
                    listUsers(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertUser(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("views/user/index.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/user/new.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("views/user/edit.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LocalDate birthDate = stringToLocaldate(request.getParameter("birth_date"));
        UserRole role = UserRole.valueOf(request.getParameter("role"));

        userService.createUser(new User(firstName, lastName, email, password, birthDate, role));
        response.sendRedirect("user");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthDate = request.getParameter("birth_date");
        UserRole role = UserRole.valueOf(request.getParameter("role"));
        userService.updateUser(new User(id, firstName, secondName, email, password, stringToLocaldate(birthDate), role));
        response.sendRedirect("user");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        UserModel userModel = userService.deleteUser(id);
        request.setAttribute("userModel", userModel);
        if (userModel.getError() != null) {
            response.sendRedirect("list?error=" + userModel.getError());
        } else if (userModel.getSuccess() != null) {
            response.sendRedirect("list?success=" + userModel.getError());
        }
            response.sendRedirect("list");
//        request.setAttribute("users", userService.getAllUsers());
    }

    // parse string date to localDate
    private LocalDate stringToLocaldate(String stringDate) {
        DateTimeFormatter formatter = null;
        if (stringDate.equals("dd-MMM-yyyy")) {
            formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        } else {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }
        return LocalDate.parse(stringDate, formatter);
    }
}
