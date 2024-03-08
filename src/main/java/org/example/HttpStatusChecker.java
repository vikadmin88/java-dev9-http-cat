package org.example;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpStatusChecker {

    public static String getStatusImage(int code) throws IOException {
        if (code < 100 || code > 599) {
            throw new IllegalArgumentException("Incorrect code: " + code);
        }
        String url = String.format("https://http.cat/%d.jpg", code);

        Request request = HttpClient.getBuilder()
                .url(url)
                .build();

        try (Response response = HttpClient.doCall(request)) {
            if (!response.isSuccessful()) {
                throw new IllegalArgumentException("Error with response code: " + response.code());
            }
            return url;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
