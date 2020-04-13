package com.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.common.PageBase;
import com.util.Misc;

public class SINcheckRatePage extends PageBase{
	@FindBy(css = "input[name='borrowerSsn']") private WebElement sinBox;
	@FindBy(xpath="//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[1]/div/div/form/div[2]/button")private WebElement signIn;
	
	public SINcheckRatePage(WebDriver driver) {
		this.driver = driver;
		logger = LogManager.getLogger(SINcheckRatePage.class.getName());
		PageFactory.initElements(driver, this);
	}
	
	public void submitSINForRate(String sin) {
		System.out.println("&&& SIN=" + sin);
		Misc.sendKeys(driver, sinBox, 5, sin);
	//	sinBox.sendKeys(sin);
		Misc.clickOn(driver, signIn, 3);
//		signIn.click();
		Misc.waitForPageLoad(driver);
		String sourceCode = driver.getPageSource();
		Misc.assertTrue(logger, sourceCode!=null && !sourceCode.contains("We´re sorry, we were unable to approve you"), "Submit SIN for rate");
	}
}
