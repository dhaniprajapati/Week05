package com.practiceproblems.convert_jsonobjectinarray;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class JSONObjectIntoArray {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> myObjectList = new ArrayList<>();
        myObjectList.add(new Object("Dhani", 23));
        myObjectList.add(new Object("Kanchan", 22));
        try {
            // Convert list of Java objects to JSON array
            String jsonArrayString = objectMapper.writeValueAsString(myObjectList);
            System.out.println(jsonArrayString);
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }
    }
}
