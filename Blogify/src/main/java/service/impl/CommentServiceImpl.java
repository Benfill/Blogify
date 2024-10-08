package service.impl;

import java.time.LocalDate;
import java.util.List;

import entity.Article;
import entity.Comment;
import entity.User;
import service.CommentService;

public class CommentServiceImpl implements CommentService {

	@Override
	public List<Comment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void post(String content, String articlId, int userId) throws Exception {
		if (content == null || articlId == null || userId == 0)
			throw new Exception("Something went wrong");

		User user = new UserServiceImpl().getUserById(userId);
		Article article = new ArticleServiceImpl().getArticleById(Integer.parseInt(articlId));
		Comment comment = new Comment(content, LocalDate.now(), article, user);

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
