package service;

import java.util.List;

import entity.Comment;

public interface ICommentService {

	List<Comment> getAll(int page, String filter);

	void post(String content, String articlId, int userId) throws Exception;

	void update(Comment comment);

	void delete(Comment comment);

}
