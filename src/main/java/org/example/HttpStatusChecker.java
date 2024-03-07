package org.example;

public class HttpStatusChecker {

    public static String getStatusImage(int code) {
        if (code < 100 || code > 599) {
            throw new IllegalArgumentException("Response code: " + code);
        }
        return String.format("https://http.cat/%d.jpg", code);
    }
}
