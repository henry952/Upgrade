package com.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.common.PageBase;
import com.util.Misc;

public class ContactInfoPage extends PageBase{
	@FindBy(name = "borrowerFirstName") private WebElement firstName;
	@FindBy(name = "borrowerLastName") private WebElement lastName;
	@FindBy(name = "borrowerStreet") private WebElement street;
	@FindBy(name = "borrowerCity") private WebElement city;
	@FindBy(name = "borrowerState") private WebElement state;
	@FindBy(name = "borrowerZipCode") private WebElement zipcode;
	@FindBy(name = "borrowerDateOfBirth") private WebElement bod;
	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[1]/div[1]/div/h2") private WebElement space;
	@FindBy(css = "button[type='submit']") private WebElement button;

	
	public  ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		logger = LogManager.getLogger(ContactInfoPage.class.getName());
		PageFactory.initElements(driver, this);
	}
	
	public void submitContactInfo(String[] contact) {
		firstName.sendKeys(contact[0]);
		lastName.sendKeys(contact[1]);
		street.sendKeys(contact[2]);
		Misc.sleepMiniSec(1000);
		space.click();
		city.sendKeys(contact[3]);
		state.sendKeys(contact[4]);
		zipcode.sendKeys(contact[5]);
		bod.sendKeys(contact[6]);
		button.click();
//		Misc.sleepMiniSec(1000);
		Misc.waitForPageLoad(driver);
		String sourceCode = driver.getPageSource();
		Misc.assertTrue(logger, sourceCode!=null && sourceCode.contains("How much money do you make in a year?"), "submit Contact Info");
	}
	
	public WebElement getContinueButton() {
		return button;
	}	
	
}
