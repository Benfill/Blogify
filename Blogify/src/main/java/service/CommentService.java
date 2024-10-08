package service;

import java.util.List;

import entity.Comment;

public interface CommentService {

	List<Comment> getAll();

	void post(String content, String articlId, int userId);

	void update(Comment comment);

	void delete(Comment comment);
}
