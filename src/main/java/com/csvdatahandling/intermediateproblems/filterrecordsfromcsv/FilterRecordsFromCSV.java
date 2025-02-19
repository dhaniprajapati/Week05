package com.csvdatahandling.intermediateproblems.filterrecordsfromcsv;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterRecordsFromCSV {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\intermediateproblems\\filterrecordsfromcsv\\Student.csv";
        int score = 80;
        // read the file using bufferedreader
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            System.out.println("Students who scored more than 80 marks:");
            // read each line until end of file
            while ((line = br.readLine()) != null) {
                // skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                // split the line by commas
                String[] data = line.split(",");
                String studentId = data[0];
                String name = data[1];
                // get student marks and convert to integer
                int marks = Integer.parseInt(data[3]);
                // check if marks are greater than score
                if (marks > score) {
                    System.out.println("ID: " + studentId + ", Name: " + name + ", Marks: " + marks);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}

