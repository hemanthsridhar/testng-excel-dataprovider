package com.github.hemanthsridhar.data;

import com.github.hemanthsridhar.CSVUtils;
import com.github.hemanthsridhar.ExcelUtils;
import com.github.hemanthsridhar.lib.ExtUtils;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class ExtDataProvider {

    /**
     * Excel workbook name with the TestNG test method names which will be fetched during runtime where the sheetname is by default Sheet1
     */
    @DataProvider
    public Object[][] excelSheetDataRead(Method methodName) throws Exception {
        ExtUtils ext = new ExcelUtils(getPathOfTheFile(methodName.getName() + ".xlsx"));
        return ext.parseData();
    }

    /**
     * Sheet names with the TestNG test method names which will be fetched during runtime.
     */
    @DataProvider(parallel = true)
    public Object[][] singleExcelMultipleSheets(Method methodName) throws Exception {
        ExtUtils ext = new ExcelUtils(getPathOfTheFile("GoogleTestData.xlsx"));
        return ext.parseData(methodName.getName());
    }

    /**
     * Read data from a CSV file.
     */
    @DataProvider(parallel = true)
    public Object[][] csvDataReadWithColumnHeaders() throws Exception {
        ExtUtils ext = new CSVUtils(getPathOfTheFile("random_comma_seperated_value.csv"));
        return ext.parseData(true);
    }


    @DataProvider(parallel = true)
    public Object[][] csvDataReadWithoutColumnHeaders() throws Exception {
        ExtUtils ext = new CSVUtils(getPathOfTheFile("random_csv_no_headers.csv"));
        return ext.parseData();
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
