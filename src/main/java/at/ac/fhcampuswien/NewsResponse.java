package at.ac.fhcampuswien;

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

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
