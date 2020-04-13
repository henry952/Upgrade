package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.pages.common.PageBase;
import com.util.Misc;

public class PersonalLoanRequestPage extends PageBase{
	@FindBy(xpath="//div[@id='hero-section']/div/div/div[1]/div/h1") private WebElement pageLabel;
	@FindBy(name = "desiredAmount") private WebElement amount;
	@FindBy(css = "#pl-loan-purpose-select") private WebElement purpose;
	@FindBy(css = "button[data-auto='CheckYourRate']") private WebElement checkButton;
	@FindBy(xpath="//*[@id=\"hero-section\"]/div/div/div[2]/div/div/form/div/div/div[1]/div/div/div[2]") private WebElement errorMsg;
	@FindBy(xpath="//*[@id=\"hero-section\"]/div/div/div[1]/div/h1") private WebElement textMsg;
	
	public PersonalLoanRequestPage(WebDriver driver) {
		this.driver = driver;
		logger = LogManager.getLogger(PersonalLoanRequestPage.class.getName());
		PageFactory.initElements(driver, this);
	}
	
	public void validatePageLabel(String expectedLabel) {
		Misc.sleepMiniSec(1000);
		String actualLabel = pageLabel.getText();
		Misc.assertTrue(logger, expectedLabel.equalsIgnoreCase(actualLabel), "Validate Personal Loan Page label");		
	}
	
	public void validateAmountInput(String[] amt, String purpose) { // to simplify the process, only first purpose will be seleted
		boolean allExpected = true;
		for(int i=0; i<amt.length; i++) {
			int inputAmount = Integer.parseInt(amt[i]);
			Misc.sleepMiniSec(1000);
			if(inputAmount<1000) {
				submitRequest(amt[i], purpose);
				if(!errorMsg.isDisplayed()) {
					allExpected = false;
					break;
				}
			}else if(inputAmount>=1000 && inputAmount<=50000) {
				submitRequest(amt[i], purpose);
				Misc.sleepMiniSec(1000);
				String url = driver.getCurrentUrl();
				if(url.equalsIgnoreCase("https://www.upgrade.com/personal-loans/")) {
					allExpected = false;
					break;
				}
				driver.navigate().back();
				
			}else if(inputAmount>=50000) {
				amount.sendKeys(amt[i]);
				Misc.sleepMiniSec(1000);

				submitRequest(amt[i], purpose);
				Misc.sleepMiniSec(1000);
				String url = driver.getCurrentUrl();
				if(!url.equalsIgnoreCase("https://www.upgrade.com/personal-loans/")) {
					allExpected = false;
					break;
				}
			}
			Misc.sleepMiniSec(1000);
		}
		Misc.assertTrue(logger, allExpected, "Validate Amount Input");
	}
	
	public void submitRequest(String amt, String purposeName) {
		Misc.sleepMiniSec(1000);
		Misc.sendKeys(driver, amount, 2, amt);
		selectPurposeByVisibleText(purposeName);
		Misc.clickOn(driver, checkButton, 2);
		Misc.waitForPageLoad(driver);
		String sourceCode = driver.getPageSource();
		Misc.assertTrue(logger, sourceCode!=null && sourceCode.contains("Let's get started with some basic information"), "submit Request");
	}
	
	public void selectPurposeByIndex(int index) {
		Select s = new Select(purpose);
		s.selectByIndex(index);
	}
	
	public void selectPurposeByVisibleText(String name) {
		Select s = new Select(purpose);
		s.selectByVisibleText(name);
	}
	
	public ArrayList<String> getPurposeItems() {
		ArrayList<String> items = new ArrayList<>();
		Select dropdown = new Select(purpose);
	    //Get all options
	    List<WebElement> dd = dropdown.getOptions();
	    // Loop to print one by one
	    for (int j = 0; j < dd.size(); j++) {
	        items.add(dd.get(j).getText());
	    }
		return items;
	}
}
