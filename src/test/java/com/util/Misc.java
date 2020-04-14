package com.util;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Misc {
	public static void sleepMiniSec(int miniSec) {
		try {
			Thread.sleep(miniSec);
		
		}catch(InterruptedException ie) {
			ie.printStackTrace(System.out);
		}
	}
	
	public static void assertTrue(Logger logger, boolean flag, String testName) {
		try {
			Assert.assertTrue(flag);
			logger.warn(testName + " test success!");
		}catch(Error e) {
			logger.error(testName + " test fail!");
		}
	}
	
	public static void sendKeys(WebDriver driver1, WebElement element, int timeout, String value){
		new WebDriverWait(driver1, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
	
	public static void clickOn(WebDriver driver1, WebElement element, int timeout){
		new WebDriverWait(driver1, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public static void waitForPageLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	    	ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	    };
	    WebDriverWait wait = new WebDriverWait(driver, 10); //maximum 10 seconds
	    wait.until(pageLoadCondition);
	}
	
	public static void printStringArray(String[] strArr) {
		System.out.println(Arrays.toString(strArr));
	}
}
