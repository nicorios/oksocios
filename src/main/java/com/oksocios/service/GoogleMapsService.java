package com.oksocios.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {

    private final RestTemplate restTemplate;

    private String gmsHost = "https://maps.googleapis.com/maps/api/geocode/json";
    private String gmsKey = "AIzaSyDdGKeF76VJl28uWcUOg2vS8t9Sk1kFrHw";


    public GoogleMapsService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public JsonNode getCandidateById(String street, Integer number){
        ResponseEntity response;

        String address = this.buildAddress(street, number);

        try{
            response = restTemplate.getForEntity(String.format("%s?address=%s&key=%s", gmsHost, address, gmsKey), JsonNode.class);
        }catch(HttpClientErrorException exception){
            return null;
        }

        JsonNode object = (JsonNode) response.getBody();
        JsonNode result = object.get("results").get(0).get("geometry").get("location");
        return result;
    }

    public String buildAddress(String street, Integer number){
        return String.format("%s+%s,+Cordoba,+ARG", street, number);
    }

}
