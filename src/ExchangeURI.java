import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeURI {

    public ExchangeRate exchangeRate(String base_code) throws IOException, InterruptedException {
        String API_KEY = "ae7750e72273e48ea4d91e7a";
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + base_code;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ExchangeRate.class);
        } catch (Exception e) {
            throw new RuntimeException("Error.");
        }
    }
}