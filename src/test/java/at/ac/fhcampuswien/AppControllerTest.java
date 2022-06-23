package at.ac.fhcampuswien;


import at.ac.fhcampuswien.gui.NewsController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTest {

    private static AppController appController;

    /***
     * execution before all tests have finished
     */
    @BeforeAll
    static void setup() throws IOException {
        appController = AppController.getInstance();
    }

    /***
     * execution after all tests have finished
     */
    @AfterAll
    public static void finish() {
        System.out.println("Finished all tests");
    }

    /***
     * setting an empty list and test the size
     */
    @Test
    @DisplayName("Testing scenario of setting an empty list")
    public void setArticlesTest() {
        List<Article> articles = new ArrayList<Article>();
        appController.setArticles(articles);
        assertEquals(articles.size(), appController.getArticleCount());
    }

    /***
     * set an empty list and test the getmethod
     */
    @Test
    @DisplayName("Testing scenario of getting an empty list")
    public void getArticleCountTest() {
        appController.setArticles(new ArrayList<Article>());
        assertEquals(0, appController.getArticleCount());
    }

    /***
     * get the top headlines with an empty list
     */


    /*@Test
    @DisplayName("Testing scenario of getting top headlines with empty list")
    public void getTopHeadlinesAustriaTest1() throws IOException {
        assertEquals(new ArrayList<Article>(), appController.getTopHeadlinesAustria());
    }


    get the top headlines with an empty list and set this empty list before

    @Test
    @DisplayName("Testing the getTopHeadLines method with empty list")
    public void getTopHeadlinesAustriaTest2() throws IOException {
        List<Article> headlines = new ArrayList<>();
        appController.setArticles(headlines);
        assertEquals(headlines, appController.getTopHeadlinesAustria());
    }*/

    /***
     * testing the list on query=bitcoin
     */
    @Test
    @DisplayName("Testing scenario of query=bitcoin")
    public void filterListTest1() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("author1", "bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "dogecoin"));
        articles.add(new Article("author5", "sports"));

        appController.setArticles(articles);
        assertEquals(1,appController.filterList(appController.getArticles(),"bitcoin").size());
    }

    /***
     * testing the list on empty query
     */
    @Test
    @DisplayName("Testing scenario of empty query")
    public void filterListTest2() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("author1", "bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "dogecoin"));
        articles.add(new Article("author5", "sports"));

        appController.setArticles(articles);
        assertEquals(5,AppController.filterList(appController.getArticles(),"").size());
    }

    /***
     * testing empty list
     */
    @Test
    @DisplayName("Testing scenario of empty list")
    public void filterListTest3() {
        List<Article> articles = new ArrayList<>();

        appController.setArticles(articles);
        assertEquals(0,appController.filterList(appController.getArticles(),"btc").size());
    }


    /**
     * testing to see if the list returned by getAllNewsBitcoin returns the correct article
     */

    /*

    @Test
    @DisplayName("Check if returned list contains the bitcoin article")
    public void bitcoinArticlesTest1() throws IOException {
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1", "this title contains the word bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "doge"));
        articles.add(new Article("author5", "sports"));

        appController.setArticles(articles);
        assertTrue(appController.getAllNewsBitcoin().contains(articles.get(0)));

    }


     // second test case to make sure the list only contains the articles that contain the word bitcoin


    @Test
    @DisplayName("Check length of returned list of articles")
    public void bitcoinArticlesTest2() throws IOException {
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1", "this title contains the word bitcoin"));
        articles.add(new Article("author2", "football"));
        articles.add(new Article("author3", "politics"));
        articles.add(new Article("author4", "doge"));
        articles.add(new Article("author5", "sports"));
        articles.add(new Article("author6", "another article about bitcoin"));

        appController.setArticles(articles);
        assertEquals(2,appController.getAllNewsBitcoin().size());
    }*/
}