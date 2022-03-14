package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AppController {

    private List<Article> articles = new ArrayList<>();

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

    /***
     * filled the mocklist with dummy values
     * @return
     */
    private static List<Article> generateMockList() {

        List<Article> mockList = new ArrayList<>();

        mockList.add(new Article("The Wall Street Journal", "Bitcoin Price Surges on Biden’s Crypto Executive Order"));
        mockList.add(new Article("The New York Times", "Can We Trust What’s Happening to Money?"));
        mockList.add(new Article("USA Today", "In Switzerland, all the political power belongs to the people"));
        mockList.add(new Article("New York Post", "Inflation is bad now — but the Fed could return us to the bad old days of the ’70s"));
        mockList.add(new Article("CoinDesk", "What Is a Satoshi? Understanding the Smallest Unit of Bitcoin"));
        mockList.add(new Article("Deseret News", "Austria and the Sublime Porte"));
        mockList.add(new Article("CoinDesk", "Austria to Tax Crypto Like Stocks and Bonds"));
        mockList.add(new Article("CoinDesk", "Bitcoin-Banning Measure Seen Too Close to Call in Monday's EU Parliament Vote"));

        return mockList;
    }
}
