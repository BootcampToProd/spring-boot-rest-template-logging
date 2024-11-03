package com.bootcamptoprod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplateLoggingController {

    @Autowired
    private RestTemplate restTemplate;  // Autowire the RestTemplate

    @GetMapping("/test-api")
    public ResponseEntity<String> callApi() {
        String url = "https://jsonplaceholder.typicode.com/posts";  // External API URL

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");  // Set content type

        // Create a request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title", "foo");
        requestBody.put("body", "bar");
        requestBody.put("userId", "1");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers); // Wrap headers and body

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);  // Execute the request
    }
}
