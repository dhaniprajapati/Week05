package com.csvdatahandling.advanceproblems.convertjsontocsv;

import org.json.*;
import java.io.*;
import java.util.*;

public class ConvertJSONToCSV {
   // Method to convert JSON to CSV
    public static void jsonToCsv(String jsonFilePath, String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Read entire JSON file
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            // Convert JSON string to JSONArray
            JSONArray jsonArray = new JSONArray(jsonContent.toString());
            // Extract CSV headers
            if (jsonArray.length() == 0) {
                System.out.println("JSON file is empty!");
                return;
            }
            JSONObject firstObject = jsonArray.getJSONObject(0);
            String[] headers = JSONObject.getNames(firstObject);
            writer.write(String.join(",", headers) + "\n");
            // Write each JSON object as a CSV row
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                List<String> values = new ArrayList<>();
                for (String header : headers) {
                    values.add(obj.get(header).toString());
                }
                writer.write(String.join(",", values) + "\n");
            }
            System.out.println("JSON converted to CSV successfully");
        } catch (Exception e) {
            System.out.println("Error converting JSON to CSV: " + e.getMessage());
        }
    }
    // Method to convert CSV to JSON
    public static void csvToJson(String csvFilePath, String jsonOutputPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(jsonOutputPath))) {
            // Read CSV headers
            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.out.println("CSV file is empty!");
                return;
            }
            String[] headers = headerLine.split(",");
            // Convert CSV data to JSONArray
            JSONArray jsonArray = new JSONArray();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                JSONObject obj = new JSONObject();
                for (int i = 0; i < headers.length; i++) {
                    obj.put(headers[i], values[i]);
                }
                jsonArray.put(obj);
            }
            // Write JSON array to file
            writer.write(jsonArray.toString(4));
            System.out.println("CSV converted to JSON successfully");
        } catch (Exception e) {
            System.out.println("Error converting CSV to JSON: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        String jsonFilePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\convertjsontocsv\\student.json";
        String csvFilePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\convertjsontocsv\\student.csv";
        String jsonOutputPath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\convertjsontocsv\\converted_students.json";
        // Convert JSON to CSV
        jsonToCsv(jsonFilePath, csvFilePath);
        // Convert CSV back to JSON
        csvToJson(csvFilePath, jsonOutputPath);
    }
}

