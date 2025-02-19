package com.csvdatahandling.advanceproblems.readlargecsv;

import java.io.*;

public class ReadLargeCSV {
    public static void main(String[] args) {

        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\readlargecsv\\customers-100000.csv";
        // variable to track total records
        int totalRecords = 0;
        // read file using BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //to skip the header row
            boolean isHeader = true;
            // number of lines to process at a time
            int batchSize = 100;
            // track lines in the current batch
            int batchCount = 0;
            // loop through each line in the file
            while ((line = br.readLine()) != null) {
                // skip the first row (header)
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                totalRecords++;
                batchCount++;
                // when batch size reaches 100 print progress and reset counter
                if (batchCount == batchSize) {
                    System.out.println("Processed " + totalRecords + " records so far.");
                    batchCount = 0;
                }
            }
            // print total records processed
            System.out.println("Total records processed: " + totalRecords);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
