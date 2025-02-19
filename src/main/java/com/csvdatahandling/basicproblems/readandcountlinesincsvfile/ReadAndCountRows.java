package com.csvdatahandling.basicproblems.readandcountlinesincsvfile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndCountRows {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\basicproblems\\readandcountlinesincsvfile\\employee.csv";
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            // read each line until end of file
            while ((line = br.readLine()) != null) {
                // skip the header row
                if (isHeader) {
                    isHeader = false;
                    // move to the next iteration
                    continue;
                }
                rowCount++;
            }
            // print the total number of records
            System.out.println("Total number of rows excluding header: " + rowCount);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
