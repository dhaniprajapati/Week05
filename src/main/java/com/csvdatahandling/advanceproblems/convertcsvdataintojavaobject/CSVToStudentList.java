package com.csvdatahandling.advanceproblems.convertcsvdataintojavaobject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Student {
    private int id;
    private String name;
    private int age;
    private double marks;
    // constructor
    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }
    // override toString() method
    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Age: " + age + ", Marks: " + marks;
    }
}
public class CSVToStudentList {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\intermediateproblems\\convertcsvdataintojavaobject\\student.csv";
        List<Student> studentList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //to track header file
            boolean isHeader = true;
            //loop to read file until end
            while ((line = br.readLine()) != null) {
                //skip the header file
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                //split from comma
                String[] data = line.split(",");
                //parse string into integer
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                double marks = Double.parseDouble(data[3].trim());
                //create and add student object
                studentList.add(new Student(id, name, age, marks));
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        //loop to print details
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
