package entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import enums.CommentStatus;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	private int id;
	private String content;
	private LocalDate creationDate;
	private CommentStatus commentStatus;

	// @ManyToOne
	// private Article article;
	// private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	// public Article getArticle() {
	// 	return article;
	// }

	// public void setArticle(Article article) {
	// 	this.article = article;
	// }

	// public User getUser() {
	// 	return user;
	// }

	// public void setUser(User user) {
	// 	this.user = user;
	// }

	public CommentStatus getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(CommentStatus commentStatus) {
		this.commentStatus = commentStatus;
	}
}
