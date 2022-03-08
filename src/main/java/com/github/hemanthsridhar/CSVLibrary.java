package com.github.hemanthsridhar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CSVLibrary extends Randomizer {

    private final String path;
    private String[] columnNames;
    private int numberOfRows;
    private final Boolean hasColumnNames;
    private String[][] data;

    public CSVLibrary(String path) {
        this.path = path;
        this.hasColumnNames = false;
    }

    public CSVLibrary(String path, Boolean hasColumnNames) {
        this.path = path;
        this.hasColumnNames = hasColumnNames;
    }

    public String[][] parseCSVData() throws Exception {

        File file = new File(path);
        BufferedReader tempBuffer = new BufferedReader(new FileReader(file));
        BufferedReader input = new BufferedReader(new FileReader(file));
        String firstRow = tempBuffer.readLine();
        if (hasColumnNames) {
            setColumnNames(firstRow);
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
                    data[i][j] = checkIfRandomAndInvoke(data[i][j]);
                    if(data[i][j] == null){
                        data[i][j] = temp[j];
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    data[i][j] = "";
                }
            }
        }

        setData(data);

        input.close();
        tempBuffer.close();
        return data;

    }

    private void setData(String[][] data) {
        this.data = data;
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

    private void setColumnNames(String firstRow) throws Exception {
        try {
            columnNames = firstRow.split(",");
            if (checkDuplicateColumns(columnNames)) {
                throw new Exception("There are duplicate column names");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            columnNames[0] = firstRow;
        }
    }

    private boolean checkDuplicateColumns(String[] columnNames) {
        Set<String> tempSet = new HashSet<>();
        for (String columnName : columnNames) {
            if (!tempSet.add(columnName)) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfColumns() {
        return columnNames.length;
    }

    public String csvReadCell(int columnNumber, int rowNumber) throws Exception {
        if (columnNumber <= 0 || rowNumber <= 0) {
            throw new Exception("row number or column number cannot be less than or equal to 0");
        }
        return data[rowNumber - 1][columnNumber - 1];
    }

    public String csvReadCell(String columnName, int rowNumber) throws Exception {
        try {
            Map<String, Integer> hashMap = new HashMap<>();
            if (columnName == null) {
                throw new Exception("column name cannot be null");
            }
            if (rowNumber <= 0) {
                throw new Exception("row number cannot be 0");
            }
            String[] columnNames = getColumnNames();
            for (int col = 0; col < getNumberOfColumns(); col++) {
                hashMap.put(columnNames[col], col);
            }
            return data[rowNumber - 1][hashMap.get(columnName)];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("invalid row number");
        } catch (NullPointerException e) {
            throw new Exception("invalid column name or CSVLibrary(String path, Boolean hasColumnNames) constructor is not used.");
        }
    }
}
