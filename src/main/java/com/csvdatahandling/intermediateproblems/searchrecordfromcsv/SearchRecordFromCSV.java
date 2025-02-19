package com.csvdatahandling.intermediateproblems.searchrecordfromcsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchRecordFromCSV {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\intermediateproblems\\searchrecordfromcsv\\employee.csv";
        Scanner scanner = new Scanner(System.in);
        //  taking user input
        System.out.print("Enter employee name to search: ");
        // read input and remove extra spaces
        String searchName = scanner.nextLine().trim();
        //to track if employee is found
        boolean found = false;
        // read the file using bufferedreader
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //to track the header row
            boolean isHeader = true;
            // read each line until end of file
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                // split the line by commas
                String[] data = line.split(",");
                String employeeId = data[0];
                String name = data[1].trim();
                String department = data[2];
                String salary = data[3];
                // check if name matches
                if (name.equalsIgnoreCase(searchName)) {
                    System.out.println("Employee Found.");
                    System.out.println("ID: " + employeeId);
                    System.out.println("Department: " + department);
                    System.out.println("Salary: " + salary);
                    found = true;
                    break;
                }
            }
            // if no match found
            if (!found) {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        scanner.close();
    }
}

