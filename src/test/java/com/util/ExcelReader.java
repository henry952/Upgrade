package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.testtasks.util.ExcelReadable;

public class ExcelReader implements ExcelReadable{
	private File file;
//	private FileInputStream inputStream;
	private Workbook workbook = null;
	DataFormatter formatter = new DataFormatter();
	
	public ExcelReader(String filePath) {  
		file = new File(filePath);
		try {
			FileInputStream inputStream = new FileInputStream(file);
	    String fileExtensionName = filePath.substring(filePath.indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    	workbook = new XSSFWorkbook(inputStream);
	    }
	    else if(fileExtensionName.equals(".xls")){
	    	workbook = new HSSFWorkbook(inputStream);
	    }
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	public static void main(String...strings) throws IOException{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\data\\Verification_Data.xlsx";
		ExcelReader reader = new ExcelReader(filePath);
		(reader.getSheetNames()).forEach(System.out::println);
		String sheetName = "Page_Label";
		String key = "Upgrade Card";
		String value = reader.getAValueByKey(sheetName, key);
		System.out.println(" ### key = " + key + "; value ="+ value);
//		System.out.println(Arrays.toString(reader.getARandomRow(sheetName))); // get a random row and print
//		List<String> list = reader.getColumnTitles(sheetName);
//		list.forEach(System.out::println);
//		List<ArrayList<String>> lt = reader.getAllContents("Credential");
//		lt.forEach(System.out::println);
//		System.out.println("row size of negtive is " + reader.getRowSize("Negtive"));
//		System.out.println("Column size of negtive is " + reader.getColumnSize("Negtive"));
//		List<String> list2 = reader.getColumnValues("Negtive", "Income");
//		list2.forEach(System.out::println);
//		List<String> list3 = reader.getCorrespondingValues("Negtive", "Password", "Qwertyu1", "Zipcode");
//		list3.forEach(System.out::println);
	}
	
	@Override
	public ArrayList<String> getSheetNames() {
		ArrayList<String> list = new ArrayList<String>();
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
             list.add(sheet.getSheetName());
        }
		return list;
	}

	@Override
	public ArrayList<String> getColumnTitles(String sheetName) {
		ArrayList<String> list = new ArrayList<String>();
		 Sheet sheet = workbook.getSheet(sheetName);
		 Row row = sheet.getRow(0);
		 Iterator<Cell> cellIterator = row.cellIterator();
         while (cellIterator.hasNext()) {
             Cell cell = cellIterator.next();
             list.add( cell.getStringCellValue());
         }
		return list;
	}

	@Override
	public int getRowSize(String sheetName) {
		Sheet sheet = workbook.getSheet(sheetName);		
		return sheet.getLastRowNum(); // 
	}
	
	@Override
	public int getColumnSize(String sheetName) {
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet.getRow(0).getLastCellNum(); //Gets the index of the last cell contained in this row PLUS ONE. start from 1
	}

	@Override
	public String getAValueByKey(String sheetName, String key) { //in this sheet, there are only columns - key and value
		String value = "";
		Sheet sheet = workbook.getSheet(sheetName);
		 Iterator<Row> rowIterator = sheet.rowIterator();
		 while(rowIterator.hasNext()) {
			 Row row = rowIterator.next();
			 if(key.equalsIgnoreCase(row.getCell(0).getStringCellValue())) {
				 value = row.getCell(1).getStringCellValue();
				 break;
			 }
				 
		 }
		return value;
	}

	@Override
	public String[] getARandomRow(String sheetName) {
		Sheet sheet = workbook.getSheet(sheetName);
		int rowSize = sheet.getLastRowNum();
		int columnSize = sheet.getRow(0).getLastCellNum();
		int rand = 0;
		if(rowSize>=2) // title raw + at least one data row
			rand = (int)(Math.random() * rowSize + 1);
		if(rand == 1) rand++; //ensure it doesn't point to title row 
		Row row = sheet.getRow(rand);
		String[] data = new String[columnSize];
		DataFormatter df = new DataFormatter();
		for(int i=0;i<columnSize; i++) {
			data[i] = df.formatCellValue(row.getCell(i));
		}
       
		return data;
	}

	@Override
	public String[] getRowByKey(String sheetName, String key) { //here, key is always at first column
		Sheet sheet = workbook.getSheet(sheetName);
		 Iterator<Row> rowIterator = sheet.rowIterator();
		 ArrayList<String> desirousRow = new ArrayList<>();
		 while(rowIterator.hasNext()) {
			 Row row = rowIterator.next();
			 if(key.equalsIgnoreCase(formatter.formatCellValue(row.getCell(0)))) {
				 Iterator<Cell> cellIterator = row.cellIterator();
				 while (cellIterator.hasNext()) {
					 Cell cell = cellIterator.next();                              
	//            	 rowArray.add( cell.getStringCellValue());
					 desirousRow.add( formatter.formatCellValue(cell));
				 }
			 }      
		 }
		return desirousRow.toArray(new String[0]);
	}
	
	@Override
	public List<ArrayList<String>> getAllContents(String sheetName) {
		List<ArrayList<String>> list = new ArrayList<>();
		 Sheet sheet = workbook.getSheet(sheetName);
//		 Iterator<Row> rowIterator = sheet.rowIterator();
//		 while(rowIterator.hasNext()) {
//			 Row row = rowIterator.next();
		 int rowNum = getRowSize(sheetName);
		 for(int i=2; i<=rowNum; i++ ) {
			 ArrayList<String> rowArray = new ArrayList<>();
			 Row row = sheet.getRow(i);
			 Iterator<Cell> cellIterator = row.cellIterator();
	        while (cellIterator.hasNext()) {
	            Cell cell = cellIterator.next();                              
	            rowArray.add( cell.getStringCellValue());
	        }
	        rowArray.forEach(System.out::println);
	        list.add(rowArray);
		 }
		return list;
	}

	@Override
	public List<String[]> getPartialContents(String sheetName, int numRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getColumnValues(String sheetName, String columnTitle) {
		ArrayList<String> list = new ArrayList<String>();
		Sheet sheet = workbook.getSheet(sheetName);
	//	Row row = sheet.getRow(0);
		int columnIndex = getTitleIndex(sheet, columnTitle);
		
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    DataFormatter df = new DataFormatter();
	    //Create a loop over all the rows of excel file to read it
	    for (int i = 2; i < rowCount+1; i++) {
	        Row row = sheet.getRow(i);
	  //      String value = row.getCell(columnIndex).getStringCellValue();	
	        String value = df.formatCellValue(row.getCell(columnIndex));
	        list.add(value);       
	    } 
		return list;
	}

	public int getTitleIndex(Sheet sheet, String columnName) {
	    Row titlerow = sheet.getRow(0);
	    int caseIndex = 0;
        for (int i = 0; i < titlerow.getLastCellNum(); i++) {
        	if(columnName.equals(titlerow.getCell(i).getStringCellValue())) {
        		caseIndex = i;
        		break;
        	}      
        }
        return caseIndex;
	}
	
	@Override
	public boolean searchValue(String sheetName, String columnTitle, String keyword) {
		// TODO Auto-generated method stub
		return false;
	}

	//from specific sheet, find value - "key" from row - "keyTitle" and get corresponding value in row = "valueTitle"
	@Override
	public ArrayList<String> getCorrespondingValues(String sheetName, String keyTitle, String key, String valueTitle) {
		ArrayList<String> list = new ArrayList<String>();
		Sheet sheet = workbook.getSheet(sheetName);
		int keyColumnIndex = getTitleIndex(sheet, keyTitle);
		int valueColumnIndex = getTitleIndex(sheet, valueTitle);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it
		DataFormatter df = new DataFormatter();
	    for (int i = 1; i < rowCount+1; i++) {
	        Row row = sheet.getRow(i);
//	        String keyValue = row.getCell(keyColumnIndex).getStringCellValue();	 
	        String keyValue = df.formatCellValue(row.getCell(keyColumnIndex));
	        if(keyValue.equalsIgnoreCase(key)) { 
	        	list.add(df.formatCellValue(row.getCell(valueColumnIndex)));
	        }
	    } 
		return list;
	}

}
