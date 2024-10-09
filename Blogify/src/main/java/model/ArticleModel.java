package model;

import java.util.List;

import entity.Article;
import entity.User;

public class ArticleModel {
    private User connectedUser;
    private String error;
    private String success;
    private List<Article> articles;


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


    public List<Article> allArticles(){
        return this.articles;
    }

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }
}
