package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.common.PageBase;
import com.util.Misc;

public class MyRatePage extends PageBase{
	@FindBy(css = "span[class='sc-kgoBCf blvwiR']") private WebElement loanAmount;
	@FindBy(css = "span[data-auto='defaultMonthlyPayment']") private WebElement monthlyPayment;
	@FindBy(css = "div[data-auto='defaultLoanTerm']") private WebElement term;
	@FindBy(css = "div[data-auto='defaultLoanInterestRate']") private WebElement interestRate;
	@FindBy(css = "div[data-auto='defaultMoreInfoAPR']>div") private WebElement apr;
	@FindBy(css = "div[data-auto='directPayMinimumAmount']") private WebElement miniPayment;
//	@FindBy(css = "label[class='header-nav__toggle']") private WebElement menu;
	@FindBy(css = "#root > div > main > div > header > div > label") private WebElement menu;
	@FindBy(linkText="Sign Out") private WebElement signout;
	                  
	public  MyRatePage(WebDriver driver) {
//		super(driver);
		this.driver = driver;
		logger = LogManager.getLogger(MyRatePage.class.getName());
		PageFactory.initElements(this.driver, this);	
	}
	
	public String[] retrieveResult() {
		String[] result = new String[5];
		result[0] = loanAmount.getText();
		result[1] = monthlyPayment.getText();
		result[2] = getMonthTerm();
		result[3] = getInterestRate();
		result[4] = getAPR();
		
		return result;
	}
	
	public String getMonthTerm() {
		String input = term.getText();
		int i = input.indexOf(' ');
		String word = input.substring(0, i);
		return word;
	}
	
	public String getInterestRate() {
		String input = interestRate.getText();
		int i = input.indexOf(' ');
		return input.substring(0, i);
	}
	
	public String getAPR() {
		String input = apr.getText();
		int i = input.indexOf(' ');
		return input.substring(0, i);
	}
	
	public String getMiniPay() {
		String input = miniPayment.getText();
		int i = input.indexOf(' ');
		return input.substring(0, i);
	}
	
	public void signOut(Logger logger, String expectedTitle) {
		logger.info("validating sign off");
//		Misc.sleepMiniSec(1000);
//		menu.click();
		Misc.clickOn(driver, menu, 2);
//		Misc.sleepMiniSec(500);
//		signout.click();
		Misc.clickOn(driver, signout, 2);
		Misc.waitForPageLoad(driver);
		String actualTile = driver.getTitle();
		Misc.assertTrue(logger, expectedTitle.equalsIgnoreCase(actualTile), "Sign Out");
	}
	
}
