package com.handsonpractice.csvtojson;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVToJSON {

    public static void main(String[] args) {
        String csvFilePath = "src/main/java/com/handsonpractice/csvtojson/file.csv";
        String jsonOutput = convertCSVToJSON(csvFilePath);
        System.out.println(jsonOutput);
    }

    public static String convertCSVToJSON(String filePath) {
        JSONArray jsonArray = new JSONArray();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> csvData = csvReader.readAll();
            String[] headers = csvData.get(0);

            for (int i = 1; i < csvData.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], csvData.get(i)[j]);
                }
                jsonArray.put(jsonObject);
            }
        } catch (IOException | CsvException e) {
            e.getMessage();
        }
        return jsonArray.toString(4);
    }
}
