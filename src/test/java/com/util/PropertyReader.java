package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	Properties obj; 
	File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\data\\Global.properties");
	
	public PropertyReader() {
		try {
		obj = new Properties();
		FileInputStream objfile = new FileInputStream(file);
		obj.load(objfile);
		} catch(Exception e) {}; 
	}
	
	public String getValue(String key) {
		return obj.getProperty(key);
	}
}
