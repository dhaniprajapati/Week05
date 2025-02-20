package com.handsonpractice.merge_json_files;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class MergeJSONFiles {
    public static void main(String[] args) {
        String file1= "src/main/java/com/handsonpractice/merge_json_files/file2.json";
        String file2= "src/main/java/com/handsonpractice/merge_json_files/file2.json";
        String mergedfile= "src/main/java/com/handsonpractice/merge_json_files/mergedfile.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read the first JSON file
            JsonNode jsonNode1 = mapper.readTree(new File(file1));
            // Read the second JSON file
            JsonNode jsonNode2 = mapper.readTree(new File(file2));
            // Merge the JSON nodes
            JsonNode mergedJsonNode = jsonNode1.deepCopy();
            mergedJsonNode = mapper.readerForUpdating(mergedJsonNode).readValue(jsonNode2);
            // Write the merged JSON to a new file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(mergedfile), mergedJsonNode);
            System.out.println("JSON files merged successfully!");

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
