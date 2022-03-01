package com.github.hemanthsridhar.lib;

import java.io.IOException;

public interface ExtLib {

    String[][] parseCSVData() throws IOException;

    String[][] parseCSVData(Boolean hasColumnNames) throws IOException;

    String[][] parseExcelData() throws Exception;

    String[][] parseExcelData(String sheetName) throws Exception;

    String[] getColumnNames() throws Exception;

    int getNumberOfRows() throws Exception;

    String xlsxReadCell(String columnName, int rowNumber);

    String xlsxReadCell(int columnNumber, int rowNumber);

    String xlsReadCell(String columnName, int rowNumber);

    String xlsReadCell(int columnNumber, int rowNumber);

    int getNumberOfColumns();
}
