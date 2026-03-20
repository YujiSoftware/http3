import java.net.URI;
import java.net.http.*;
import java.net.http.HttpOption.Http3DiscoveryMode;

void main() throws java.io.IOException, InterruptedException {
    // Debugging options for the HTTP client
    // System.setProperty("jdk.internal.httpclient.debug", "true");

    try (var client = HttpClient.newHttpClient()) {
        var uri = URI.create("https://www.google.com/");
        var request = HttpRequest.newBuilder(uri)
            .version(HttpClient.Version.HTTP_3)
            .setOption(HttpOption.H3_DISCOVERY, Http3DiscoveryMode.ANY)
            .GET().build();

        HttpResponse<String> response =
            client.send(request, HttpResponse.BodyHandlers.ofString());
        IO.println("Status code: " + response.statusCode());
        IO.println("Version: " + response.version());
    }
}
