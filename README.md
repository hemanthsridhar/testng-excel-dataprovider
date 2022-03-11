# testng-excel-dataprovider
TestNG data provider library to read from csv and excel(xlsx and xls)

```java
<dependency>
  <groupId>com.github.hemanthsridhar</groupId>
  <artifactId>testng-ext-dataprovider</artifactId>
  <version>14.2.0</version>
</dependency>
```



Assuming that the CSV is defined as below with name random_comma_seperated_value.csv

```java
param1,param2,param3
hi,bye,hello
bye,hi,hello
hello,hi,bye
```

```java
@DataProvider(parallel=true)
public Object[][] csvDataRead() throws Exception {
  String path = "src/test/resources/random_comma_seperated_value.csv";
  ExtUtils ext = new CSVUtils(path);
  return ext.parseData();
}
```

If it has column names, we need to skip the first row read.

```java
@DataProvider(parallel=true)
public Object[][] csvDataRead() throws Exception {
  String path = "src/test/resources/random_comma_seperated_value.csv";
  ExtUtils ext = new CSVUtils(path, true);
  return ext.parseData();
}
```

**XLSX TestNG Data Provider**

```java
@DataProvider
public Object[][] excelSheetDataRead() throws Exception {
    ExtUtils ext = new ExcelUtils("src/test/resources/workbookName.xlsx");
    /*
      Using custom_sheet_name
      ExtUtils ext = new ExcelUtils("src/test/resources/workbookName.xlsx","custom_sheet_name");
     */
    return ext.parseData();
}
```


Checkout the WIKI section for further features.
