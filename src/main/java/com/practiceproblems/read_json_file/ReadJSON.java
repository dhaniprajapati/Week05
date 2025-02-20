package com.practiceproblems.read_json_file;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ReadJSON {
    public static void main(String[] args) {
        try {
            // Read the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            String filePath= "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day02\\src\\main\\java\\com\\practiceproblems\\read_json_file\\file.json";
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            // Loop through each JSON object and extract fields
            for (JsonNode node : rootNode) {
                String name = node.get("name").asText();
                String email = node.get("email").asText();
                System.out.println("Name: " + name + ", Email: " + email);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
