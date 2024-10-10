package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Article;
import entity.User;

public class ArticleModel {
    private User connectedUser;
    private String error;
    private  Map<String, String> errors = new HashMap<>();
    private String success;
    private List<Article> articles;
    private Article article;



    public Article getArticle(){
        return this.article;
    }

    public void setArticle(Article article){
        this.article = article;
    }
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

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
