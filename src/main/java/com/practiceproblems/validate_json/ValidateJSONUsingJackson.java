package com.practiceproblems.validate_json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateJSONUsingJackson {
    public static void main(String[] args) {
        String jsonString = "{ \"name\": \"Dhani\", \"age\": 22 }";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println("Valid JSON: " + jsonNode.toPrettyString());
        } catch (Exception e) {
            System.out.println("Invalid JSON: " + e.getMessage());
        }
    }
}
