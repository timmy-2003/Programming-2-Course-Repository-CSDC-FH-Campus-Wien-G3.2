package at.ac.fhcampuswien;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import at.ac.fhcampuswien.apiStuff.NewsApi;
import at.ac.fhcampuswien.exceptions.NewsAPIException;
import at.ac.fhcampuswien.downloader.Downloader;
import at.ac.fhcampuswien.downloader.ParallelDownloader;
import at.ac.fhcampuswien.downloader.SequentialDownloader;
import at.ac.fhcampuswien.enums.Country;
import at.ac.fhcampuswien.enums.Endpoint;
import at.ac.fhcampuswien.exceptions.NewsAPIException;
import at.ac.fhcampuswien.gui.NewsController;


public class AppController {

    public List<Article> articles = new ArrayList<>();
    private WriteTXT writeTXT = new WriteTXT();

    NewsApi newsApi = NewsApi.getInstance();

    /***
     * when instanced sets the list
     */

    private AppController() {
        // setArticles(NewsApi.jsonToArticleList(new NewsApi().handleRequest(Endpoint.EVERYTHING, "")));
    }

    // Singleton Pattern
    private volatile static AppController appController; // volatile keyword ensures that multiple threads read the correct instance value -> we suggest the compiler to never store the value of the variable in cache memory

    // in the following code, only one thread can enter the getInstance and execute code at the time

    public static AppController getInstance() {
        // double-checked locking
        AppController result = appController;
        if (result != null) {
            return result;
        }
        synchronized (AppController.class) { //?
            if (appController == null) {
                appController = new AppController();
            }
            return appController;
        }
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
    public List<Article> getCustomNews(Enum endpoint, String query, Enum... args) throws IOException {
        // Requests top-headlines with the query corona from the news api
        return articles = NewsApi.jsonToArticleList(newsApi.handleRequest(endpoint, query, args));
    }

    /***
     * get top headlines from austria (for query corona)
     * @return
     */
    public List<Article> getTopHeadlinesAustria() throws IOException {
        saveOriginalArticles();
        // Requests top-headlines with the query corona from the news api
        return articles = NewsApi.jsonToArticleList(newsApi.handleRequest(Endpoint.TOP_HEADLINES, "", Country.AT));
    }

    /***
     * get all news with the query bitcoin
     * @return
     */
    public List<Article> getAllNewsBitcoin() throws IOException {
        //return filterList(articles, "bitcoin");
        // Requests everything with the query bitcoin from the news api
        return articles = NewsApi.jsonToArticleList(newsApi.handleRequest(Endpoint.EVERYTHING, "bitcoin"));
    }

    // Returns the source with the most articles
    public String sourceWithMostArticles() {
        //return new Source("", "");
        /*
        return articles.stream()
                .collect(Collectors.groupingBy(a -> a.getSource().getName(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
         */
        // source (articles) || .map (intermediate operation) || .collect .max .orelse  (terminal operation)
        return articles.stream().map(Article::getSourceName) //get back sourcename
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) //count the articles
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse(null);
    }

    // Returns all articles sorted by the length of the authors name
    public String longestAuthorName() {
        //for articles with no author
        removeNullFromAuthor();
        // source (articles) || .sorted (intermediate operation) || .collect (terminal operation)
        return articles.stream()
                .sorted(Comparator.comparingInt((Article a) -> a.getAuthor().length())) //sorts elements -> length

                .collect(Collectors.toList()).get(articles.size() - 1).getAuthor(); //accumulates elements in a stream into a container such as a collection
    }

    // Returns all articles where the source is the new york times
    public List<Article> sourceNewYorkTimes() {
        return articles.stream().filter(a -> a.getSource().getName() == "New York Times").collect(Collectors.toList());
    }

    //return all  articles which have a title that consists of less than 15 characters
    public List<Article> headLinesUnderFifteenSymbols() {
        return articles.stream().filter(title -> title.getTitle().length() < 55).collect(Collectors.toList());
    }

    //sort the articles by the length of their description in ascending order then alphabetically
    public List<Article> sortAsc() {
        //test function
        // articles.add(new Article("author1", "bitcoin", "yesc"));
        //articles.add(new Article("author1", "xxx", "Desc"));
        //articles.add(new Article("author31", "4", "besc"));
        //articles.add(new Article("4author1", "bitcoin", "xesc"));
        removeNullFromDescription();
        // source (articles) || .sorted .thenComparing (intermediate operation) || .collect (terminal operation)
        return articles.stream()
                .sorted(Comparator.comparingInt((Article a) -> a.getDescription().length())
                        .thenComparing(Article::getDescription, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    /**
     * remove null values otherwise he can't compare
     */
    private void removeNullFromDescription() {
        for (Article a : articles) {
            if (a.getDescription() == null) {
                a.setDescription("");
            }
        }
    }

    /**
     * remove null values from author
     */
    private void removeNullFromAuthor() {
        for (Article a : articles) {
            if (a.getAuthor() == null) {
                a.setAuthor("");
            }
        }
    }

    /***
     * save the articles in originalarticles.txt
     */
    public void saveOriginalArticles() {
        writeTXT.write(articles);
    }


    /***
     * the article URLs are extracted using streams
     * @param downloader
     * @return
     * @throws NewsAPIException
     */

    public int downloadURLs(Downloader downloader) throws NewsAPIException{
        // TODO extract urls from articles with java stream
        //In  downloadURLs() method of AppController the urls of the articles are extracted using streams
        if( articles == null)
            throw new NewsAPIException();

        List<String> urls =articles.stream().map(Article::getUrlToImage).filter(Objects::nonNull).collect(Collectors.toList());



        // {function_body}-> parameter  {body} -> what is done
        //one param -> could be also ((e) ->
        //two param -> must be with (a,b) ->
        articles.forEach(e->{
            urls.add(e.getUrlToImage());
            urls.add(e.getUrl());


            String sentUrlImage = e.getUrlToImage();
            String sentUrl = e.getUrl();
            System.out.println(sentUrlImage + sentUrl);

        });
        return downloader.process(urls);
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
