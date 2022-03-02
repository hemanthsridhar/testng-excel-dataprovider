package com.github.hemanthsridhar;

import com.github.hemanthsridhar.lib.ExtUtils;

import java.io.IOException;

public class CSVUtils implements ExtUtils {

    private final CSVLibrary csvLibrary;

    public CSVUtils(String path){
        csvLibrary = new CSVLibrary(path);
    }

    @Override
    public String[][] parseData() throws IOException {
        return csvLibrary.parseCSVData();
    }

    @Override
    public String[][] parseData(String sheetName) throws Exception {
        throw new Exception("CSV file format does not support multiple sheets. You can save only only one sheet per file in CSV file format.");
    }

    @Override
    public String[][] parseData(Boolean hasColumnNames) throws Exception {
        return csvLibrary.parseCSVData(hasColumnNames);
    }

    @Override
    public String readCell(String columnName, int rowNumber) throws Exception {
        return csvLibrary.csvReadCell(columnName,rowNumber);
    }

    @Override
    public String readCell(int columnNumber, int rowNumber) throws Exception {
        return csvLibrary.csvReadCell(columnNumber,rowNumber);
    }

    @Override
    public String[] getColumnNames() throws Exception {
        return csvLibrary.getColumnNames();
    }

    @Override
    public int getNumberOfRows() {
        return csvLibrary.getNumberOfRows();
    }

    @Override
    public int getNumberOfColumns() {
        return csvLibrary.getNumberOfColumns();
    }
}
