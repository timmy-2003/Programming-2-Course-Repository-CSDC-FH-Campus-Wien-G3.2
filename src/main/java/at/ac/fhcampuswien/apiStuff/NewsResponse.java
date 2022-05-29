package at.ac.fhcampuswien.apiStuff;

import at.ac.fhcampuswien.Article;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse {

    private String status;
    private int totalResults;
    private List<Article> articles;

    public NewsResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

}
