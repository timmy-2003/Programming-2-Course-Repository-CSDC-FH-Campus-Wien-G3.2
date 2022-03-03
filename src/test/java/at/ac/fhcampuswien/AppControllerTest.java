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


}
