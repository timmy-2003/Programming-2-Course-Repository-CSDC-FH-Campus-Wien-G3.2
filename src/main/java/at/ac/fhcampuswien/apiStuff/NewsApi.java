package at.ac.fhcampuswien.apiStuff;
import at.ac.fhcampuswien.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class NewsApi {

    private final String APIKEY = "b44b32e1c7de47c7827d10cdf122365f";
    private final String URL = "https://newsapi.org/v2/";

    /* Possible endpoints are "everything" and "top-headlines" */
    /*public String handleRequest (String query, String endpoint)throws IOException{
        String url = URL + endpoint + "?q=" + query + "&apiKey=" + APIKEY;
        return request(urlfirs);
    }*/

    /**
     * build URL and handle request
     * @param endpoint either everything or top-headlines
     * @param query search for a given word or expression
     * @param args any Enums that need to be added to the URL
     * @return response of the API as a String
     */

    public String handleRequest(Enum endpoint, String query, Enum ... args) throws IOException {
        StringBuilder url = new StringBuilder(URL);
        url.append(endpoint.toString());
        url.append("?q=").append(query);

        for (int i = 0; i< args.length; i++){
            url.append("&").append(args[i].toString());
        }

        url.append("&pageSize=100");

        url.append("&apiKey=").append(APIKEY);

        System.out.println(url);

        return request(String.valueOf(url));

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
