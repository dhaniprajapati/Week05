package com.practiceproblems.parse_json;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.stream.Collectors;

class Person {
    private String name;
    private int age;
    private String city;

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"age\":" + age +
                ", \"city\":\"" + city + '\"' +
                '}';
    }
}

public class ParseJSON {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String fileNmae= "src/main/java/com/practiceproblems/parse_json/file.json";
        try {
            // Read JSON file into a List of Person objects
            List<Person> people = objectMapper.readValue(
                    new File(fileNmae),
                    new TypeReference<List<Person>>() {});
            // Filter records where age > 25
            List<Person> filteredPeople = people.stream()
                    .filter(person -> person.getAge() > 25)
                    .collect(Collectors.toList());
            // Print filtered records
            System.out.println("Filtered records (age > 25):");
            filteredPeople.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }
}
