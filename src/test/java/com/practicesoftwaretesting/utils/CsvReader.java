package com.practicesoftwaretesting.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading CSV test data.
 */
public class CsvReader {

    /**
     * Reads test data from CSV file.
     *
     * @param filePath path to csv file
     * @return two-dimensional array for TestNG DataProvider
     * @throws IOException when file cannot be read
     */
    public static Object[][] getCsvData(String filePath) throws IOException {
        List<Object[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            data.add(values);
        }
        br.close();
        return data.toArray(new Object[0][]);
    }
}
