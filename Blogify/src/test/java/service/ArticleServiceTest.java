package service;

import repository.impl.ArticleRepositoryImpl;
import service.impl.ArticleServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entity.Article;
import entity.User;
import enums.UserRole;
import model.ArticleDTO;

public class ArticleServiceTest {

    @Mock
    private ArticleRepositoryImpl articleRepositoryImpl;

    @InjectMocks
    private ArticleServiceImpl articleServiceImpl;

    private User user1;
    private User user2;
    private List<Article> mockArticles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User("user one", "one", "user_one@example.com", "user_one_password", null, UserRole.EDITOR);
        user2 = new User("user two", "two", "user_two@example.com", "user_two_password", null, UserRole.EDITOR);

        mockArticles = Arrays.asList(
            new Article(
                "https://example.com/article-image1.jpg", 
                "This is the content of the first article",   
                LocalDateTime.now(),                    
                LocalDateTime.of(2024, 10, 10, 12, 0),  
                "DRAFT",                                
                "The title of the article1",              
                user1                                
            ),
            new Article(
                "https://example.com/article-image2.jpg", 
                "This is the content of the second article",   
                LocalDateTime.now(),                    
                LocalDateTime.of(2024, 10, 10, 12, 0),  
                "DRAFT",                                
                "The title of the article2",              
                user2                           
            )
        );
    }

   

    @Test 
    void testAllArticles() {

       List<ArticleDTO> mockArticles = new ArrayList<>();
        mockArticles.add(new ArticleDTO(1L, "url1", "Content1", LocalDateTime.now(), LocalDateTime.now(), "DRAFT", "The title of the article1", 1L, "FirstName1", "LastName1"));
        mockArticles.add(new ArticleDTO(2L, "url2", "Content2", LocalDateTime.now(), LocalDateTime.now(), "PUBLISHED", "The title of the article2", 2L, "FirstName2", "LastName2"));

        when(articleRepositoryImpl.getAllArticles(0, 5,"ALL")).thenReturn(mockArticles);

        List<ArticleDTO> result = articleServiceImpl.getAllArticles(1,"ALL");

        // Assertions
        assertEquals(2, result.size()); 
        assertEquals("The title of the article1", result.get(0).getTitle());
        assertEquals("The title of the article2", result.get(1).getTitle());

        verify(articleRepositoryImpl, times(1)).getAllArticles(0, 5,"ALL");
    }

    @Test
    public void testFindArticleById(){

        when(articleRepositoryImpl.getArticleById(1L)).thenReturn(Optional.of(mockArticles.get(0)));



        Article result = articleServiceImpl.findArticleById(1L);

        assertNotNull(result);
        assertEquals("The title of the article1", result.getTitle());
        verify(articleRepositoryImpl).getArticleById(1L);
    }

    @Test
    public void testFindArticleById_Found() {

        Long articleId = 1L;
      
        when(articleRepositoryImpl.getArticleById(articleId)).thenReturn(Optional.of(mockArticles.get(0)));

        Article result = articleServiceImpl.findArticleById(articleId);

        assertNotNull(result, "The returned article should not be null");
        assertEquals("The title of the article1", result.getTitle(), "The article title should match the mock data");
        assertEquals("DRAFT", result.getStatus(), "The article status should be 'DRAFT'");
        assertEquals(user1, result.getUserId(), "The article's user should match the mock user");

        // Verify
        verify(articleRepositoryImpl, times(1)).getArticleById(articleId);
    }

    @Test
    public void testAddNewArticle(){
        Article articleToAdd = mockArticles.get(0);
        Article savedArticle = new Article(
                articleToAdd.getArticlePictureUrl(),
                articleToAdd.getContent(),
                articleToAdd.getCreationDate(),
                articleToAdd.getPublishedDateTime(),
                articleToAdd.getStatus(),
                articleToAdd.getTitle(),
                articleToAdd.getUserId()
        );
        savedArticle.setId(1L); 
    
        when(articleRepositoryImpl.save(any(Article.class))).thenReturn(savedArticle);
    
        Boolean result = articleServiceImpl.addNewArticle(articleToAdd);
    
        assertTrue(result); 
        verify(articleRepositoryImpl).save(articleToAdd); 
    }

    @Test
    public void testUpdateArticle() {
        Long articleId = 1L;
        Article existingArticle = mockArticles.get(0);
        existingArticle.setId(articleId); 
    
        Article updArticle = existingArticle;
        updArticle.setTitle("new title");
        updArticle.setStatus("PUBLISHED");
    
        when(articleRepositoryImpl.updateArticle(any(Article.class))).thenReturn(true);
        when(articleRepositoryImpl.getArticleById(articleId)).thenReturn(Optional.of(existingArticle));
    
        Boolean result = articleServiceImpl.updateArticle(updArticle);
    
        assertTrue(result, "The updateArticle method should return true when an article is updated successfully.");
        
        verify(articleRepositoryImpl).updateArticle(updArticle);
    }


    @Test
    public void testLikeFunction() {
        Long articleId = 1L;
        Article existingArticle = mockArticles.get(0);
        existingArticle.setId(articleId); 
        
        when(articleRepositoryImpl.like(articleId)).thenReturn(true);

        Boolean result = articleServiceImpl.like(articleId);

        assertTrue(result, "The status of liked is changed");
        verify(articleRepositoryImpl).like(articleId);



       

    }
    

}

