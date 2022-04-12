package at.ac.fhcampuswien;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        // Requests top-headlines with the query corona from the news api
        String jsonString = new NewsApi().handleRequest("corona","top-headlines");
        // Creates a new gson builder
        GsonBuilder builder = new GsonBuilder();
        // Makes the gson builder format everything pretty?
        builder.setPrettyPrinting();
        // Creates a new gson object
        Gson gson = builder.create();
        // Converts the json string into a news response object
        NewsResponse response =  gson.fromJson(jsonString, NewsResponse.class);
        // If the arraylist of articles is not empty, return the converted news response objects list of articles
        if (response.getArticles() != null) {
            return response.getArticles();
        }
        else {
            return new ArrayList<Article>();
        }
    }

    /***
     * get all news with the query bitcoin
     * @return
     */
    public List<Article> getAllNewsBitcoin() throws IOException {
        //return filterList(articles, "bitcoin");
        // Requests everything with the query bitcoin from the news api
        String jsonString = new NewsApi().handleRequest("bitcoin","everything");
        // Creates a new gson builder
        GsonBuilder builder = new GsonBuilder();
        // Makes the gson builder format everything pretty?
        builder.setPrettyPrinting();
        // Creates a new gson object
        Gson gson = builder.create();
        // Converts the json string into a news response object
        NewsResponse response =  gson.fromJson(jsonString, NewsResponse.class);
        // If the arraylist of articles is not empty, return the converted news response objects list of articles
        if (response.getArticles() != null) {
            return response.getArticles();
        }
        else {
            return new ArrayList<Article>();
        }
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
