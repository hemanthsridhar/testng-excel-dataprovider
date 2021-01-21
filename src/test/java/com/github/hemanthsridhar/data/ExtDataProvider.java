package com.github.hemanthsridhar.data;

import com.github.hemanthsridhar.CSVLibrary;
import com.github.hemanthsridhar.ExcelLibrary;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ExtDataProvider {

    /**
     * Excel workbook name with the TestNG test method names which will be fetched during runtime where the sheetname is by default Sheet1
     */
    @DataProvider
    public Object[][] excelSheetDataRead(Method methodName) throws Exception {
        String path = getPathOfTheFile(methodName.getName() + ".xlsx");
        return new ExcelLibrary(path).readFromExcelDataForTestNGDataProvider();
    }

    /**
     * Sheet names with the TestNG test method names which will be fetched during runtime.
     */
    @DataProvider(parallel = true)
    public Object[][] singleExcelMultipleSheets(Method methodName) throws Exception {
        String path = getPathOfTheFile("GoogleTestData.xlsx");
        return new ExcelLibrary(path, methodName.getName()).readFromExcelDataForTestNGDataProviderWithSheetName();
    }

    /**
     * Read data from a CSV file.
     */
    @DataProvider(parallel = true)
    public Object[][] csvDataRead() throws Exception {
        String path = getPathOfTheFile("random_comma_seperated_value.csv");
        return new CSVLibrary().parseCSVData(path);
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
