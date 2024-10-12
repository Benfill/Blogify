package service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import entity.Comment;
import entity.User;
import model.ArticleModel;
import repository.impl.CommentRepositoryImpl;
import service.ICommentService;

public class CommentServiceImpl implements ICommentService {

	private static final Logger log = Logger.getLogger(CommentServiceImpl.class.getName());
	private CommentRepositoryImpl commentRepo = new CommentRepositoryImpl();

	@Override
	public List<Comment> getAll(int page, String filter) {

		int offset = (page - 1) * 5;

		List<Comment> comments = getCommentRepo().readAll(offset);

		if (comments == null) {
			return new ArrayList<>(); // Return an empty list instead of null
		}

		if (filter != null && !filter.equals("all")) {
			return comments.stream().filter(
					c -> c.getCommentStatus() != null && c.getCommentStatus().toString().equalsIgnoreCase(filter))
					.collect(Collectors.toList());
		}

		return comments;
	}

	@Override
	public void post(String content, String articleId, int userId) throws Exception {
		if (content == null || content.trim().isEmpty() || articleId == null || userId <= 0)
			throw new Exception("Invalid input parameters");

		User user = new UserServiceImpl().getUserById((long) userId);
		ArticleModel article = new ArticleServiceImpl().getArticleById(Long.parseLong(articleId));
		Comment comment = new Comment(content, LocalDate.now(), article.getArticle(), user);

		System.out.println("Attempting to insert comment: " + comment);
		getCommentRepo().insert(comment);

	}

	@Override
	public void update(String idParam, String content) throws Exception {
		if (idParam == null || (!idParam.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(idParam) > 0))
			throw new Exception("The id Param is null or not a valid number");
		if (content == null || content.isEmpty())
			throw new Exception("The status Param is null or empty");

		int id = Integer.parseInt(idParam);

		Optional<Comment> cOptional = getCommentRepo().readById(id);

		if (!cOptional.isPresent())
			throw new Exception("the comment not found");

		Comment comment = cOptional.get();

		getCommentRepo().update(comment, content);
	}

	@Override
	public void delete(String idParam) throws Exception {
		if (idParam == null || (!idParam.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(idParam) > 0))
			throw new Exception("The id Param is null or not a valid number");

		int id = Integer.parseInt(idParam);

		Optional<Comment> cOptional = getCommentRepo().readById(id);

		if (!cOptional.isPresent())
			throw new Exception("the comment not found");

		Comment comment = cOptional.get();

		getCommentRepo().delete(comment);

	}

	@Override
	public void approveOrDenieComment(String idParam, String status) throws Exception {
		int id;
		if (idParam == null || (!idParam.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(idParam) > 0))
			throw new Exception("The id Param is null or not a valid number");

		if (status == null)
			throw new Exception("The status Param is null");

		id = Integer.parseInt(idParam);
		Optional<Comment> cOptional = getCommentRepo().readById(id);

		if (!cOptional.isPresent())
			throw new Exception("Comment not found");

		Comment comment = cOptional.get();
		getCommentRepo().changeStatus(comment.getId(), status);
	}

	@Override
	public long getCommentSize() {
		return getCommentRepo().getCount();
	}

	public CommentRepositoryImpl getCommentRepo() {
		return commentRepo;
	}

	public void setCommentRepo(CommentRepositoryImpl commentRepo) {
		this.commentRepo = commentRepo;
	}

}
