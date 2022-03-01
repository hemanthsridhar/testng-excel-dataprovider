package com.github.hemanthsridhar.tests;

import com.github.hemanthsridhar.ExtUtils;
import com.github.hemanthsridhar.data.ExtDataProvider;
import com.github.hemanthsridhar.lib.ExtLib;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtTest {

    @Test(dataProvider = "excelSheetDataRead", dataProviderClass = ExtDataProvider.class)
    public void excelReadFromASingleWorkbook(String param1, String param2) {
        System.out.print(param1 + ",");
        System.out.print(param2);
        System.out.println();
    }

    @Test(dataProvider = "singleExcelMultipleSheets", dataProviderClass = ExtDataProvider.class)
    public void loginTest2(String param1, String param2, String param3, String param4) {
        System.out.print(param1 + ",");
        System.out.print(param2 + ",");
        System.out.print(param3 + ",");
        System.out.print(param4);
        System.out.println();
    }

    @Test(dataProvider = "csvDataReadWithColumnHeaders", dataProviderClass = ExtDataProvider.class)
    public void csvRead(String param1, String param2, String param3) {
        System.out.print(param1 + ",");
        System.out.print(param2 + ",");
        System.out.print(param3);
        System.out.println();
    }

    @Test(dataProvider = "csvDataReadWithoutColumnHeaders", dataProviderClass = ExtDataProvider.class)
    public void csvDataReadWithoutColumnHeaders(String param1, String param2, String param3) {
        System.out.print(param1 + ",");
        System.out.print(param2 + ",");
        System.out.print(param3);
        System.out.println();
    }

    @Test
    public void csvGetColumnNames() throws Exception {
        ExtLib ext = new ExtUtils("src/test/resources/random_comma_seperated_value.csv");
        ext.parseCSVData(true);
        ext.getColumnNames();
        String[] columnNames = ext.getColumnNames();
        String[] expectedColumnNames = {"param1", "param2", "param3"};
        Assert.assertEquals(columnNames, expectedColumnNames);
    }

    @Test
    public void csvGetColumnNamesWithoutHasColumnNamesInParseData() throws Exception {
        ExtLib ext = new ExtUtils("src/test/resources/random_comma_seperated_value.csv");
        ext.parseCSVData();
        ext.getColumnNames();
        String[] columnNames = ext.getColumnNames();
        Assert.assertNull(columnNames);
    }


    @Test
    public void csvGetNumberOfRowsWithColumNames() throws Exception {
        ExtLib ext = new ExtUtils("src/test/resources/random_comma_seperated_value.csv");
        ext.parseCSVData(true);
        Assert.assertEquals(ext.getNumberOfRows(), 4);
    }

    @Test
    public void csvGetNumberOfRowsWithoutColumnNames() throws Exception {
        ExtLib ext = new ExtUtils("src/test/resources/random_csv_no_headers.csv");
        ext.parseCSVData();
        Assert.assertEquals(ext.getNumberOfRows(), 2);
    }

    @Test
    public void xlsxGetNumberOfRows() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtLib ext = new ExtUtils(path);
        ext.parseExcelData("loginTest2");
        Assert.assertEquals(ext.getNumberOfRows(), 5);
    }

    @Test
    public void xlsxGetNumberOfColumns() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtLib ext = new ExtUtils(path);
        ext.parseExcelData("loginTest2");
        Assert.assertEquals(ext.getNumberOfColumns(), 4);
    }

    @Test
    public void xlsxColumnNames() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtLib ext = new ExtUtils(path);
        ext.parseExcelData("loginTest2");
        String[] expectedColumnNames = {"Test Case Id", "User Name", "Password", "Expected Name of the user"};
        Assert.assertEquals(ext.getColumnNames(), expectedColumnNames);
    }

    @Test
    public void readIndividualDataFromXLSX() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtLib ext = new ExtUtils(path);
        ext.parseExcelData("loginTest2");

        System.out.println(ext.xlsxReadCell("Password", 2));

        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext.xlsxReadCell(0, 2));

        ext.parseExcelData("loginTest1");

        System.out.println(ext.xlsxReadCell("Password", 1));
        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext.xlsxReadCell(3, 1));
    }

    @Test(enabled = false)
    public void readIndividualDataFromXLS() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xls").getPath();
        ExtLib ext = new ExtUtils(path);
        ext.parseExcelData("loginTest2");
        System.out.println(ext.xlsReadCell("Password", 2));

        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext.xlsReadCell(0, 2));

        ext.parseExcelData("loginTest1");

        System.out.println(ext.xlsReadCell("Password", 1));
        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext.xlsReadCell(3, 1));
    }
}
