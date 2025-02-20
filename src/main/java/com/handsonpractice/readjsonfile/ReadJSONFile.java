package com.handsonpractice.readjsonfile;

import java.nio.file.*;
import org.json.JSONObject;

public class ReadJSONFile {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day02\\src\\main\\java\\com\\handsonpractice\\readjsonfile\\input.json";
        //Create a Path object from the file path
        Path path = Paths.get(filePath);
        //Read all bytes from the file into a byte array
        byte[] fileBytes = Files.readAllBytes(path);
        //Convert the byte array into a String
        String content = new String(fileBytes);
        //Create a JSONObject from the String content
        JSONObject jsonObject = new JSONObject(content);
        //Iterate over JSON keys and print key-value pairs
        for (String key : jsonObject.keySet()) {
            System.out.println(key + ": " + jsonObject.get(key));
        }
    }
}
