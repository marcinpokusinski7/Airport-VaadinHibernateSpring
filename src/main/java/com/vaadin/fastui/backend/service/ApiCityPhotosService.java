package com.vaadin.fastui.backend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.atmosphere.config.service.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;


public class ApiCityPhotosService {
    private FlightService flightService;

    @Value("${api.key}")
    private String apiKey;

    private String POSTS_API_URL = "https://pixabay.com/api/?key=" +apiKey +"&q=france&image_type=photo";


    @RequestMapping("/movie")
   public void getImage(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, ,HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new JsonMapper();
//        List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>());
        posts.forEach(System.out::println);
    }


}
