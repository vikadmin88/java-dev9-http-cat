package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClient {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static OkHttpClient getClient() {
        return CLIENT;
    }

    public static Request.Builder getBuilder() {
        return new Request.Builder();

    }

    public static Response doCall(Request request) throws IOException {
            return CLIENT.newCall(request).execute();
    }
}
