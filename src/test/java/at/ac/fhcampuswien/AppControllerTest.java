package at.ac.fhcampuswien;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTest {

    @Test
    public void setArticlesTest() {
        List<Article> articles = new ArrayList<Article>();
        AppController appController = new AppController();
        appController.setArticles(articles);
        assertEquals(articles.size(), appController.getArticleCount());
    }

    @Test
    public void getArticleCountTest(){
        AppController appController = new AppController();
        appController.setArticles(new ArrayList<Article>());
        assertEquals(0,appController.getArticleCount());
    }

    @Test
    public void getTopHeadlinesAustriaTest1(){
        AppController appController = new AppController();
        assertEquals(new ArrayList<Article>(), appController.getTopHeadlinesAustria());
    }

    @Test
    public void getTopHeadlinesAustriaTest2(){
        List<Article> headlines = new ArrayList<Article>();
        AppController appController = new AppController();
        appController.setArticles(headlines);
        assertEquals(headlines, appController.getTopHeadlinesAustria());
    }





}
