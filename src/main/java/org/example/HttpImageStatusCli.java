package org.example;

import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {

    public static void askStatus() {
        int code = -1;
        String message = "Enter http code (valid values are from 100 to 599) or 0 to exit: ";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (code == -1) {
                System.out.print(message);
            }

            if (scanner.hasNextInt()) {
                code = scanner.nextInt();

                if (code == 0) {
                    break;
                }

                if (code < 100 || code > 599) {
                    System.out.print("Invalid value! " + message);
                } else {
                    try {
                        HttpStatusImageDownloader.downloadStatusImage(code);
                        System.out.println("Downloaded image with code: " + code);
                    } catch (IOException e) {
                        System.out.println("There is no image for HTTP status " + code + ". Exception: " + e.getMessage());
                    }
                    code = -1;
                }
            } else {
                scanner.next();
                code = -1;
            }
        }
    }
}
