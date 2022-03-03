package at.ac.fhcampuswien;

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

    public List<Article> getTopHeadlinesAustria (){
        return null;
    }

    public List<Article> getAllNewsBitcoin(){
        return null;
    }

    protected List<Article> filterList (List<Article> articles, String query){
        return null;
    }

    private List<Article> generateMockList(){
        return null;
    }
}
