package at.ac.fhcampuswien;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTest {

    private static AppController appController;

    @BeforeAll
    static void setup() {
        appController = new AppController();
    }


    @Test
    public void setArticlesTest() {
        List<Article> articles = new ArrayList<Article>();
        appController.setArticles(articles);
        assertEquals(articles.size(), appController.getArticleCount());
    }

    @Test
    public void getArticleCountTest() {
        appController.setArticles(new ArrayList<Article>());
        assertEquals(0, appController.getArticleCount());
    }

    @Test
    public void getTopHeadlinesAustriaTest1() {
        assertEquals(new ArrayList<Article>(), appController.getTopHeadlinesAustria());
    }

    @Test
    public void getTopHeadlinesAustriaTest2() {
        List<Article> headlines = new ArrayList<Article>();
        appController.setArticles(headlines);
        assertEquals(headlines, appController.getTopHeadlinesAustria());
    }

    /***
     * Test of the filter list size == 0 (implement -> get the articles list from actual articles not manually created)
     */
    @Test
    public void filterListTest() {
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1", "bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "doge"));
        articles.add(new Article("author5", "sports"));

        //assertEquals(appController.filterList(appController.getArticles(),"").size(), 0);
        assertEquals(appController.filterList(articles, "").size(), 5);
    }

    @Test
    public void bitcoinArticlesTest(){
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1", "this title contains the word bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "doge"));
        articles.add(new Article("author5", "sports"));

        appController.setArticles(articles);
        assertTrue(appController.getAllNewsBitcoin().contains(articles.get(0)));

    }
}