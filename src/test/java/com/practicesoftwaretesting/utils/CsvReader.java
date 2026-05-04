package com.practicesoftwaretesting.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

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
