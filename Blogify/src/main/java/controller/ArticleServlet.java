package controller;


import java.io.IOException;
import java.util.List;
import java.io.File;

import javax.servlet.ServletException;

import javax.servlet.http.*;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Article;
import entity.User;
import enums.ArticleStatus;
import enums.UserRole;
import model.ArticleModel;
import service.impl.ArticleServiceImpl;

public class ArticleServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";    
    private static final Logger logger = LoggerFactory.getLogger(ArticleServlet.class);
    private  ArticleServiceImpl articleServiceImpl ;


    @Override
    public void init() throws ServletException {
        this.articleServiceImpl = new ArticleServiceImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        

		// this.getServletContext().getRequestDispatcher("/views/article/index.jsp").forward(req, res);

           String action = req.getParameter("action");
            if (action == null || action.isEmpty()) {
                index(req, res);
            } else if ("add".equalsIgnoreCase(action)) {
                create(req, res);
            } else if ("list".equalsIgnoreCase(action)) {
              index(req, res);
            } else if ("edit".equalsIgnoreCase(action)) {
                edit(req,res);
            }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
            if ("create".equals(action)) {
                createP(req, res);
            } else if ("update".equals(action)) {
               update(req,res);
            } else if ("delete".equals(action)) {
                delete(req,res);
            }
       
    }

    // GET REQUEST 
    protected void index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
		// req.setAttribute("model", model);
		this.getServletContext().getRequestDispatcher("/views/article/index.jsp").forward(req, res);

	}

    protected void create(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/views/article/add.jsp").forward(req, res);
	}

    protected void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String memberId = req.getParameter("id");

        
    }

    protected void createP(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // simulate session check 
        HttpSession session = req.getSession(false); 

       session.setAttribute("user_id", 1L);
       session.setAttribute("first_name", "med");





        String title = null;
        String content = null;
        String fileName = null ;
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request
                List<FileItem> formItems = upload.parseRequest(req);

                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {

                        logger.info("req param"+item.getFieldName());
                        if (!item.isFormField()) {
                            fileName = new File(item.getName()).getName();
                            String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + fileName;
                            


                            // Create the upload directory if it doesn't exist
                            // File uploadDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY);
                            // if (!uploadDir.exists()) {
                            //     uploadDir.mkdir();
                            // }

                            // // // Save the file on disk
                            // File storeFile = new File(filePath);
                            // item.write(storeFile);

                            // Inform the user about the successful upload
                            res.getWriter().println("File uploaded successfully: " + fileName);
                        }else{
                            if ("title".equals(item.getFieldName())) {
                                title = item.getString();  
                                logger.info("Title parameter: " + title);
                            }

                            if ("content".equals(item.getFieldName())) {
                                content = item.getString();  
                                logger.info("content parameter: " + title);
                            }

                        }
                    }
                }
                
                Article newArticle = new Article();
                newArticle.setTitle(title);
                newArticle.setContent(content);
                newArticle.setArticlePictureUrl(fileName);
                newArticle.setStatus(ArticleStatus.PUBLISHED.name());

                User user = new User();
                user.setId(1L);
                newArticle.setUserId(user);

                Boolean inserted =this.articleServiceImpl.addNewArticle(newArticle);
                ArticleModel model = new ArticleModel();


                if(inserted){
                    model.setSuccess("Saved");
                    req.setAttribute("model", model);
                }else{
                    model.setError("error occured while inserting the article");
                }

                this.getServletContext().getRequestDispatcher("/views/article/add.jsp").forward(req, res);

                
                
            } catch (Exception ex) {
                res.getWriter().println("File upload failed: " + ex.getMessage());
            }
        } else {
            res.getWriter().println("Request is not multipart");
        }
    }
    protected void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       
    }

    protected void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
     
    }
}
