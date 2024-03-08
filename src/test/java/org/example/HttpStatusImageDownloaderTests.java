package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


public class HttpStatusImageDownloaderTests {

    @Test
    void testStatus200DownloadAndSaveFile() throws IOException {
        File file = new File("./200.jpg");
        if (file.exists()) {
            file.delete();
            System.out.println(">>> Test: (testStatus200DownloadAndSaveFile) File deleted for test: ./200.jpg");
        }
        //When
        HttpStatusImageDownloader.downloadStatusImage(200);

        //Then
        Assertions.assertTrue(file.exists());
    }

    @Test
    void testStatus600ReturnIllegalArgumentException () {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatusImageDownloader.downloadStatusImage(600));
    }

    @Test
    void testStatus50ReturnIllegalArgumentException () {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatusImageDownloader.downloadStatusImage(50));
    }

    @Test
    void testStatus555NotFoundReturnIllegalArgumentException() {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatusImageDownloader.downloadStatusImage(555));
    }
}
