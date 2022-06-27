package at.ac.fhcampuswien.apiStuff;

import at.ac.fhcampuswien.Article;
import at.ac.fhcampuswien.exceptions.NoInternetException;
import at.ac.fhcampuswien.exceptions.urlException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

import at.ac.fhcampuswien.exceptions.urlException;


// singleton pattern
public class NewsApi {

    public static String APIKEY;
    private final String URL = "https://newsapi.org/v2/";
    public static String errorMessage = "";


    private NewsApi() {}

    private static final NewsApi newsApi = new NewsApi();


    public static NewsApi getInstance()
    {
        return newsApi;
    }


    /**
     * get the API key
     *
     * @return
     */
    public static String getAPIKEY() {
        return APIKEY;
    }

    /***
     * set the API key
     * @param APIKEY
     */
    public static void setAPIKEY(String APIKEY) {
        NewsApi.APIKEY = APIKEY;
    }

    /* Possible endpoints are "everything" and "top-headlines" */
    /*public String handleRequest (String query, String endpoint)throws IOException{
        String url = URL + endpoint + "?q=" + query + "&apiKey=" + APIKEY;
        return request(urlfirs);
    }*/

    /**
     * build URL and handle request
     *
     * @param endpoint either everything or top-headlines
     * @param query    search for a given word or expression
     * @param args     any Enums that need to be added to the URL
     * @return response of the API as a String
     */

    public String handleRequest(Enum endpoint, String query, Enum... args) throws IOException {
        StringBuilder url = new StringBuilder(URL);
        url.append(endpoint.toString());
        url.append("?q=").append(query);

        for (Enum arg : args) {
            url.append("&").append(arg.toString());
        }

        url.append("&pageSize=50");

        url.append("&apiKey=").append(APIKEY);

        System.out.println(url);

        if (!checkConnection()){
            try {
                throw new NoInternetException("Please check your internet connection!");
            } catch (NoInternetException e) {
                System.out.println("No internet connection");
            }
        }

        return request(String.valueOf(url));

    }

    public static String request(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        if (!checkForEndpoints(url)) {
            try {
                throw new urlException();
            } catch (urlException e) {
                System.out.println(e.getMessage());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //System.out.println(response.body().string());
            return response.body().string();
        } catch (Exception e){
            System.out.println("Something went wrong");
            return "";
        }
    }

    public static List<Article> jsonToArticleList(String jsonString) {
        // Creates a new gson builder
        GsonBuilder builder = new GsonBuilder();
        // Makes the gson builder format everything pretty?
        builder.setPrettyPrinting();
        // Creates a new gson object
        Gson gson = builder.create();
        // Converts the json string into a news response object
        NewsResponse response = gson.fromJson(jsonString, NewsResponse.class);
        // If the arraylist of articles is not empty, return the converted news response objects list of articles
        if (response.getArticles() != null) {
            return response.getArticles();
        }
        // Else, return an empty list
        else {
            errorMessage = jsonString;
            return Collections.emptyList();
        }
    }

    /**
     * @param url our http request url
     * @return boolean whether the given url contains a valid endpoint parameter
     */

    private static boolean checkForEndpoints(String url) {
        return url.contains("everything") || url.contains("top-headlines");
    }

    private static boolean checkConnection(){
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e){
            return false;
        }

    }


}
