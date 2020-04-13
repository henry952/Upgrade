package com.testtasks.util;

import java.util.ArrayList;
import java.util.List;

public interface ExcelReadable {
	public ArrayList<String> getSheetNames();
	public ArrayList<String> getColumnTitles(String sheetName);
	public int getRowSize(String sheetName);
	public int getColumnSize(String sheetName);
	public String getAValueByKey(String sheetName, String key); //in this sheet, there are only columns - key and value
	public String[] getARandomRow(String sheetName);
	public String[] getRowByKey(String sheetName, String key);//here, key is always in first column
	public List<ArrayList<String>> getAllContents(String sheetName);
	public List<String[]> getPartialContents(String sheetName, int numRows);
	public ArrayList<String> getColumnValues(String sheetName, String columnTitle);
	public boolean searchValue(String sheetName, String columnTitle, String keyword);
	public ArrayList<String> getCorrespondingValues(String sheetName, String keyTitle, String key, String valueTitle);

}
