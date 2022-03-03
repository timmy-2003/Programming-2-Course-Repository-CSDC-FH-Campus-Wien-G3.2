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
    public void getArticleCountTest() {
        AppController appController = new AppController();
        appController.setArticles(new ArrayList<Article>());
        assertEquals(0, appController.getArticleCount());
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

   /* @Test
        public void getFilterList(){
        AppController appController = new AppController();
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1","bitcoin"));
        articles.add(new Article("author2","football"));
        articles.add(new Article("author3","politics"));
        articles.add(new Article("author4","doge"));
        articles.add(new Article("author5","sports"));
        //appController.filterList(articles, "bitcoin");

        List<Article> testlist = new ArrayList<Article>();
        testlist.add(new Article("author1","bitcoin"));
        assertEquals(testlist, appController.filterList(articles, "bitcoin"));
    }*/

    /***
     * Test of the filter list size == 0 (implement -> get the articles list from actual articles not manually created)
     */
    @Test
    public void filterListTest() {
        AppController appController = new AppController();
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("author1","bitcoin"));
        articles.add(new Article("author2","football"));
        articles.add(new Article("author3","politics"));
        articles.add(new Article("author4","doge"));
        articles.add(new Article("author5","sports"));

        //assertEquals(appController.filterList(appController.getArticles(),"").size(), 0);
        assertEquals(appController.filterList(articles,"").size(), 5);
    }








}