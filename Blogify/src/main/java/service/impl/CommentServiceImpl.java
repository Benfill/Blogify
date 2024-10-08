package service.impl;

import java.time.LocalDate;
import java.util.List;

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
	public void post(String content, String articlId, int userId) {
		User user = new UserServiceImpl().getUserById(userId);
		Comment comment = new Comment(content, LocalDate.now());

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
