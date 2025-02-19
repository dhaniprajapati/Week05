package com.csvdatahandling.advanceproblems.validatecsvdata;

import java.io.*;
import java.util.regex.*;

public class ValidateCSVData {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\intermediateproblems\\validatecsvdata\\contacts.csv";
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String phoneRegex = "^[0-9]{10}$";
        // compile email regex
        Pattern emailPattern = Pattern.compile(emailRegex);
        // compile phone regex
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //to track header row
            boolean isHeader = true;
            //run loop until the end
            while ((line = br.readLine()) != null) {
                // write header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                //split the lines by comma
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String email = data[2].trim();
                String phone = data[3].trim();
                // match regex
                Matcher emailMatcher = emailPattern.matcher(email);
                Matcher phoneMatcher = phonePattern.matcher(phone);
                // check if email is invalid
                if (!emailMatcher.matches()) {
                    System.out.println("Invalid Email: " + email + " (ID: " + id + ", Name: " + name + ")");
                }
                // check if phone number is invalid
                if (!phoneMatcher.matches()) {
                    System.out.println("Invalid Phone Number: " + phone + " (ID: " + id + ", Name: " + name + ")");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
