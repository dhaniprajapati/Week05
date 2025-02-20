package com.practiceproblems.merge_json_object;

import org.json.JSONObject;

public class MergeTwoJSONObjects {
    public static void main(String[] args) {
        // First JSON Object
        JSONObject json1 = new JSONObject();
        json1.put("name", "Dhani");
        json1.put("age", 22);
        // Second JSON Object
        JSONObject json2 = new JSONObject();
        json2.put("city", "Bhopal");
        json2.put("Designation", "ASE");
        // Merge json2 into json1
        for (String key : json2.keySet()) {
            json1.put(key, json2.get(key));
        }
        System.out.println(json1.toString());
    }
}
