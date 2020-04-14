package com.test.module;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.DriverType;
import com.pages.PersonalLoanRequestPage;
import com.pages.common.HomePage;
import com.test.common.TestBase;
import com.testtasks.modules.PersonalLoanPageTesting;
import com.util.Misc;
import com.util.TestingListener;

@Listeners(TestingListener.class)
public class PersonalLoanPageTest extends TestBase implements PersonalLoanPageTesting {
	PersonalLoanRequestPage pLoanPage;
	HomePage homePage;
	public PersonalLoanPageTest() {
		super(DriverType.CHROME);
		logger = LogManager.getLogger(PersonalLoanPageTest.class.getName());
		homePage = new HomePage(driver);
		pLoanPage = new PersonalLoanRequestPage(driver);
	}
	
	@Test(priority=1)
	@Override
	public void validatePageLabel() {
		String homeUrl = property.getValue("upgradeHome"); //back to home page because "Help Center" home doesn't display menu
		String expectedTitle = vDataBook.getAValueByKey("Webpage_Title", "Home");
		driver.get(homeUrl);		
		homePage.openHomePage(homeUrl, expectedTitle);
		Misc.waitForPageLoad(driver);
		
		String menuLabel = "Personal Loans";
		String expectedModuleTitle = vDataBook.getAValueByKey("Page_Label", menuLabel);
		homePage.gotoModule(menuLabel, expectedModuleTitle);
		
		String expectedLabel = vDataBook.getAValueByKey("Page_Label", "Personal Loans");
		pLoanPage.validatePageLabel(expectedLabel);
	}
	
	@Test(priority=2)
	@Override
	public void validatePageLogo() {
		// TODO Auto-generated method stub
		
	}
	
	@Test(priority=3)
	@Override
	public void validateDropdownItems() {
		logger.info(" *** Validating Drop down Items");
		
		String menuLabel = "Personal Loans";
		String expectedModuleTitle = vDataBook.getAValueByKey("Webpage_Title", menuLabel);
		homePage.gotoModule(menuLabel, expectedModuleTitle);
		Misc.waitForPageLoad(driver);
		
		ArrayList<String> items = vDataBook.getColumnValues("Loan_Purpose", "Purpose");		
		ArrayList<String> elements = pLoanPage.getPurposeItems();

		Collections.sort(items);
        Collections.sort(elements);
        Misc.assertTrue(logger, items.equals(elements), "validate Drop down Items");
	}
	
	@Test(priority=4)
	@Override
	public void validateAmountRestriction() {
		logger.info("Validating Amount Restriction");
		ArrayList<String> arrList = tDataBook.getColumnValues("Loan_Amount", "Loan_Amount");
		String[] amt = arrList.toArray(new String[arrList.size()]);
//		Misc.printStringArray(amt);
		pLoanPage.validateAmountInput(amt, "Pay off Credit Cards");		
	}
}
