package com.github.hemanthsridhar.data;

import com.github.hemanthsridhar.ExtUtils;
import com.github.hemanthsridhar.lib.ExtLib;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class ExtDataProvider {

    /**
     * Excel workbook name with the TestNG test method names which will be fetched during runtime where the sheetname is by default Sheet1
     */
    @DataProvider
    public Object[][] excelSheetDataRead(Method methodName) throws Exception {
        ExtLib ext = new ExtUtils(getPathOfTheFile(methodName.getName() + ".xlsx"));
        return ext.parseExcelData();
    }

    /**
     * Sheet names with the TestNG test method names which will be fetched during runtime.
     */
    @DataProvider(parallel = true)
    public Object[][] singleExcelMultipleSheets(Method methodName) throws Exception {
        ExtLib ext = new ExtUtils(getPathOfTheFile("GoogleTestData.xlsx"));
        return ext.parseExcelData(methodName.getName());
    }

    /**
     * Read data from a CSV file.
     */
    @DataProvider(parallel = true)
    public Object[][] csvDataReadWithColumnHeaders() throws Exception {
        ExtLib ext = new ExtUtils(getPathOfTheFile("random_comma_seperated_value.csv"));
        return ext.parseCSVData(true);
    }


    @DataProvider(parallel = true)
    public Object[][] csvDataReadWithoutColumnHeaders() throws Exception {
        ExtLib ext = new ExtUtils(getPathOfTheFile("random_csv_no_headers.csv"));
        return ext.parseCSVData();
    }

    /**
     * Get file from src/test/resources
     */
    String getPathOfTheFile(String fileName) throws Exception {
        String path;
        try {
            path = getClass().getClassLoader().getResource(fileName).getPath();
        } catch (NullPointerException e) {
            throw new Exception("External TestNG dataprovider file not found");
        }
        return path;
    }
}
