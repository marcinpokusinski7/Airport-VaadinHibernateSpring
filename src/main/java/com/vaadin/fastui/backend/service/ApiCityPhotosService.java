package com.vaadin.fastui.backend.service;


import com.vaadin.fastui.backend.entity.Flight;
import com.vaadin.fastui.backend.repository.FlightRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class ApiService {
        private String url = "https://www.pexels.com/photo/trees-during-day-3573351/";
        private FlightService flightService;

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.pexels.com/photo/trees-during-day-3573351/"))
            .header()
            .GET()
            .build();


}
