package service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import entity.Comment;
import entity.User;
import model.ArticleModel;
import repository.impl.CommentRepositoryImpl;
import service.ICommentService;

public class CommentServiceImpl implements ICommentService {

	private static final Logger log = Logger.getLogger(CommentServiceImpl.class.getName());
	private CommentRepositoryImpl commentRepo;

	@Override
	public List<Comment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void post(String content, String articleId, int userId) throws Exception {
		if (content == null || content.trim().isEmpty() || articleId == null || userId <= 0)
			throw new Exception("Invalid input parameters");

        User user = new UserServiceImpl().getUserById((long) userId);
		ArticleModel article = new ArticleServiceImpl().getArticleById(articleId);
		Comment comment = new Comment(content, LocalDate.now(), article.getArticle(), user);

		System.out.println("Attempting to insert comment: " + comment);
		commentRepo.insert(comment);

	}

	@Override
	public void update(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub

	}

}
