package com.shaffan.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static Object[][] getExcelData(String fileName, String sheetName) {
	    List<Object[]> dataList = new ArrayList<Object[]>(); // Dynamic list to store ONLY valid rows
	    Workbook book = null;

	    try {
	        FileInputStream fis = new FileInputStream("src/test/resources/testdata/" + fileName);
	        book = new XSSFWorkbook(fis);
	        XSSFSheet sheet = (XSSFSheet) book.getSheet(sheetName);

	        int rowCount = sheet.getLastRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();
	        
	        DataFormatter formatter = new DataFormatter();

	        // Start loop
	        for (int i = 1; i <= rowCount; i++) {
	            XSSFRow row = sheet.getRow(i);
	            
	            // 1. Skip completely null rows
	            if (row == null) continue;

	            // 2. Check the first cell (Username). If empty, it's a Ghost Row.
	            String firstCell = formatter.formatCellValue(row.getCell(0));
	            if (firstCell == null || firstCell.trim().isEmpty()) {
	                continue; // SKIP IT!
	            }

	            // 3. If valid, capture the data
	            Object[] rowData = new Object[colCount];
	            for (int j = 0; j < colCount; j++) {
	                rowData[j] = formatter.formatCellValue(row.getCell(j));
	            }
	            dataList.add(rowData);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (book != null) book.close();
	        } catch (IOException e) { e.printStackTrace(); }
	    }

	    // Convert the clean list back to Object[][]
	    return dataList.toArray(new Object[0][0]);
	}
}
