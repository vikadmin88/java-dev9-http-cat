package org.example;

import okhttp3.Request;
import okhttp3.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class HttpStatusImageDownloader {

    public static void downloadStatusImage(int code) throws IOException, IllegalArgumentException {
        String url = null;
        try {
            url = HttpStatusChecker.getStatusImage(code);
        } catch (IOException e) {
            throw new IOException(e);
        }

        Request request = HttpClient.getBuilder()
                .url(url)
                .build();

        try (Response response = HttpClient.doCall(request)) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new IllegalArgumentException("Error with response code: " + response.code());
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
