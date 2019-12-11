package com.liuboyu.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MacAddressTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://api.maclookup.app/v1/macs/da:a1:19:ab:4c:65", String.class);
        System.out.println(response);
    }

}
