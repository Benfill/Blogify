package repository;

import java.util.List;
import java.util.Optional;

import entity.Comment;

public interface ICommentRepository {

	List<Comment> readAll(int offset);

	void insert(Comment comment) throws Exception;

	void update(Comment comment, String content) throws Exception;

	void delete(Comment comment) throws Exception;

	void changeStatus(int commentId, String newStatus) throws Exception;

	Optional<Comment> readById(int id);

	long getCount();

}
