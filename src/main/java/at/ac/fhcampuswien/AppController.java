package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private List<Article> articles;

    public AppController() {}

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getArticleCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Article> getTopHeadlinesAustria () {
        if (articles == null) {
            return new ArrayList<Article>();
        } else {
            return articles;
        }
    }

    public List<Article> getAllNewsBitcoin(){
        return null;
    }

    protected static List<Article> filterList (List<Article> articles, String query){
        return null;
    }

    private static List<Article> generateMockList(){
        return null;
    }
}
