package com.github.hemanthsridhar;

import com.github.hemanthsridhar.lib.ExtUtils;

import java.io.IOException;

public class ExcelUtils implements ExtUtils {

    private final String path;
    private ExcelLibrary excelLibrary;

    public ExcelUtils(String path) {
        this.path = path;
    }

    @Override
    public String[][] parseData() throws Exception {
        excelLibrary = new ExcelLibrary(path);
        return excelLibrary.readFromExcelDataForTestNGDataProvider();
    }

    @Override
    public String[][] parseData(String sheetName) throws Exception {
        excelLibrary = new ExcelLibrary(path, sheetName);
        return excelLibrary.readFromExcelDataForTestNGDataProviderWithSheetName();
    }

    @Override
    public String[][] parseData(Boolean hasColumnNames) {
        throw null;
    }

    @Override
    public String readCell(String columnName, int rowNumber) throws Exception {
        if(rowNumber <= 0){
            throw new Exception("row number cannot be 0");
        }
        if(path.endsWith(".xlsx")){
            return excelLibrary.xlsxReadCell(columnName,rowNumber-1);
        }
        else if(path.endsWith(".xls")){
            return excelLibrary.xlsxReadCell(columnName,rowNumber-1);
        }
        else{
            return null;
        }
    }

    @Override
    public String readCell(int columnNumber, int rowNumber) throws Exception {
        if(columnNumber <= 0 || rowNumber <= 0){
            throw new Exception("Column number or row number cannot be less than or equal to 0");
        }
        if(path.endsWith(".xlsx")){
            return excelLibrary.xlsxReadCell(columnNumber-1,rowNumber-1);
        }
        else if(path.endsWith(".xls")){
            return excelLibrary.xlsxReadCell(columnNumber-1,rowNumber-1);
        }
        else{
            return null;
        }
    }

    @Override
    public String[] getColumnNames() throws Exception {
        if(path.endsWith(".xlsx")){
            return excelLibrary.xlsxColumnNames();
        }
        else if(path.endsWith(".xls")){
            return excelLibrary.xlsColumnNames();
        }
        else{
            return null;
        }
    }

    @Override
    public int getNumberOfRows() {
        if(path.endsWith(".xlsx")){
            return excelLibrary.xlsxRowCount();
        }
        else if(path.endsWith(".xls")){
            return excelLibrary.xlsRowCount();
        }
        else{
            return 0;
        }
    }

    @Override
    public int getNumberOfColumns() {
        if(path.endsWith(".xlsx")){
            return excelLibrary.xlsxColumnCount();
        }
        else if(path.endsWith(".xls")){
            return excelLibrary.xlsColumnCount();
        }
        else{
            return 0;
        }
    }
}
