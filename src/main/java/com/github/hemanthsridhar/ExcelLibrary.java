package com.github.hemanthsridhar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

class ExcelLibrary {


    private final Hashtable hash = new Hashtable();
    private final String excelSheetPath;
    //xls
    private HSSFSheet hssfwrksheet;
    private HSSFWorkbook hssfwrkbook = null;
    //xlsx
    private XSSFSheet xssfwrksheet;
    private XSSFWorkbook xssfwrkbook = null;
    private String sheet;

    public ExcelLibrary(String excelSheetPath) throws IOException {
        this.excelSheetPath = excelSheetPath;
        if (excelSheetPath.endsWith(".xls")) {//Initialize
            File file = new File(excelSheetPath);
            FileInputStream fis = new FileInputStream(file);
            hssfwrkbook = new HSSFWorkbook(fis);
            hssfwrksheet = hssfwrkbook.getSheet("Sheet1");
            int colNum = hssfwrksheet.getRow(0).getLastCellNum();
            hssfColumnDictionary(colNum);
        } else if (excelSheetPath.endsWith(".xlsx")) {
            File file = new File(excelSheetPath);
            FileInputStream fis = new FileInputStream(file);
            xssfwrkbook = new XSSFWorkbook(fis);
            xssfwrksheet = xssfwrkbook.getSheet("Sheet1");
            int colNum = xssfwrksheet.getRow(0).getLastCellNum();
            xssfColumnDictionary(colNum);
        } else {
            System.out.println("Format is invalid. The file format not supported by the custom library");
        }
    }


    public ExcelLibrary(String excelSheetPath, String sheet) throws IOException {
        this.excelSheetPath = excelSheetPath;
        this.sheet = sheet;
        //Initialize
        if (excelSheetPath.endsWith(".xls")) {
            File file = new File(excelSheetPath);
            FileInputStream fis = new FileInputStream(file);
            hssfwrkbook = new HSSFWorkbook(fis);
            hssfwrksheet = hssfwrkbook.getSheet(sheet);
            int colNum = hssfwrksheet.getRow(0).getLastCellNum();
            hssfColumnDictionary(colNum);
        } else if (excelSheetPath.endsWith(".xlsx")) {
            File file = new File(excelSheetPath);
            FileInputStream fis = new FileInputStream(file);
            xssfwrkbook = new XSSFWorkbook(fis);
            xssfwrksheet = xssfwrkbook.getSheet(sheet);
            int colNum = xssfwrksheet.getRow(0).getLastCellNum();
            xssfColumnDictionary(colNum);

        } else {
            System.out.println("Format is invalid. The file format not supported by the custom library");
        }
    }

    public int xlsxColumnCount() {
        return xssfwrksheet.getRow(0).getLastCellNum();
    }


    public int xlsColumnCount() {
        return hssfwrksheet.getRow(0).getLastCellNum();
    }

    //Returns the Number of Rows for .xls
    public int xlsRowCount() {
        int rowNum = hssfwrksheet.getLastRowNum() + 1;
        return rowNum;
    }

    //Returns the number of rows for .xlsx
    public int xlsxRowCount() {
        int rowNum = xssfwrksheet.getLastRowNum() + 1;
        return rowNum;
    }

    //Returns the Cell value by taking row and Column values as argument
    //for .xls
    public String xlsReadCell(int colNum, int rowNum) {
        String[][] data = new String[rowNum + 1][colNum + 1];
        HSSFRow row = hssfwrksheet.getRow(rowNum);
        HSSFCell cell = row.getCell(colNum);
        String val = hssfcellToString(cell);
        data[rowNum][colNum] = val;
        if (val == null) {
            System.out.println("data empty");
        }
        return val;

    }

