package repository;

import java.util.List;

import entity.Comment;

public interface CommentRepository {

	List<Comment> readAll();

	void insert(Comment comment) throws Exception;

	void update(Comment comment);

	void delete(Comment comment);

	void changeStatus(Comment comment);

}
