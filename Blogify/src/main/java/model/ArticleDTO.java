package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ArticleDTO {
    private Long id;
    private String articlePictureUrl;
    private String content;
    private Date creationDate;
    private Date publishedDateTime;
    private String status;
    private String title;
    private Long userId; 
    private String firstName; 
    private String secondName; 
    private boolean isLiked;


    public ArticleDTO(Long id, String articlePictureUrl, String content,
                      LocalDateTime creationDate, LocalDateTime publishedDateTime,
                      String status, String title, Long userId, String firstName , String secondName) {
        this.id = id;
        this.articlePictureUrl = articlePictureUrl;
        this.content = content;
        this.creationDate = Date.from(creationDate.atZone(ZoneId.systemDefault()).toInstant());
        if(publishedDateTime!=null){
            this.publishedDateTime = Date.from(publishedDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        this.status = status;
        this.title = title;
        this.userId = userId;
        this.firstName = firstName; 
        this.secondName = secondName;
    }

    // Getters and Setters
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getPublishedDateTime() {
        return publishedDateTime;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return this.firstName;
    }

    public void setUserFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserSecondName() {
        return this.secondName;
    }

    public void setUserSecondName(String secondName) {
        this.secondName = secondName;
    }
}