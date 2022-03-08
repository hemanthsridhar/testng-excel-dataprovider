package com.github.hemanthsridhar;

import com.github.hemanthsridhar.lib.ExtUtils;

public class ExcelUtils implements ExtUtils {

    private final String path;
    private ExcelLibrary excelLibrary;
    private final String sheetName;

    public ExcelUtils(String path) {
        this.path = path;
        this.sheetName = "Sheet1";
    }

    public ExcelUtils(String path, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
    }

    @Override
    public String[][] parseData() throws Exception {
        excelLibrary = new ExcelLibrary(path, sheetName);
        return excelLibrary.readExcelData();
    }

    @Override
    public String readCell(String columnName, int rowNumber) throws Exception {
        if(rowNumber <= 0){
            throw new Exception("row number cannot be 0");
        }
        if(path.endsWith(".xlsx")){
            return excelLibrary.xlsxReadCell(columnName,rowNumber);
        }
        else if(path.endsWith(".xls")){
            return excelLibrary.xlsxReadCell(columnName,rowNumber);
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
