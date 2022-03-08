package com.github.hemanthsridhar.lib;

public interface ExtUtils  {

    String[][] parseData() throws Exception;

    String readCell(String columnName, int rowNumber) throws Exception;

    String readCell(int columnNumber, int rowNumber) throws Exception;

    String[] getColumnNames() throws Exception;

    int getNumberOfRows() throws Exception;

    int getNumberOfColumns();
}
