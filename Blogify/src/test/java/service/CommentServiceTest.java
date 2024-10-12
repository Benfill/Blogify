package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entity.Comment;
import enums.CommentStatus;
import repository.impl.CommentRepositoryImpl;
import service.impl.ArticleServiceImpl;
import service.impl.CommentServiceImpl;
import service.impl.UserServiceImpl;

class CommentServiceTest {

	@Mock
	private CommentRepositoryImpl commentRepo;

	@Mock
	private UserServiceImpl userService;

	@Mock
	private ArticleServiceImpl articleService;

	@InjectMocks
	private CommentServiceImpl commentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		commentService = new CommentServiceImpl();
		commentService.setCommentRepo(commentRepo);
	}

	@Test
	void testGetAll_NoFilter() {
		List<Comment> mockComments = Arrays.asList(new Comment("Test comment 1", LocalDate.now(), null, null),
				new Comment("Test comment 2", LocalDate.now(), null, null));
		when(commentRepo.readAll(0)).thenReturn(mockComments);

		List<Comment> result = commentService.getAll(1, "all");

		assertEquals(2, result.size());
		verify(commentRepo).readAll(0);
	}

	@Test
	void testGetAll_WithFilter() {
		Comment approvedComment = new Comment("Approved", LocalDate.now(), null, null);
		approvedComment.setCommentStatus(CommentStatus.APPROVED);
		Comment pendingComment = new Comment("Pending", LocalDate.now(), null, null);
		pendingComment.setCommentStatus(CommentStatus.PENDING);

		List<Comment> mockComments = Arrays.asList(approvedComment, pendingComment);
		when(commentRepo.readAll(0)).thenReturn(mockComments);

		List<Comment> result = commentService.getAll(1, "approved");

		assertEquals(1, result.size());
		assertEquals(CommentStatus.APPROVED, result.get(0).getCommentStatus());
	}

	@Test
	void testPost_InvalidInput() {
		assertThrows(Exception.class, () -> commentService.post("", "1", 1));
		assertThrows(Exception.class, () -> commentService.post("Test", null, 1));
		assertThrows(Exception.class, () -> commentService.post("Test", "1", 0));
	}

	@Test
	void testUpdate_ValidInput() throws Exception {
		Comment mockComment = new Comment();
		when(commentRepo.readById(1)).thenReturn(Optional.of(mockComment));

		commentService.update("1", "Updated content");

		verify(commentRepo).update(mockComment, "Updated content");
	}

	@Test
	void testUpdate_InvalidInput() {
		assertThrows(Exception.class, () -> commentService.update(null, "content"));
		assertThrows(Exception.class, () -> commentService.update("1", ""));
		assertThrows(Exception.class, () -> commentService.update("1", null));
	}

	@Test
	void testDelete_ValidInput() throws Exception {
		Comment mockComment = new Comment();
		when(commentRepo.readById(1)).thenReturn(Optional.of(mockComment));

		commentService.delete("1");

		verify(commentRepo).delete(mockComment);
	}

	@Test
	void testDelete_InvalidInput() {
		assertThrows(Exception.class, () -> commentService.delete(null));
		assertThrows(Exception.class, () -> commentService.delete("invalid"));
	}

	@Test
	void testApproveOrDenieComment_InvalidInput() {
		assertThrows(Exception.class, () -> commentService.approveOrDenieComment(null, "approved"));
		assertThrows(Exception.class, () -> commentService.approveOrDenieComment("1", null));
	}

	@Test
	void testGetCommentSize() {
		when(commentRepo.getCount()).thenReturn(10L);

		long result = commentService.getCommentSize();

		assertEquals(10L, result);
		verify(commentRepo).getCount();
	}
}