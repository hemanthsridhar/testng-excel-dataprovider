package com.github.hemanthsridhar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVLibrary {

    public String[][] parseCSVData(String fileName) throws IOException {

        File file = new File(fileName);
        BufferedReader tempBuffer = new BufferedReader(new FileReader(file));
        BufferedReader input = new BufferedReader(new FileReader(file));
        String firstRow = tempBuffer.readLine();
        input.readLine();
        int numberOfColumns = firstRow.split(",").length;
        int numberOfRows = getRowSize(tempBuffer);

        String[][] data = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            String[] temp = input.readLine().split(",");
            if (numberOfColumns >= 0) System.arraycopy(temp, 0, data[i], 0, numberOfColumns);
        }

        input.close();
        tempBuffer.close();
        return data;
    }

    private int getRowSize(BufferedReader input) throws IOException {
        int count = 0;
        while ((input.readLine()) != null) {
            count++;
        }
        return count;
    }
}
