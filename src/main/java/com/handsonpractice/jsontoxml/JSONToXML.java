package com.handsonpractice.jsontoxml;

import org.json.JSONObject;
import org.json.XML;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONToXML {

    public static void main(String[] args) {
        String file = "src/main/java/com/handsonpractice/jsontoxml/file.json";
        try {
            // Read JSON from file
            String jsonString = new String(Files.readAllBytes(Paths.get(file)));
            // Convert JSON to XML
            JSONObject json = new JSONObject(jsonString);
            String xml = XML.toString(json);
            System.out.println("XML: " + xml);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
