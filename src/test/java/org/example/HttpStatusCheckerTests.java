package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpStatusCheckerTests {

    @Test
    void testStatus200ReturnStringUrl() throws IOException {
        //When
        String actual = HttpStatusChecker.getStatusImage(200);

        //Then
        String expected = "https://http.cat/200.jpg";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testStatus600ReturnIllegalArgumentException () {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatusChecker.getStatusImage(600));
    }

    @Test
    void testStatus50ReturnIllegalArgumentException () {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatusChecker.getStatusImage(50));
    }

    @Test
    void testStatus555NotFoundReturnIllegalArgumentException() {
        //When-Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatusChecker.getStatusImage(555));
    }
}
