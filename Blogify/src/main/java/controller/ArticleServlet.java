package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Article;
import entity.User;
import enums.ArticleStatus;
import enums.CommentStatus;
import model.ArticleDTO;
import model.ArticleModel;
import repository.impl.ArticleRepositoryImpl;
import service.impl.ArticleServiceImpl;
import service.impl.UserServiceImpl;

public class ArticleServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";    
    private static final Logger logger = LoggerFactory.getLogger(ArticleServlet.class);
    private  ArticleServiceImpl articleServiceImpl ;
    private Validator validator;

    @Override
    public void init() throws ServletException {

        ArticleRepositoryImpl articleRepo = new ArticleRepositoryImpl();
        articleServiceImpl = new ArticleServiceImpl(articleRepo);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

  
    	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {
          index(req, res);
        } else if ("admin".equalsIgnoreCase(action)) {
          admin(req, res);
        } else if ("add".equalsIgnoreCase(action)) {
          create(req, res);
        } else if ("list".equalsIgnoreCase(action)) {
          index(req, res);
        } else if ("edit".equalsIgnoreCase(action)) {
          edit(req, res);
        } else if ("detail".equalsIgnoreCase(action)) {

          detail(req, res);
        }
	  }


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("create".equals(action)) {
			createP(req, res);
		} else if ("update".equals(action)) {
			update(req, res);
		} else if ("delete".equals(action)) {
			delete(req, res);
		}else if("like".equals(action)){
			String id = req.getParameter("article_id");
			this.articleServiceImpl.like(Long.parseLong(id));
			res.sendRedirect(req.getContextPath()+"/article?action=detail&id="+Long.parseLong(id));

		}

	}

    

	// GET REQUEST
	protected void index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pageString = req.getParameter("page");
		int page = 1;

		if (pageString != null && pageString.matches("-?\\d+(\\.\\d+)?"))
			page = Integer.parseInt(pageString);

		List<ArticleDTO> articles = this.articleServiceImpl.getAllArticles(page,ArticleStatus.PUBLISHED.toString());

		long totalProjects = this.articleServiceImpl.count();
		int pageSize = 5;
		long pageNumbers = (totalProjects + pageSize - 1) / pageSize;

		ArticleModel model = new ArticleModel();
		model.setArticles(articles);
		model.setTotalProjects(totalProjects);
		model.setPageNumbers(pageNumbers);
		model.setPageSize(pageSize);
		model.setPage(page);

		req.setAttribute("model", model);

		this.getServletContext().getRequestDispatcher("/views/article/index.jsp").forward(req, res);

	}

	protected void admin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		logger.info("userf role" + loggedInUser.getRole());
	

		String pageString = req.getParameter("page");
		String pageFilter = req.getParameter("filter");

		List<String> allowedFilters = Arrays.asList("DRAFT", "PUBLISHED", "ALL");

   		 // If the provided filter is not in the list, throw an exception or handle accordingly
		if (pageFilter == null || !allowedFilters.contains(pageFilter.toUpperCase())) {
			throw new IllegalArgumentException("Invalid filter parameter");
		}


		int page = 1;

		if (pageString != null && pageString.matches("-?\\d+(\\.\\d+)?"))
			page = Integer.parseInt(pageString);

		List<ArticleDTO> articles = this.articleServiceImpl.getAllArticles(page ,pageFilter);

		long totalProjects = this.articleServiceImpl.count();
		int pageSize = 5;
		long pageNumbers = (totalProjects + pageSize - 1) / pageSize;

		ArticleModel model = new ArticleModel();
		model.setArticles(articles);
		model.setTotalProjects(totalProjects);
		model.setPageNumbers(pageNumbers);
		model.setPageSize(pageSize);
		model.setPage(page);
		model.setFIlter(pageFilter);

		req.setAttribute("model", model);

		this.getServletContext().getRequestDispatcher("/views/article/admin.jsp").forward(req, res);

	}

	protected void create(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null || loggedInUser.getRole() == null
				|| !loggedInUser.getRole().toString().equals("ADMIN")) {
			res.sendRedirect(req.getContextPath());
			return;
		}

		this.getServletContext().getRequestDispatcher("/views/article/add.jsp").forward(req, res);
	}

	protected void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String articleId = req.getParameter("id");
		HttpSession session = req.getSession();

		ArticleModel model = new ArticleModel();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null || loggedInUser.getRole() == null) {
			res.sendRedirect(req.getContextPath());
			return;
		}
		if (articleId != null && !articleId.isEmpty()) {
			Long id = Long.parseLong(articleId);
			Article article = this.articleServiceImpl.findArticleById(id);
			if (article != null) {
				model.setArticle(article);

				req.setAttribute("model", model);
				this.getServletContext().getRequestDispatcher("/views/article/edit.jsp").forward(req, res);
			} else {
				res.sendRedirect("/article?action=list");
			}

		} else {
			logger.error("ID parameter is missing");
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Article ID is required");
		}

	}

	protected void detail(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");

		if (id != null) {
			Long articleId = Long.parseLong(id);
			ArticleModel article = this.articleServiceImpl.getArticleById(articleId);
			if (article != null) {
				req.setAttribute("article", article.getArticle());
				req.setAttribute("user", article.getUser());
				req.setAttribute("comments", article.getComments().stream()
						.filter(c -> c.getCommentStatus().equals(CommentStatus.APPROVED)).collect(Collectors.toList()));

				if (article.getComments() != null)
					req.setAttribute("commentCount", article.getComments().stream().count());
				else
					req.setAttribute("commentCount", 0);

				this.getServletContext().getRequestDispatcher("/views/article/detail.jsp").forward(req, res);
			} else {
				res.sendRedirect("/article?action=list");
			}
		}

	}

	protected void createP(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null || loggedInUser.getRole() == null
				|| !loggedInUser.getRole().toString().equals("ADMIN")) {
			res.sendRedirect(req.getContextPath());
			return;
		}

		String title = null;
		String content = null;
		String fileName = null;
		String status = null;
		String filePath = null;

		ArticleModel model = new ArticleModel();
		Article newArticle = new Article();

		// Check if the request is multipart
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> formItems = upload.parseRequest(req);

				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							fileName = new File(item.getName()).getName();
							filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY
									+ File.separator + fileName;

							File uploadDir = new File(
									getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY);
							if (!uploadDir.exists()) {
								uploadDir.mkdir();
							}

							File storeFile = new File(filePath);
							item.write(storeFile);

						} else {
							if ("title".equals(item.getFieldName())) {
								title = item.getString();
							} else if ("content".equals(item.getFieldName())) {
								content = item.getString();
							} else if ("status".equals(item.getFieldName())) {
								status = item.getString();
							}
						}
					}
				}

				// Set article fields
				newArticle.setTitle(title);
				newArticle.setContent(content);
				newArticle.setArticlePictureUrl(fileName);
				if ("PUBLISHED".equals(status)) {
					newArticle.setStatus(ArticleStatus.PUBLISHED.name());
					newArticle.setPublishedDateTime(LocalDateTime.now());
				} else {
					newArticle.setStatus(ArticleStatus.DRAFT.name());
				}

				// Validate the article
				Set<ConstraintViolation<Article>> violations = validator.validate(newArticle);
				if (!violations.isEmpty()) {
					for (ConstraintViolation<Article> violation : violations) {
						String propertyName = violation.getPropertyPath().toString();
						String errorMessage = violation.getMessage();
						model.getErrors().put(propertyName, errorMessage);
					}

					req.setAttribute("model", model);
					req.getRequestDispatcher("/views/article/add.jsp").forward(req, res);
					return;
				}

				newArticle.setUserId(loggedInUser);

				// Insert the article
				Boolean inserted = this.articleServiceImpl.addNewArticle(newArticle);
				if (inserted) {
					model.setSuccess("Saved");
					req.setAttribute("model", model);
				} else {
					model.setError("Error occurred while inserting the article");
					req.setAttribute("model", model);
				}

				// Forward to the add page after processing

			} catch (Exception ex) {
				logger.error("File upload failed", ex);
				model.setError("Upload file");
				req.setAttribute("model", model);

				// this.getServletContext().getRequestDispatcher("/views/article/add.jsp").forward(req,
				// res);

			}
		} else {
			logger.error("Request is not multipart");
			// this.getServletContext().getRequestDispatcher("/views/article/add.jsp").forward(req,
			// res);
		}

		this.getServletContext().getRequestDispatcher("/views/article/add.jsp").forward(req, res);

	}

	protected void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");

		if (id != null) {
			boolean deleted = this.articleServiceImpl.deleteArticle(Long.parseLong(id));
			if (deleted) {
				res.sendRedirect("article?action=admin&success=Article deleted  ");

			} else {
				res.sendRedirect("article?action=admin&error=Article not deleted  ");
			}

		}

	}

	protected void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String status = req.getParameter("status");

		ArticleModel model = new ArticleModel();
		Article newArticle = new Article();
		Map<String, String> errors = new HashMap<>();

		logger.info("title: " + title);
		logger.info("content: " + content);
		logger.info("status: " + status);
		logger.info("id: " + id);

		try {
			newArticle.setId(Long.parseLong(id));
			newArticle.setTitle(title);
			newArticle.setContent(content);
			if ("PUBLISHED".equals(status)) {
				newArticle.setStatus(ArticleStatus.PUBLISHED.name());
				newArticle.setPublishedDateTime(LocalDateTime.now());
			} else {
				newArticle.setStatus(ArticleStatus.DRAFT.name());
			}

			Set<ConstraintViolation<Article>> violations = validator.validate(newArticle);

			// Check for violations and populate the model's error map
			if (!violations.isEmpty()) {
				for (ConstraintViolation<Article> violation : violations) {
					String propertyName = violation.getPropertyPath().toString();
					String errorMessage = violation.getMessage();
					model.getErrors().put(propertyName, errorMessage);
				}

				req.setAttribute("model", model);
				req.getRequestDispatcher("/views/article/edit.jsp?id=" + id).forward(req, res);
				return;
			}

			if (this.articleServiceImpl.updateArticle(newArticle)) {
				model.setSuccess("Article updated successfully");
			} else {
				model.setError("Cant update article");

			}
			req.setAttribute("model", model);
			req.getRequestDispatcher("/views/article/edit.jsp?id=" + id).forward(req, res);

		} catch (NumberFormatException e) {
			model.getErrors().put("id", "Invalid article ID.");
			req.setAttribute("model", model);
			req.getRequestDispatcher("/views/article/edit.jsp?id=" + id).forward(req, res);
		} catch (Exception e) {
			logger.error("An error occurred while updating the article: ", e);
			model.setError("An unexpected error occurred. Please try again.");
			req.setAttribute("model", model);
			req.getRequestDispatcher("/views/article/edit.jsp?id=" + id).forward(req, res);
		}

	}
}