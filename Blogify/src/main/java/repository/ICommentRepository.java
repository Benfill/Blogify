package repository;

import java.util.List;
import java.util.Optional;

import entity.Comment;

public interface ICommentRepository {

	List<Comment> readAll(int offset);

	void insert(Comment comment) throws Exception;

	void update(Comment comment);

	void delete(Comment comment);

	void changeStatus(int commentId, String newStatus) throws Exception;

	Optional<Comment> readById(int id);

}
