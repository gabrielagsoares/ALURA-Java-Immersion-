import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
    
        // conection HTTP and search top 250 movies
        String url = "https://imdb-api.com/en/API/Top250Movies/k_b0ysqh7i";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // get most important data (title, poster, rating,)
        var parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        // show and handle data
        for (Map<String,String> movie: moviesList){
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            
        }
    }
}
