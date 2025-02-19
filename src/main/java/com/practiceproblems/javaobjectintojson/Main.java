package com.practiceproblems.javaobjectintojson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JavaObjectIntoJSON.Car car= new JavaObjectIntoJSON.Car("Audi","47gresjk", 2014);
            // Convert Java Object to JSON String
            String jsonString = objectMapper.writeValueAsString(car);
            System.out.println(jsonString);

        } catch (JsonProcessingException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
