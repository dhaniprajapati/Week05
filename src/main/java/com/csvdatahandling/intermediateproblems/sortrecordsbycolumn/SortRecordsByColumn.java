package com.csvdatahandling.intermediateproblems.sortrecordsbycolumn;

import java.io.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    // constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    // getter for salary
    public double getSalary() {
        return salary;
    }
    // override toString()
    @Override
    public String toString() {
        return "Employee { ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary + " }";
    }
}
public class SortRecordsByColumn {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\intermediateproblems\\sortrecordsbycolumn\\employee.csv";
        List<Employee> employeeList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            //run loop until the end
            while ((line = br.readLine()) != null) {
                // skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                //split from comma
                String[] data = line.split(",");
                //parse integer from string
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String department = data[2].trim();
                double salary = Double.parseDouble(data[3].trim());
                // create and add Employee object
                employeeList.add(new Employee(id, name, department, salary));
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        // sort employees by salary in descending order
        employeeList.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        // print the top 5 highest paid employees
        System.out.println("Top 5 Highest Paid Employees:");
        for (int i = 0; i < Math.min(5, employeeList.size()); i++) {
            System.out.println(employeeList.get(i));
        }
    }
}
