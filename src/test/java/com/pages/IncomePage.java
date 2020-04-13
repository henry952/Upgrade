package com.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.common.PageBase;
import com.util.Misc;

public class IncomePage extends PageBase{
	@FindBy(name = "borrowerIncome") private WebElement income;
	@FindBy(name = "borrowerAdditionalIncome") private WebElement addIncome;
	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[1]/div[1]/div/h2") private WebElement space;
	@FindBy(css = "button[type=submit]") private WebElement button;
	
	public  IncomePage(WebDriver driver) {
		this.driver = driver;
		logger = LogManager.getLogger(IncomePage.class.getName());
		PageFactory.initElements(driver, this);
	}
	
	public void submitIncome(String anuIncome, String aIncome) {
		income.sendKeys(anuIncome);
		addIncome.sendKeys(aIncome);
		Misc.sleepMiniSec(500);
		space.click();
		Misc.sleepMiniSec(500);
		button.click();
		Misc.waitForPageLoad(driver);
		String sourceCode = driver.getPageSource();
		Misc.assertTrue(logger, sourceCode!=null && sourceCode.contains("Last step before you get your rate"), "Submit Income");
	}
}
