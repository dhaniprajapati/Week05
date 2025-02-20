package com.handsonpractice.jsonfromdatabase;

import java.sql.*;
import com.google.gson.*;

public class JSONFromDatabase {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/employee_details";
        String jdbcUser = "root@localhost";
        String jdbcPassword = "Prajapatidhani@06";
        String query = "SELECT * FROM employee_details";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            JsonArray jsonArray = new JsonArray();

            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    jsonObject.addProperty(columnName, columnValue);
                }

                jsonArray.add(jsonObject);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(jsonArray);
            System.out.println(jsonString);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
