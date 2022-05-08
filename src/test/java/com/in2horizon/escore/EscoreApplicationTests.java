package com.in2horizon.escore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*@ExtendWith(SpringExtension.class)*/
@WebAppConfiguration
@SpringBootTest
class EscoreApplicationTests {

    @Test
    void requestResult() {

        try {


            URL url = new URL("https://www.google.com");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            int responseCode = huc.getResponseCode();

            assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        } catch (IOException e) {
       assertEquals(1,2);
            e.getStackTrace();
        }



        /*HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://www.google.com"*//*"https://localhost:8080/admin"*//*))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());


            assertEquals(response.statusCode(), 200);

        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
*/
    }
}
