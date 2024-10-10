package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import entity.User;
import model.UserModel;
import enums.UserRole;
import service.impl.AuthServiceImpl;
import service.impl.UserServiceImpl;
import utils.PasswordUtil;


public class AuthServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AuthServlet.class);
    private  UserServiceImpl userServiceImpl;
    private AuthServiceImpl authServiceImpl;


    @Override
    public void init() throws ServletException {
      this.userServiceImpl = new UserServiceImpl();
      this.authServiceImpl = new AuthServiceImpl();

    }



    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {
            this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
        } else if ("register".equalsIgnoreCase(action)) {
            this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
        }
            

        
    }


  


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
          login(req,res);


        } else if ("register".equals(action)) {
            register(req,res);
        } 
       
    }


    protected void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String first_name = req.getParameter("first_name");
        String second_name = req.getParameter("second_name");
        String birth_dateStr = req.getParameter("birth_date");
        String password = PasswordUtil.hashPassword(req.getParameter("password"));
        String role = req.getParameter("role");

        UserModel model = new UserModel();


        if(this.userServiceImpl.userAlreadyExist(email)){
            model.setError("Email already exist ");
            req.setAttribute("model", model);
            this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);


        }else{
            if(role==null)
            role="CONTRIBUTOR";


            logger.info("name  "+first_name);
            logger.info("second_name  "+second_name);
            logger.info("birth_date  "+birth_dateStr);
            logger.info("password  "+password);
            logger.info("role  "+role);


            Date birth_date = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy"); 
                birth_date = dateFormat.parse(birth_dateStr);
            } catch (ParseException e) {
                logger.error("Cant cast the birth date" ,e);

            }

            User newUser = new User();

            newUser.setFirst_name(first_name);
            newUser.setSecond_name(second_name);
            newUser.setEmail(email);
            newUser.setBirth_date(birth_date);
            newUser.setPassword(password);
            newUser.setRole(UserRole.valueOf(role.toUpperCase()));

            User user = this.authServiceImpl.register(newUser);

            model.setUser(user);
            if(user != null){

                req.setAttribute("model", model);
                this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);

            }else{
                model.setError("An error occured while registring , try again ! ");
                req.setAttribute("model", model);
                this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
            }

            

        }

      
    }
    

    protected void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    }
}
