package com.test.module;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.DriverType;
import com.pages.common.HomePage;
import com.test.common.TestBase;
import com.testtasks.modules.HomePageTesting;
import com.util.TestingListener;

@Listeners(TestingListener.class)
public class HomePageTest extends TestBase implements HomePageTesting {
	HomePage homePage;
	
	public HomePageTest() {
		super(DriverType.CHROME);
		homePage = new HomePage(driver);
		logger = LogManager.getLogger(HomePageTest.class.getName());
	}

	@Test(priority=1)
	@Override
	public void validateTitle() {
		logger.info("Validating Home page Title");
		String homeUrl = property.getValue("upgradeHome"); //back to home page because "Help Center" home doesn't display menu
		driver.get(homeUrl);
		String expectedTitle = vDataBook.getAValueByKey("Webpage_Title", "Home");
		homePage.openHomePage(homeUrl, expectedTitle);
	}
	
	@Test(priority=2)
	@Override
	public void validateLabel() {
		logger.info("Validating Home page label");
		String pageLabel = "Home";
		String expectedLabel = vDataBook.getAValueByKey("Page_Label", pageLabel);
		homePage.validatePLFormLabel(expectedLabel);
	}
	
	@Test(priority=3)
	@Override
	public void validateLogo() {
		logger.info("Validating Home page logo - not working yet");
//		homePage.validateLogo();		
	}

	@Test(priority = 4)
	@Override
	public void validateMenuPersonalLoan() {
		logger.info("validating Menu - Personal Loan");
		String menuLabel = "Personal Loans";
		String expectedModuleTitle = vDataBook.getAValueByKey("Webpage_Title", menuLabel);
		homePage.gotoModule(menuLabel, expectedModuleTitle);
	}

	@Test(priority = 5)
	@Override
	public void validateMenuUpgradeCard() {
		logger.info("validating Menu - Upgrade Card");
		String menuLabel = "Upgrade Card";
		String expectedModuleTitle = vDataBook.getAValueByKey("Webpage_Title", menuLabel);
		homePage.gotoModule(menuLabel, expectedModuleTitle);
	}

	@Test(priority = 6)
	@Override
	public void validateMenuCreditHealth() {
		logger.info("validating Menu - Credit Health");
		String menuLabel = "Credit Health";
		String expectedModuleTitle = vDataBook.getAValueByKey("Webpage_Title", menuLabel);
		homePage.gotoModule(menuLabel, expectedModuleTitle);
	}

	@Test(priority = 7)
	@Override
	public void validateMenuHelpCenter() {
		logger.info("validating Menu - Help Center");
		String menuLabel = "Help Center";
		String expectedModuleTitle = vDataBook.getAValueByKey("Webpage_Title", menuLabel);
		homePage.gotoModule(menuLabel, expectedModuleTitle);
	}
}
