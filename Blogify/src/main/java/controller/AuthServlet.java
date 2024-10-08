package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.impl.UserServiceImpl;


public class AuthServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AuthServlet.class);
    private  UserServiceImpl userServiceImpl;


    @Override
    public void init() throws ServletException {
      this.userServiceImpl = new UserServiceImpl();

    }



    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {
            login(req, res);
        } else if ("register".equalsIgnoreCase(action)) {
            register(req, res);

        }
            

        
    }


    protected void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
    }


    protected void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
          


        } else if ("register".equals(action)) {
            String email = req.getParameter("email");
            String first_name = req.getParameter("first_name");
            String second_name = req.getParameter("second_name");
            String birth_date = req.getParameter("birth_date");
            String password = req.getParameter("password");

            logger.info("name  "+first_name);
            logger.info("second_name  "+second_name);
            logger.info("birth_date  "+birth_date);
            logger.info("password  "+password);





            if(this.userServiceImpl.userAlreadyExist(email)){
                logger.info("user exist ");
    
            }else{
                    logger.info("user dznt exist ");
    
            }

        } 
       
    }
    
}
