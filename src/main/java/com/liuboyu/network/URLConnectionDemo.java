package com.liuboyu.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL Connection Demo
 * <p>
 * Created by Tony on 29/05/2017.
 */
public class URLConnectionDemo {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8080/get?name=Tony");
        URLConnection urlConnection = url.openConnection();
        try (InputStream inputStream = urlConnection.getInputStream()) {
            Reader reader = new InputStreamReader(inputStream);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.println(c);
                System.out.println((char) c);
            }
        }

    }

}