    //for .xlsx
    public String xlsxReadCell(int colNum, int rowNum) {
        String[][] data = new String[rowNum + 1][colNum + 1];
        XSSFRow row = xssfwrksheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum);
        String val = xssfcellToString(cell);
        data[rowNum][colNum] = val;
        if (val == null) {
            System.out.println("data empty");
        }
        return val;

    }

    //for xls
    public String xlsReadCell(String colName, int rowNumber) {
        return xlsReadCell(GetCell(colName), rowNumber);
    }


    //for xlsx
    public String xlsxReadCell(String colName, int rowNumber) {
        return xlsxReadCell(GetCell(colName), rowNumber);
    }


    //Create Column Dictionary to hold all the Column Names
    //for xls
    @SuppressWarnings("unchecked")
    private void hssfColumnDictionary(int colNum) {

        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
        for (int col = 0; col < colNum; col++) {
            hash.put(xlsReadCell(col, 0), col);
        }
    }

    //for xlsx
    @SuppressWarnings("unchecked")
    private void xssfColumnDictionary(int colNum) {

        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
        for (int col = 0; col < colNum; col++) {
            hash.put(xlsxReadCell(col, 0), col);
        }
    }

    //Read Column Names
    private int GetCell(String colName) {
        try {
            int value;
            value = ((Integer) hash.get(colName)).intValue();
            return value;
        } catch (NullPointerException e) {
            return (0);

        }
    }

    //for xls
    private String hssfcellToString(HSSFCell cell) {
        CellType type;
        Object result;
        type = cell.getCellType();
        switch (type) {
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;

            case STRING:
                result = cell.getStringCellValue();
                break;

            case FORMULA:
                result = cell.getCellFormula();
                break;

            case BLANK:
                result = "";
                break;

            case BOOLEAN:
                result = cell.getBooleanCellValue();
                break;

            case ERROR:
                result = cell.getErrorCellValue();
                break;
            default:
                throw new RuntimeException("no support for this cell");
        }
        return result.toString();
    }

    //for xlsx
    private String xssfcellToString(XSSFCell cell) {
        CellType type;
        Object result;
        type = cell.getCellType();
        switch (type) {
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;

            case STRING:
                result = cell.getStringCellValue();
                break;

            case FORMULA:
                result = cell.getCellFormula();
                break;

            case BLANK:
                result = "";
                break;

            case BOOLEAN:
                result = cell.getBooleanCellValue();
                break;

            case ERROR:
                result = cell.getErrorCellValue();
                break;

            default:
                throw new RuntimeException("no support for this cell");
        }
        return result.toString();
    }


    //for testng dataprovider
    public String[][] readExcelData() throws Exception {
        File file = new File(excelSheetPath);
        FileInputStream fis = new FileInputStream(file);
        xssfwrkbook = new XSSFWorkbook(fis);
        xssfwrksheet = xssfwrkbook.getSheet(sheet);
        int numberOfColumns = xlsxColumnCount();
        int numberOfRows = xlsxRowCount();
        String[][] data = new String[numberOfRows - 1][numberOfColumns];
        for (int i = 1; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                XSSFRow row = xssfwrksheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String val = xssfcellToString(cell);
                data[i - 1][j] = val;
                if (val == null) {
                    System.out.println("data empty");
                }

            }
        }
        return data;
    }

    public String[] xlsxColumnNames() throws Exception {
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < xlsxColumnCount(); i++) {
            columnNames.add(xlsxReadCell(i, 0));
        }
        if(checkDuplicateColumns(columnNames)){
            throw new Exception("There are duplicate column names");
        }
        return columnNames.toArray(new String[0]);
    }

    private boolean checkDuplicateColumns(List<String> columnNames) {
        Set<String> tempSet = new HashSet<>();
        for(String columnName : columnNames){
            if(!tempSet.add(columnName)){
                return true;
            }
        }
        return false;
    }

    public String[] xlsColumnNames() throws Exception {
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < xlsColumnCount(); i++) {
            columnNames.add(xlsReadCell(i, 0));
        }
        if(checkDuplicateColumns(columnNames)){
            throw new Exception("There are duplicate column names");
        }
        return columnNames.toArray(new String[0]);
    }
}

