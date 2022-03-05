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
     * testing the list on query=bitcoin
     */
    @Test
    public void filterListTest1() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("author1", "bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "dogecoin"));
        articles.add(new Article("author5", "sports"));

        appController.setArticles(articles);
        assertEquals(appController.filterList(appController.getArticles(),"bitcoin").size(), 1);
    }

    /***
     * testing the list on empty query
     */
    @Test
    public void filterListTest2() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("author1", "bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "dogecoin"));
        articles.add(new Article("author5", "sports"));

        appController.setArticles(articles);
        assertEquals(appController.filterList(appController.getArticles(),"").size(), 5);
    }

    /***
     * testing empty list
     */
    @Test
    public void filterListTest3() {
        List<Article> articles = new ArrayList<>();

        appController.setArticles(articles);
        assertEquals(appController.filterList(appController.getArticles(),"btc").size(), 0);
    }


    /**
     * testing to see if the list returned by getAllNewsBitcoin returns the correct article
     */

    @Test
    public void bitcoinArticlesTest1(){
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1", "this title contains the word bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "doge"));
        articles.add(new Article("author5", "sports"));

        appController.setArticles(articles);
        assertTrue(appController.getAllNewsBitcoin().contains(articles.get(0)));

    }

    /**
     * second test case to make sure the list only contains the articles that contain the word bitcoin
     */

    @Test
    public void bitcoinArticlesTest2(){
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1", "this title contains the word bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "doge"));
        articles.add(new Article("author5", "sports"));
        articles.add(new Article("author6", "another article about bitcoin"));

        appController.setArticles(articles);
        assertEquals(appController.getAllNewsBitcoin().size(), 2);
    }

}