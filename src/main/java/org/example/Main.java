package org.example;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

public class Main {
    public static void main(String[] args) {

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
            for (User user: response.getBody()) {
                System.out.println(user);
            }
            String cookie = response.getHeaders().get("Set-Cookie").get(0);
        System.out.println(cookie.split(";")[0]);
            User user = new User();
            user.setId(3l);
            user.setName("James");
            user.setLastName("Brown");
            user.setAge(21);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cookie", cookie);
        headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> map = new HashMap<>();
            map.put("name", "James");
            map.put("lastName", "Brown");
            map.put("age", "21");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
            RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String>     response2 = restTemplate2.postForEntity(url, entity, String.class);
        System.out.println(response2.getBody());
        user.setName("Thomas");
        user.setLastName("Shelby");


        ResponseEntity<String>     response3 = restTemplate2.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.println(response3.getBody());

        HttpEntity<String> entity2 = new HttpEntity<>(headers);
        ResponseEntity<String>     response4 = restTemplate2.exchange(url + "/3", HttpMethod.DELETE, entity2, String.class);
        System.out.println(response4.getBody());

    }
}