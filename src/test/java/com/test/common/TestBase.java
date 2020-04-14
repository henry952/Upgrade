package com.test.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

import com.data.DriverType;
import com.util.DriverManager;
import com.util.ExcelReader;
import com.util.PropertyReader;

public class TestBase {
	public static WebDriver driver;
	protected PropertyReader property;
	protected ExcelReader tDataBook ; 
	protected ExcelReader vDataBook ;
	protected Logger logger;
	
	public TestBase(DriverType type ){
		if(driver == null)
			driver = DriverManager.getDriver(type); 
		property = new PropertyReader();
		String testDataPath = property.getValue("testDataPath"); // this path should be passed in construct
		String verificationDataPath = property.getValue("verificationDataPath");
		tDataBook =  new ExcelReader(System.getProperty("user.dir") + testDataPath);
		vDataBook = new ExcelReader(System.getProperty("user.dir") + verificationDataPath);
	}
	
    public void takeScreenshot(String name){
    	try {
	         //Convert web driver object to TakeScreenshot
	        TakesScreenshot scrShot =((TakesScreenshot)driver);
	        //Call getScreenshotAs method to create image file
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	        String filepath = System.getProperty("user.dir") + property.getValue("screenshotPath");
	        String filename =  filepath + name +  new SimpleDateFormat("_MMdd_HHmmss'.png'").format(new Date()); //0130_083816.png
	            //Move image file to new destination
	        File DestFile=new File( filename);
	                //Copy file at destination
	        FileUtils.copyFile(SrcFile, DestFile);
    	}catch(IOException e){
    		e.printStackTrace(); 
    	}
    }

	@AfterClass
	public void closeBrowser() {
		DriverManager.closeDriver(driver);
	}
}
