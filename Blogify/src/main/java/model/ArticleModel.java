package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Article;
import entity.Comment;
import entity.User;

public class ArticleModel {
    private User connectedUser;
    private String error;
    private Map<String, String> errors = new HashMap<>();
    private String success;
    private List<ArticleDTO> articles;
    private Article article;
    private String successMessage;
    private String errorMessage;

    private Long totalProjects;
    private int page ;
    private long pageNumbers;
    private int pageSize;
    private String filter;


    // Corrected Getter and Setter for errorMessage
    public String getErrorMessage(){
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    // Corrected Getter and Setter for successMessage
    public String getSuccessMessage(){
        return this.successMessage;
    }

    public void setSuccessMessage(String successMessage){
        this.successMessage = successMessage;
    }

    // Corrected Getter and Setter for articles
    public List<ArticleDTO> getArticles(){
        return this.articles;
    }

    public void setArticles(List<ArticleDTO> articles){
        this.articles = articles;
    }

    // Getter and Setter for single article
    public Article getArticle(){
        return this.article;
    }

    public void setArticle(Article article){
        this.article = article;
    }

    // Getters and Setters for error and success
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    // Getters and Setters for errors map
    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    // Corrected getUser method
    public User getUser(){
        return this.article.getUserId();
    }

    // Getter for comments
    public List<Comment> getComments(){
        return this.article.getComments();
    }

    // Getter and Setter for connectedUser if needed
    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public Long getTotalProjects() {
        return totalProjects;
    }
    
    public void setTotalProjects(Long totalProjects) {
        this.totalProjects = totalProjects;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public long getPageNumbers() {
        return pageNumbers;
    }
    
    public void setPageNumbers(long pageNumbers) {
        this.pageNumbers = pageNumbers;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setFIlter(String filter){
        this.filter = filter;
    }

    public String getFilter(){
        return this.filter;
    }
}
