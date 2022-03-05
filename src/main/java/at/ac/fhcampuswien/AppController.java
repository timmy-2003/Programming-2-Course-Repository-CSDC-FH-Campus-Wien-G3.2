package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AppController {

    private List<Article> articles;

    public AppController() {
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getArticleCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Article> getTopHeadlinesAustria() {
        if (articles == null) {
            return new ArrayList<Article>();
        } else {
            return articles;
        }
    }

    public List<Article> getAllNewsBitcoin() {
        return filterList(articles, "bitcoin");
    }

    protected static List<Article> filterList(List<Article> articles, String query) {
        List<Article> tmp = new ArrayList<>();
        for (Article a : articles) {
            if (a.getTitle().toLowerCase().contains(query)) {
                tmp.add(a);
                //System.out.println(a);
            }
        }
        return tmp;
    }

    private static List<Article> generateMockList() {
        return null;
    }
}
