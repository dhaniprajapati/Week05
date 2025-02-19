package com.csvdatahandling.advanceproblems.csvreportfordatabase;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class GenarateReport {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/employee_details";
        String dbUser = "root@localhost";
        String dbPassword = "Prajapatidhani@06";
        String csvFile = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\csvreportfordatabase\\employee.csv";
        String query = "SELECT id, name, department, salary FROM employees";
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             FileWriter fileWriter = new FileWriter(csvFile)) {
            fileWriter.append("Employee ID,Name,Department,Salary\n");
            while (resultSet.next()) {
                fileWriter.append(resultSet.getInt("id") + ",");
                fileWriter.append(resultSet.getString("name") + ",");
                fileWriter.append(resultSet.getString("department") + ",");
                fileWriter.append(resultSet.getDouble("salary") + "\n");
            }
            System.out.println("CSV file generated successfully: " + csvFile);
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
