package com.github.hemanthsridhar.tests;

import com.github.hemanthsridhar.ExcelLibrary;
import com.github.hemanthsridhar.data.ExtDataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExtTest {

    @Test(dataProvider = "excelSheetDataRead", dataProviderClass = ExtDataProvider.class)
    public void excelReadFromASingleWorkbook(String param1, String param2) {
        System.out.print(param1 + ",");
        System.out.print(param2);
        System.out.println();
    }

    @Test(dataProvider = "singleExcelMultipleSheets", dataProviderClass = ExtDataProvider.class)
    public void loginTest2(String param1,String param2,String param3,String param4) {
        System.out.print(param1 + ",");
        System.out.print(param2 + ",");
        System.out.print(param3 + ",");
        System.out.print(param4);
        System.out.println();
    }

    @Test(dataProvider = "csvDataRead", dataProviderClass = ExtDataProvider.class)
    public void csvRead(String param1, String param2, String param3) {
        System.out.print(param1 + ",");
        System.out.print(param2 + ",");
        System.out.print(param3);
        System.out.println();
    }


    @Test
    public void readIndividualDataFromXLSX() throws IOException {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExcelLibrary excel = new ExcelLibrary(path, "loginTest2");

        System.out.println(excel.xlsxReadCell("Password", 2));

        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(excel.xlsxReadCell(0, 2));

        ExcelLibrary excel1 = new ExcelLibrary(path, "loginTest1");

        System.out.println(excel1.xlsxReadCell("Password", 1));
        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(excel1.xlsxReadCell(3, 1));
    }

    @Test(enabled = false)
    public void readIndividualDataFromXLS() throws IOException {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xls").getPath();
        ExcelLibrary excel = new ExcelLibrary(path, "loginTest2");

        System.out.println(excel.xlsReadCell("Password", 2));

        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(excel.xlsReadCell(0, 2));

        ExcelLibrary excel1 = new ExcelLibrary(path, "loginTest1");

        System.out.println(excel1.xlsReadCell("Password", 1));
        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(excel1.xlsReadCell(3, 1));
    }
}
