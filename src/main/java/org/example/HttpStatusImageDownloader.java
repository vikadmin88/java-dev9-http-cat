package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HttpStatusImageDownloader {

    public static void downloadStatusImage(int code) throws IOException {
        String url = HttpStatusChecker.getStatusImage(code);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url(url)
                    .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful() || response.body() == null) {
                throw new IOException("Response code: " + response.code());
            }
            InputStream inputStream = response.body().byteStream();

            if (!saveImage(inputStream, code)) {
                throw new IOException("File not saved");
            }

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static boolean saveImage(InputStream inputStream, int code) throws IOException {

        File file = new File("./" + code + ".jpg");
        BufferedImage image = ImageIO.read(inputStream);
        ImageIO.write(image, "jpg", file);

        return file.exists();
    }
}
