package com.csvdatahandling.advanceproblems.detectduplicatesincsvfile;

import java.io.*;
import java.util.*;

public class DetectDuplicates {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\detectduplicatesincsvfile\\student.csv";
        // hashmap to store unique IDs and their rows
        Map<String, String> recordMap = new HashMap<>();
        // list to store duplicate records
        List<String> duplicateRecords = new ArrayList<>();
        // read file using BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            // loop through each line in the file
            while ((line = br.readLine()) != null) {
                // skip the header
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                // split the line using a comma
                String[] data = line.split(",");
                String id = data[0].trim();
                // check if ID already exists
                if (recordMap.containsKey(id)) {
                    // store duplicate record
                    duplicateRecords.add(line);
                } else {
                    // store unique record
                    recordMap.put(id, line);
                }
            }
            // print duplicate records if found
            if (!duplicateRecords.isEmpty()) {
                System.out.println("Duplicate Records Found:");
                for (String record : duplicateRecords) {
                    System.out.println(record);
                }
            } else {
                System.out.println("No duplicate records found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
