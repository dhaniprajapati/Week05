package com.csvdatahandling.intermediateproblems.modifycsvfile;

import java.io.*;

public class ModifyCSVFile {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\intermediateproblems\\modifycsvfile\\input.csv";
        String outputFile = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\intermediateproblems\\modifycsvfile\\output.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            //to track header row
            boolean isHeader = true;
            //run loop until the end
            while ((line = br.readLine()) != null) {
                // write header row
                if (isHeader) {
                    bw.write(line + "\n");
                    isHeader = false;
                    continue;
                }
                //split the lines by comma
                String[] data = line.split(",");
                String department = data[2];
                double salary = Double.parseDouble(data[3]);
                //check if department is same or not
                if (department.equalsIgnoreCase("IT")) {
                    // increase salary by 10%
                    salary = salary + (salary * 0.10);

                }
                // write updated record to new file
                bw.write(data[0] + "," + data[1] + "," + department + "," + String.format("%.2f", salary) + "\n");
            }
            System.out.println("Updated CSV file has been saved.");
        } catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }
    }
}
