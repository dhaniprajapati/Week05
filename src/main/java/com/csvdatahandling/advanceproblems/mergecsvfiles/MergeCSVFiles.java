package com.csvdatahandling.advanceproblems.mergecsvfiles;

import java.io.*;
import java.util.*;

class Student {
    int id;
    String name;
    int age;
    double marks;
    String grade;
    // constructor to initialize student object
    public Student(int id, String name, int age, double marks, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.grade = grade;
    }
    // override toString() method
    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + marks + "," + grade;
    }
}
public class MergeCSVFiles {
    public static void main(String[] args) {
        String file1 = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\mergecsvfiles\\file1.csv";
        String file2 = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\mergecsvfiles\\file2.csv";
        String outputFile = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\mergecsvfiles\\mergedfile.csv";
        // hashmap to store student details using ID as key
        Map<Integer, Student> studentMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line;
            // flag to skip the header row
            boolean isHeader = true;
            // loop through each line in the file
            while ((line = br.readLine()) != null) {
                // skip the first row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                // split the line using a comma
                String[] data = line.split(",");
                // parse ID from string to integer
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                // parse age from string to integer
                int age = Integer.parseInt(data[2].trim());
                // add student to hashmap with default marks & grade
                studentMap.put(id, new Student(id, name, age, 0, "N/A"));
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line;
            boolean isHeader = true;
            // loop through each line in the file
            while ((line = br.readLine()) != null) {
                // skip the first row (header)
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                // split the line using a comma as a delimiter
                String[] data = line.split(",");
                // parse ID from string to integer
                int id = Integer.parseInt(data[0].trim());
                // parse marks from string to double
                double marks = Double.parseDouble(data[1].trim());
                String grade = data[2].trim();
                // update existing student object if ID matches
                if (studentMap.containsKey(id)) {
                    Student student = studentMap.get(id);
                    studentMap.put(id, new Student(id, student.name, student.age, marks, grade));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading:" +e.getMessage());
        }
        // write merged data to a new CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            // write header row
            bw.write("ID,Name,Age,Marks,Grade\n");
            // write each student data to file
            for (Student student : studentMap.values()) {
                bw.write(student.toString() + "\n");
            }

            System.out.println("Merged CSV files");
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
