package com.pages.common;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.Misc;


public class HomePage extends PageBase{
	@FindBy(linkText="Personal Loans") private WebElement menuPLoan;
	@FindBy(linkText="Upgrade Card") private WebElement MenuUCard;
	@FindBy(linkText="Credit Health") private WebElement MenuCHealth;
	@FindBy(linkText="Help Center") private WebElement MenuHCenter;
	@FindBy(linkText="Sign In") private WebElement MenuSignin;
	@FindBy(xpath="//*[@id=\"home-page-top\"]/div[1]/div/h2") private WebElement pageLabel;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(HomePage.class.getName());
	}
	public void openHomePage(String homeUrl, String expectedTitle) {		
		logger.info("Navigating to Upgrade home page");
		driver.get(homeUrl);

		Misc.waitForPageLoad(driver);
//		Misc.sleepMiniSec(500);
		driver.manage().window().maximize();
		String actualTitle = driver.getTitle();
		Misc.assertTrue(logger, expectedTitle.equalsIgnoreCase(actualTitle), "Navigate Upgrade Home");
	}
	
	public void gotoModule(String menuLabel, String expectedTitle) { //go to
		logger.info("Going to module - " + menuLabel);
		switch(menuLabel) 
        { 
            case "Personal Loans": 
                menuPLoan.click();
                 break; 
            case "Upgrade Card": 
                 MenuUCard.click();
                break; 
            case "Credit Health": 
            	Misc.clickOn(driver, MenuCHealth, 2);
//                 MenuCHealth.click();
                break; 
            case "Help Center": 
                MenuHCenter.click();
                break; 
            case "Sign In": 
                MenuSignin.click();
                break; 
            default: 
 //               System.out.println("no match"); 
        }
		
//		Misc.sleepMiniSec(1000);
		Misc.waitForPageLoad(driver);
		String actualTitle = driver.getTitle();
		Misc.assertTrue(logger, expectedTitle.equalsIgnoreCase(actualTitle), "go to Module");
	}


	public void validateLogo() {
//		System.out.println("entering Home Page > verifyRFAImg ...");
//		Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", rfaImg);
//		if (!ImagePresent) {
//			System.out.println("RFA Image not displayed.");
//		  } else {
//			System.out.println("RFA Image displayed.");
//		}		
	}


	public void validatePLFormLabel(String expectedLabel) {
		String actualLabel = pageLabel.getText();
		Misc.assertTrue(logger, expectedLabel.equalsIgnoreCase(actualLabel), "Validate Home Page label");
	}

}
