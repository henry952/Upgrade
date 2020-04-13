package com.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.common.PageBase;
import com.util.Misc;

public class CreateAccountPage extends PageBase{
	@FindBy(name = "username") private WebElement email;
	@FindBy(name = "password") private WebElement password;
	@FindBy(css = "label>div:nth-of-type(1)") private WebElement checkbox;
	@FindBy(css = "button[type='submit']") private WebElement button;
	
	public  CreateAccountPage(WebDriver dr) {
		this.driver = dr;
		logger = LogManager.getLogger(CreateAccountPage.class.getName());
		PageFactory.initElements(driver, this);
	}
	
	public void createAccount(String em, String pwd) {
		Misc.sendKeys(driver, email, 2, em);
//		email.sendKeys(em);
		password.sendKeys(pwd);
		Misc.sleepMiniSec(1000);
		checkbox.click();
		Misc.clickOn(driver, button, 2);
		Misc.waitForPageLoad(driver);
		String sourceCode = driver.getPageSource();
		Misc.assertTrue(logger, sourceCode!=null && !sourceCode.contains("We´re sorry, we were unable to approve you"), "Create Account");
	}
}
