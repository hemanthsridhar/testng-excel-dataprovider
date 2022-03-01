package com.github.hemanthsridhar;

import com.github.hemanthsridhar.lib.ExtLib;

import java.io.IOException;

public class ExtUtils implements ExtLib {

    private final String path;
    private final CSVLibrary csvLibrary;
    private ExcelLibrary excelLibrary;

    public ExtUtils(String path) {
        this.path = path;
        csvLibrary = new CSVLibrary();
    }

    @Override
    public String[][] parseCSVData() throws IOException {
        return csvLibrary.parseCSVData(path);
    }

    @Override
    public String[][] parseCSVData(Boolean hasColumnNames) throws IOException {
        return csvLibrary.parseCSVData(path, hasColumnNames);
    }

    @Override
    public String[][] parseExcelData() throws Exception {
        excelLibrary = new ExcelLibrary(path);
        return excelLibrary.readFromExcelDataForTestNGDataProvider();
    }

    @Override
    public String[][] parseExcelData(String sheetName) throws Exception {
        excelLibrary = new ExcelLibrary(path, sheetName);
        return excelLibrary.readFromExcelDataForTestNGDataProviderWithSheetName();
    }

    @Override
    public String[] getColumnNames() throws Exception {
        String[] columnNames = {};
        if (path.endsWith(".csv")) {
            columnNames = csvLibrary.getColumnNames();
        } else if (path.endsWith(".xlsx")) {
            columnNames = excelLibrary.xlsxColumnNames();
        } else if (path.endsWith(".xls")) {
            columnNames = excelLibrary.xlsColumnNames();
        }
        return columnNames;
    }

    @Override
    public int getNumberOfRows() throws Exception {
        int numberOfRows = 0;
        if (path.endsWith(".csv")) {
            numberOfRows = csvLibrary.getNumberOfRows();
        } else if (path.endsWith(".xlsx")) {
            numberOfRows = excelLibrary.xlsxRowCount();
        } else if (path.endsWith(".xls")) {
            numberOfRows = excelLibrary.xlsRowCount();
        }
        return numberOfRows;
    }

    @Override
    public String xlsxReadCell(String columnName, int rowNumber) {
        return excelLibrary.xlsxReadCell(columnName, rowNumber);
    }

    @Override
    public String xlsxReadCell(int columnNumber, int rowNumber) {
        return excelLibrary.xlsxReadCell(columnNumber, rowNumber);
    }

    @Override
    public String xlsReadCell(String columnName, int rowNumber) {
        return excelLibrary.xlsReadCell(columnName, rowNumber);
    }

    @Override
    public String xlsReadCell(int columnNumber, int rowNumber) {
        return excelLibrary.xlsReadCell(columnNumber, rowNumber);
    }

    @Override
    public int getNumberOfColumns() {
        int numberOfColumns = 0;
        if (path.endsWith(".xlsx")) {
            numberOfColumns = excelLibrary.xlsxColumnCount();
        } else if (path.endsWith(".xls")) {
            numberOfColumns = excelLibrary.xlsColumnCount();
        } else if (path.endsWith(".csv")) {
            numberOfColumns = csvLibrary.getNumberOfColumns();
        }
        return numberOfColumns;
    }
}
