package com.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.data.DriverType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	public static WebDriver getDriver( DriverType browser) {
		WebDriver driver = null;
		switch (browser) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			driver = new ChromeDriver(chromeOptions);
//			ITestContext context = new ITestContext();
//			context.setAttribute("WebDriver", driver);
			break;
		case EDGE:
			
			break;
		case FIREFOX:
			
			break;
		case IE:
//			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\java\\com\\finastra\\express\\resource\\IEDriverServer.exe");
//			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//			ieOptions.disableNativeEvents();
//			ieOptions.requireWindowFocus();
//			driver = new InternetExplorerDriver(ieOptions);
//			driver.manage().window().maximize();
			break;
			
		default:
		}
		
		return driver;
	}
	
	public static void closeDriver(WebDriver driver) {
		if(driver != null)
			driver.close();
		driver = null;
	}
}
