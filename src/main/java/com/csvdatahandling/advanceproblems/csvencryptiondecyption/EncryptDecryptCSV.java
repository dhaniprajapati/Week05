package com.csvdatahandling.advanceproblems.csvencryptiondecyption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.util.*;
import java.util.Base64;

public class EncryptDecryptCSV {
    private static final String ALGORITHM = "AES";
    //encryption key
    private static SecretKey secretKey;
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\csvencryptiondecyption\\employees.csv";
        String encryptedCsvFile = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\csvencryptiondecyption\\encrypted.csv";
        String decryptedCsvFile = "C:\\Users\\praja\\OneDrive\\Desktop\\Week05\\Day01\\src\\main\\java\\com\\csvdatahandling\\advanceproblems\\csvencryptiondecyption\\decrypted.csv";
        // Generate a secret key for encryption/decryption
        generateSecretKey();
        //employee data
        List<String[]> employees = Arrays.asList(
                new String[]{"ID", "Name", "Department", "Salary", "Email"}, // Header
                new String[]{"101", "Dhani", "IT", "75000", "dhani@gmail.com"},
                new String[]{"102", "Rani", "HR", "60000", "rani@gmail.com"},
                new String[]{"103", "Vani", "Finance", "90000", "vani@gmail.com"}
        );
        // Encrypt & Write to CSV
        encryptAndWriteCSV(employees, encryptedCsvFile);
        System.out.println("Encrypted data saved ");
        // Read & Decrypt CSV
        decryptAndReadCSV(encryptedCsvFile, decryptedCsvFile);
        System.out.println(" Decrypted data saved ");
    }
    // Generate Secret Key
    private static void generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(128);
            secretKey = keyGen.generateKey();
        } catch (Exception e) {
            System.out.println(" Error generating key: " + e.getMessage());
        }
    }
    // Encrypt and Write CSV
    private static void encryptAndWriteCSV(List<String[]> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                // Encrypt Salary & Email
                if (!row[0].equals("ID")) {
                    row[3] = encrypt(row[3]);
                    row[4] = encrypt(row[4]);
                }
                writer.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing encrypted CSV: " + e.getMessage());
        }
    }
    // Read and Decrypt CSV
    private static void decryptAndReadCSV(String inputFile, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                // Skip header row
                if (!isHeader) {
                    row[3] = decrypt(row[3]);
                    row[4] = decrypt(row[4]);
                }
                isHeader = false;
                writer.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading encrypted CSV: " + e.getMessage());
        }
    }
    // Encrypt data using algorithm
    private static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            return "Error encrypting";
        }
    }

    // Decrypt data using algorithm
    private static String decrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(data);
            return new String(cipher.doFinal(decodedBytes));
        } catch (Exception e) {
            return "Error decrypting";
        }
    }
}

