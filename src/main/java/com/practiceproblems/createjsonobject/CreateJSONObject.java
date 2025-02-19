package com.practiceproblems.createjsonobject;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreateJSONObject {
    public static void main(String[] args) {
        //create json array and put subjects
        JSONArray subject= new JSONArray();
        subject.put("maths");
        subject.put("science");
        subject.put("english");
        //create json object
        JSONObject jsonObject = new JSONObject();
        //put details
        jsonObject.put("name", "Dhani");
        jsonObject.put("age", 22);
        jsonObject.put("subjects", subject);
        //print details
        System.out.println(jsonObject.toString());
    }
}
