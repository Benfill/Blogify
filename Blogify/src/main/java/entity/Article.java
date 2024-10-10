package entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;

import java.util.*;

import enums.ArticleStatus;


@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @NotEmpty(message = "Title is required.")
    @Size(max = 255, message = "Title cannot exceed 255 characters.")
    @Column(name = "title")
    private String title;


    @NotEmpty(message = "Content is required.")
    @Size(max = 1000, message = "Content cannot exceed 1000 characters.")
    @Column(name = "content")
    private String content;

    @Column(name = "articlePictureUrl")
    private String articlePictureUrl;

    @Column(name = "creationDate", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

    @Column(name = "publishedDateTime")
    private LocalDateTime publishedDateTime;

    @Column(name = "status")
    private String status;

   

    // @Column(name = "user_id")
    // private Long userId;
    @ManyToOne
	private User user;


    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comment> comments;

    public Article() {
        this.creationDate = LocalDateTime.now();
    }

    public Article(String articlePictureUrl, String content, LocalDateTime creationDate, LocalDateTime publishedDateTime, String status, String title, User user) {
        this.articlePictureUrl = articlePictureUrl;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.publishedDateTime = publishedDateTime;
        this.status = status;
        this.title = title;
        this.user = user;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticlePictureUrl() {
        return articlePictureUrl;
    }

    public void setArticlePictureUrl(String articlePictureUrl) {
        this.articlePictureUrl = articlePictureUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(LocalDateTime publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUserId() {
        return this.user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public List<Comment> getComments(){
        return this.comments;
    }
}
