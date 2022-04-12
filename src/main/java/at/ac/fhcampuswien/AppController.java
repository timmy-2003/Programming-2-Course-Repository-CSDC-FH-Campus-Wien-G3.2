package at.ac.fhcampuswien;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AppController {

    private List<Article> articles = new ArrayList<>();

    /***
     * when instanced set the list with our mocklist
     */
    public AppController() {
        setArticles(generateMockList());
    }

    /***
     * set articles from input list
     * @param articles
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /***
     * return our article list size as an int
     * @return
     */
    public int getArticleCount() {
        return articles.size();
    }

    /***
     * return our article list
     * @return
     */
    public List<Article> getArticles() {
        return articles;
    }

    /***
     * get top headlines from austria (at the moment just the full list back)
     * @return
     */
    public List<Article> getTopHeadlinesAustria() throws IOException {
        String jsonString = new NewsApi().handleRequest("corona","top-headlines");
        System.out.println(jsonString);
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.setPrettyPrinting();
        Gson gson = gsonbuilder.create();

        Type listType = new TypeToken<ArrayList<Article>>(){}.getType();
        ArrayList<Article> articleList = gson.fromJson(jsonString, listType);

        System.out.println(articleList.toString());

        //---
        if (articles == null) {
            return new ArrayList<Article>();
        } else {
            return articles;
        }
    }

    /***
     * get all news with the query bitcoin
     * @return
     */
    public List<Article> getAllNewsBitcoin() {
        return filterList(articles, "bitcoin");
    }

    /***
     * filter the input list based on the query
     * @param articles
     * @param query
     * @return
     */
    protected static List<Article> filterList(List<Article> articles, String query) {
        List<Article> tmp = new ArrayList<>();
        for (Article a : articles) {
            if (a.getTitle().toLowerCase().contains(query)) {
                tmp.add(a);
            }
        }
        return tmp;
    }

    /***
     * filled the mockList with dummy values
     * @return the mockList
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
