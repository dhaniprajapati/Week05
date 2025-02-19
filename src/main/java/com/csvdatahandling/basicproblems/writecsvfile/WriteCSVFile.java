package com.csvdatahandling.basicproblems.writecsvfile;

import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\basicproblems\\writecsvfile\\employee.csv";

        //employee data
        String[] employees = {
                "101,Dhani Prajapati,IT,75000",
                "102,Akansha Pandit,HR,68000",
                "103,Rishav Rajput,Finance,72000",
                "104,Shruti Vishwakarma,Marketing,70000",
                "105,Mrunal Rangari,Operations,73000"
        };
        // create filewriter to write to the file
        try (FileWriter writer = new FileWriter(filePath)) {
            // write the header row
            writer.write("ID,Name,Department,Salary\n");
            // loop through employee records and write each record
            for (String employee : employees) {
                writer.write(employee + "\n");
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
