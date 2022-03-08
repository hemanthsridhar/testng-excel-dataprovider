package com.github.hemanthsridhar;

import com.github.hemanthsridhar.lib.ExtUtils;

public class CSVUtils implements ExtUtils {

    private final CSVLibrary csvLibrary;

    public CSVUtils(String path){
        csvLibrary = new CSVLibrary(path);
    }

    public CSVUtils(String path, Boolean hasColumnNames){
        csvLibrary = new CSVLibrary(path, hasColumnNames);
    }

    @Override
    public String[][] parseData() throws Exception {
        return csvLibrary.parseCSVData();
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
