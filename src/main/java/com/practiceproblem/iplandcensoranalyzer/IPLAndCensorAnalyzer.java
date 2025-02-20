package com.practiceproblem.iplandcensoranalyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class IPLMatch {
    public int match_id;
    public String team1;
    public String team2;
    public Map<String, Integer> score;
    public String winner;
    public String player_of_match;
    public IPLMatch(int match_id, String team1, String team2, Map<String, Integer> score, String winner, String player_of_match) {
        this.match_id = match_id;
        this.team1 = team1;
        this.team2 = team2;
        this.score = score;
        this.winner = winner;
        this.player_of_match = player_of_match;
    }
}

public class IPLAndCensorAnalyzer {
    public static void main(String[] args) {
        String jsonInputPath = "src/main/java/com/practiceproblem/iplandcensoranalyzer/ipl.json";
        String jsonOutputPath = "src/main/java/com/practiceproblem/iplandcensoranalyzer/output.json";
        String csvInputPath = "src/main/java/com/practiceproblem/iplandcensoranalyzer/ipl.csv";
        String csvOutputPath = "src/main/java/com/practiceproblem/iplandcensoranalyzer/output.csv";

        try {
            // Process JSON
            List<IPLMatch> matches = readJson(jsonInputPath);
            List<IPLMatch> censoredMatches = applyCensorship(matches);
            writeJson(jsonOutputPath, censoredMatches);
            // Process CSV
            List<String[]> csvData = readCsv(csvInputPath);
            List<String[]> censoredCsvData = censorCsvData(csvData);
            writeCsv(csvOutputPath, censoredCsvData);
            System.out.println("Censored JSON and CSV files generated successfully!");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    // Read JSON data
    public static List<IPLMatch> readJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<List<IPLMatch>>() {});
    }
    //Apply censorship rules
    public static List<IPLMatch> applyCensorship(List<IPLMatch> matches) {
        return matches.stream().map(match -> new IPLMatch(
                match.match_id,
                censorTeamName(match.team1),
                censorTeamName(match.team2),
                match.score.entrySet().stream()
                        .collect(Collectors.toMap(
                                e -> censorTeamName(e.getKey()), e -> e.getValue())),
                censorTeamName(match.winner),
                "REDACTED"
        )).collect(Collectors.toList());
    }
    //Write JSON data
    public static void writeJson(String filePath, List<IPLMatch> matches) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
        writer.writeValue(new File(filePath), matches);
    }
    //Read CSV data
    public static List<String[]> readCsv(String filePath) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        }
    }
    //Censor CSV data
    public static List<String[]> censorCsvData(List<String[]> csvData) {
        List<String[]> censoredData = new ArrayList<>();
        // Keep headers as it is
        censoredData.add(csvData.get(0));

        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            String[] censoredRow = {
                    row[0],  // match_i
                    censorTeamName(row[1]),
                    censorTeamName(row[2]),
                    row[3],
                    row[4],
                    censorTeamName(row[5]),
                    "REDACTED"
            };
            censoredData.add(censoredRow);
        }
        return censoredData;
    }
    //Write CSV data
    public static void writeCsv(String filePath, List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(data);
        }
    }
    //Mask team names
    public static String censorTeamName(String teamName) {
        if (teamName == null || teamName.isEmpty()) return teamName;
        String[] words = teamName.split(" ");
        return words[0] + " ***";
    }
}