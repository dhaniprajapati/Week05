package com.csvdatahandling.basicproblems.readcsvfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\basicproblems\\readcsvfile\\student.csv";
        //try and catch block
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //to track the header row
            boolean isHeader = true;
            //run loop to read each line until its not null
            while ((line = br.readLine()) != null) {
                //skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                // split the line by commas and store it seperately
                String[] data = line.split(",");
                String studentId = data[0];
                String name = data[1];
                String age = data[2];
                String marks = data[3];
                System.out.println("ID: " + studentId + ", Name: " + name + ", Age: " + age + ", Marks: " + marks);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
