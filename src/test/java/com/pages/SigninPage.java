package com.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.common.PageBase;
import com.util.Misc;

public class SigninPage  extends PageBase{
//	private final String url = "https://www.credify.tech/portal/login";
	@FindBy(name = "username") private WebElement username;
	@FindBy(name = "password") private WebElement password;
	@FindBy(xpath="//*[@id=\"root\"]/div/main/div/div/div/div/form/button")private WebElement signIn;
	
	public  SigninPage(WebDriver driver) {
//		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(SigninPage.class.getName());
	}
	
	public boolean signin(String userid, String pwd, String expectedTitle) {
		logger.info("validating sign in");
		boolean success = false;
		Misc.sleepMiniSec(1000);
		Misc.sendKeys(driver, username, 2, userid);
	//	username.sendKeys(userid);
		Misc.sendKeys(driver, password, 2, pwd);
//		password.sendKeys(pwd);
		signIn.click();

		Misc.sleepMiniSec(2000);
		
		String actualTitle = driver.getTitle();
		if(expectedTitle.equalsIgnoreCase(actualTitle))
			success = true;
		return success;
	}
}
