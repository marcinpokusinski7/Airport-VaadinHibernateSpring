package com.vaadin.fastui.backend.service;


import com.google.gson.Gson;
import com.vaadin.fastui.backend.POJO.Hit;
import com.vaadin.fastui.backend.POJO.PixaBay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class ApiCityPhotosService {
    private FlightService flightService;
    private String temp = "";
    @Value("${api.key}")
    private String apiKey;


    public String getImage(String city) throws IOException {

        URL url = new URL("https://pixabay.com/api/?key=" + apiKey + "&q="+city+"&per_page=3");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        PixaBay pixaBay = new Gson().fromJson(reader, PixaBay.class);

        for (Hit hit : pixaBay.getHits()) {
            temp = hit.getWebformatURL();
        }
        URL url1 = new URL(temp);
        return url1.toString();
    }


}
