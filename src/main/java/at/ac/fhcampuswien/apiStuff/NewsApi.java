package at.ac.fhcampuswien.apiStuff;
import at.ac.fhcampuswien.Article;
import at.ac.fhcampuswien.enums.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class NewsApi {

    private final String APIKEY = "193df6fbc5ad43e3b3450635be311c7e";
    private final String URL = "https://newsapi.org/v2/";

    /* Possible endpoints are "everything" and "top-headlines" */
    /*public String handleRequest (String query, String endpoint)throws IOException{
        String url = URL + endpoint + "?q=" + query + "&apiKey=" + APIKEY;
        return request(urlfirs);
    }*/



    public String runRequest(Enum endpoint, String query, Enum ... args) throws IOException {
        StringBuilder url = new StringBuilder(URL);
        url.append(endpoint.toString());
        url.append("?q=").append(query);

        for (Enum parameter:args) {
            url.append("&").append(parameter.toString());
        }

        url.append("&apiKey=").append(APIKEY);

        System.out.println(url);

        try{
            return request(url.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("URL might be invalid, please check: " + url);
        return "";

    }

    public static String request(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //System.out.println(response.body().string());
            return response.body().string();
        }
    }

    public static List<Article> jsonToArticleList(String jsonString)
    {
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
        // Else, return an empty list
        else {
            return Collections.emptyList();
        }
    }
}
