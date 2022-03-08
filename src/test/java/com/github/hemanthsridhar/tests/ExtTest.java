package com.github.hemanthsridhar.tests;

import com.github.hemanthsridhar.CSVUtils;
import com.github.hemanthsridhar.ExcelUtils;
import com.github.hemanthsridhar.data.ExtDataProvider;
import com.github.hemanthsridhar.lib.ExtUtils;
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
        ExtUtils ext = new CSVUtils("src/test/resources/random_comma_seperated_value.csv", true);
        ext.parseData();
        ext.getColumnNames();
        String[] columnNames = ext.getColumnNames();
        String[] expectedColumnNames = {"param1", "param2", "param3"};
        Assert.assertEquals(columnNames, expectedColumnNames);
    }

    @Test
    public void csvGetColumnNamesWithoutHasColumnNamesInParseData() throws Exception {
        ExtUtils ext = new CSVUtils("src/test/resources/random_comma_seperated_value.csv");
        ext.parseData();
        ext.getColumnNames();
        String[] columnNames = ext.getColumnNames();
        Assert.assertNull(columnNames);
    }

    @Test
    public void csvGetCellData() throws Exception {
        ExtUtils ext = new CSVUtils("src/test/resources/random_comma_seperated_value.csv");
        ext.parseData();
        Assert.assertEquals(ext.readCell(1,2),"hi");
    }

    @Test
    public void csvGetCellDataColName() throws Exception {
        ExtUtils ext = new CSVUtils("src/test/resources/random_comma_seperated_value.csv",true);
        ext.parseData();
        Assert.assertEquals(ext.readCell("param3",2),"helloSECOND");
    }


    @Test
    public void csvGetNumberOfRowsWithColumNames() throws Exception {
        ExtUtils ext = new CSVUtils("src/test/resources/random_comma_seperated_value.csv", true);
        ext.parseData();
        Assert.assertEquals(ext.getNumberOfRows(), 4);
    }

    @Test
    public void csvGetNumberOfRowsWithoutColumnNames() throws Exception {
        ExtUtils ext = new CSVUtils("src/test/resources/random_csv_no_headers.csv");
        ext.parseData();
        Assert.assertEquals(ext.getNumberOfRows(), 2);
    }

    @Test
    public void randomCsvData() throws Exception {
        ExtUtils ext = new CSVUtils("src/test/resources/random_data.csv",true);
        ext.parseData();
        for(int i = 1 ; i < ext.getNumberOfRows(); i++) {
            System.out.println(ext.readCell("param1", i));
            Assert.assertNotNull(ext.readCell("param1", i));
            Assert.assertFalse(ext.readCell("param1", i).isEmpty());
        }
    }

    @Test
    public void randomXLSXData() throws Exception {
        String path = getClass().getClassLoader().getResource("RandomTestData.xlsx").getPath();
        ExtUtils ext = new ExcelUtils(path);
        ext.parseData();
        for(int i = 1 ; i < ext.getNumberOfRows(); i++) {
            System.out.println(ext.readCell("param1", i));
            Assert.assertNotNull(ext.readCell("param1", i));
            Assert.assertFalse(ext.readCell("param1", i).isEmpty());
        }
    }

    @Test
    public void xlsxGetNumberOfRows() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtUtils ext = new ExcelUtils(path,"loginTest2");
        ext.parseData();
        Assert.assertEquals(ext.getNumberOfRows(), 5);
    }

    @Test
    public void xlsxGetNumberOfColumns() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtUtils ext = new ExcelUtils(path,"loginTest2");
        ext.parseData();
        Assert.assertEquals(ext.getNumberOfColumns(), 4);
    }

    @Test
    public void xlsxColumnNames() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtUtils ext = new ExcelUtils(path,"loginTest2");
        ext.parseData();
        String[] expectedColumnNames = {"Test Case Id", "User Name", "Password", "Expected Name of the user"};
        Assert.assertEquals(ext.getColumnNames(), expectedColumnNames);
    }

    @Test
    public void readIndividualDataFromXLSX() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xlsx").getPath();
        ExtUtils ext = new ExcelUtils(path,"loginTest2");
        ext.parseData();

        System.out.println(ext.readCell("Password", 2));

        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext.readCell(1, 3));

        ExtUtils ext2 = new ExcelUtils(path,"loginTest1");
        ext2.parseData();
        System.out.println(ext2.readCell("Password", 1));
        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext2.readCell(4, 2));
    }

    @Test(enabled = false)
    public void readIndividualDataFromXLS() throws Exception {
        String path = getClass().getClassLoader().getResource("GoogleTestData.xls").getPath();
        ExtUtils ext = new ExcelUtils(path,"loginTest2");
        ext.parseData();
        System.out.println(ext.readCell("Password", 2));

        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext.readCell(1, 2));

        ExtUtils ext2 = new ExcelUtils(path,"loginTest1");

        ext2.parseData();

        System.out.println(ext.readCell("Password", 1));
        //for row number and column number reading, make sure you provider 1 value less for the respective row number and the column number.
        System.out.println(ext.readCell(4, 1));
    }
}
