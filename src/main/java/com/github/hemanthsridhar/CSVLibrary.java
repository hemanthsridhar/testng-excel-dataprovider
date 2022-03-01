package com.github.hemanthsridhar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class CSVLibrary {

    private String[] columnNames;
    private int numberOfRows;

    public String[][] parseCSVData(String filePath) throws IOException {

        File file = new File(filePath);
        BufferedReader tempBuffer = new BufferedReader(new FileReader(file));
        BufferedReader input = new BufferedReader(new FileReader(file));
        String firstRow = tempBuffer.readLine();
        int numberOfColumns = firstRow.split(",").length;
        int numberOfRows = setNumberOfRows(tempBuffer) + 1;

        String[][] data = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            String[] temp = input.readLine().split(",");
            for (int j = 0; j < numberOfColumns; j++) {
                try {
                    data[i][j] = temp[j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    data[i][j] = "";
                }
            }
        }

        input.close();
        tempBuffer.close();
        return data;
    }

    public String[][] parseCSVData(String filePath, boolean hasColumnNames) throws IOException {

        File file = new File(filePath);
        BufferedReader tempBuffer = new BufferedReader(new FileReader(file));
        BufferedReader input = new BufferedReader(new FileReader(file));
        String firstRow = tempBuffer.readLine();
        setColumnNames(firstRow);
        if (hasColumnNames) {
            input.readLine();
        }
        int numberOfColumns = firstRow.split(",").length;
        int numberOfRows = setNumberOfRows(tempBuffer);

        String[][] data = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            String[] temp = input.readLine().split(",");
            for (int j = 0; j < numberOfColumns; j++) {
                try {
                    data[i][j] = temp[j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    data[i][j] = "";
                }
            }
        }

        input.close();
        tempBuffer.close();
        return data;
    }

    private int setNumberOfRows(BufferedReader input) throws IOException {
        int count = 0;
        while ((input.readLine()) != null) {
            count++;
        }
        numberOfRows = count;
        return count;
    }

    public int getNumberOfRows() {
        return numberOfRows + 1;
    }

    public String[] getColumnNames() throws Exception {
        try {
            return columnNames;
        } catch (NullPointerException e) {
            throw new Exception("hasColumnNames parameter has not been set. Signature : parseCSVData(String filePath, boolean hasColumnNames) ");
        }
    }

    private void setColumnNames(String firstRow) {
        try {
            columnNames = firstRow.split(",");
        } catch (ArrayIndexOutOfBoundsException e) {
            columnNames[0] = firstRow;
        }
    }

    public int getNumberOfColumns() {
        return columnNames.length;
    }
}
