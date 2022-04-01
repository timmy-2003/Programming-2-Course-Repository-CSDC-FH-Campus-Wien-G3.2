package at.ac.fhcampuswien;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;


public class NewsApi {

    private final String APIKEY = "193df6fbc5ad43e3b3450635be311c7e";
    private final String URL = "https://newsapi.org/v2/";

    public String handleRequest (String query, String endpoint)throws IOException{
        String url = URL + endpoint + "?q=" + query + "&apiKey=" + APIKEY;
        return request(url);
    }

    public static String request(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
