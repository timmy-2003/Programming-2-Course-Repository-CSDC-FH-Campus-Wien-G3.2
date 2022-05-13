package at.ac.fhcampuswien;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.ac.fhcampuswien.apiStuff.NewsApi;
import at.ac.fhcampuswien.enums.Endpoint;

public class AppController {

    private List<Article> articles = new ArrayList<>();

    /***
     * when instanced sets the list
     */

    public AppController() throws IOException {
       // setArticles(NewsApi.jsonToArticleList(new NewsApi().handleRequest(Endpoint.EVERYTHING, "")));
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
     * get top headlines from austria (for query corona)
     * @return
     */
    public List<Article> getTopHeadlinesAustria() throws IOException {
        // Requests top-headlines with the query corona from the news api
        return NewsApi.jsonToArticleList(new NewsApi().handleRequest(Endpoint.TOP_HEADLINES, "corona"));
    }

    /***
     * get all news with the query bitcoin
     * @return
     */
    public List<Article> getAllNewsBitcoin() throws IOException {
        //return filterList(articles, "bitcoin");
        // Requests everything with the query bitcoin from the news api
        return NewsApi.jsonToArticleList(new NewsApi().handleRequest(Endpoint.EVERYTHING, "bitcoin"));
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
}
