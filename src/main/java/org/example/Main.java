package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Hello world!");
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://94.198.50.185:7081/api/users";
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);
            ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getStatusCodeValue());
            for (User user: response.getBody()) {
                System.out.println(user);
            }
            System.out.println(response.getBody());
            String cookie = response.getHeaders().get("Set-Cookie").get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}