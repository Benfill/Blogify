package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServlet.class);


    @Override
    public void init() throws ServletException {
      
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
            logger.info(req.getParameter("email"));

        } else if ("register".equals(action)) {
            logger.info(req.getParameter("first_name"));

        } 
       
    }
    
}
