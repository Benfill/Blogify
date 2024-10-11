package service;

import java.util.List;

import entity.Comment;

public interface ICommentService {

	List<Comment> getAll(int page, String filter);

	void post(String content, String articlId, int userId) throws Exception;

	void update(String id, String content) throws Exception;

	void delete(String id) throws Exception;

	void approveOrDenieComment(String id, String status) throws Exception;

}
