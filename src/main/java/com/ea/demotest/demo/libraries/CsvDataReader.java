package com.ea.demotest.demo.libraries;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvDataReader {
    
    public List<Map<String, String>> readTestData(String filePath) {
        List<Map<String, String>> testData = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = null;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(",");
                
                if (lineNumber == 1) {
                    // First line contains headers
                    headers = values;
                } else {
                    // Data rows
                    Map<String, String> rowData = new HashMap<>();
                    for (int i = 0; i < headers.length && i < values.length; i++) {
                        rowData.put(headers[i].trim(), values[i].trim());
                    }
                    testData.add(rowData);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + filePath, e);
        }
        
        return testData;
    }
}
