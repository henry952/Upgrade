package com.test.end2end;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.DriverType;
import com.pages.ContactInfoPage;
import com.pages.CreateAccountPage;
import com.pages.IncomePage;
import com.pages.MyRatePage;
import com.pages.PersonalLoanRequestPage;
import com.pages.SINcheckRatePage;
import com.pages.SigninPage;
import com.pages.common.HomePage;
import com.test.common.TestBase;
import com.testtasks.end2end.PersonalLoanQuotable;
import com.util.Misc;
import com.util.TestingListener;

@Listeners(TestingListener.class)
public class PersonalLoanQuotationTest extends TestBase implements PersonalLoanQuotable {
	HomePage homePage;
	PersonalLoanRequestPage pLoanReqPage;
	ContactInfoPage contactPage;
	IncomePage incomePage;
	CreateAccountPage createAccountPage; 
	SINcheckRatePage sinPage;
	MyRatePage myRatePage;
	SigninPage signinPage;
	
	String[] myRate;
	
	public PersonalLoanQuotationTest() {
		super(DriverType.CHROME);
		homePage = new HomePage(driver);
		pLoanReqPage = new PersonalLoanRequestPage(driver);
		contactPage = new ContactInfoPage(driver);
		incomePage = new IncomePage(driver);
		createAccountPage= new CreateAccountPage(driver);
		sinPage = new SINcheckRatePage(driver);
		myRatePage = new MyRatePage(driver);
		signinPage = new SigninPage(driver);
		logger = LogManager.getLogger(PersonalLoanQuotationTest.class.getName());
	}
	
	@Test(priority=1)
	@Override
	public void validateLoanQuotation() {
		logger.info("Validating Loan Quotation end-to-end");
		String homeUrl = property.getValue("upgradeHome");
		String expectedTitle = vDataBook.getAValueByKey("Webpage_Title", "Home");
		homePage.openHomePage(homeUrl, expectedTitle);
		
		String moduleName = "Personal Loans";
		String expectedModuleTitle = vDataBook.getAValueByKey("Webpage_Title", moduleName);
		homePage.gotoModule(moduleName, expectedModuleTitle);
		
		//submit request
		logger.info("Submit Request");
		String[] value = tDataBook.getRowByKey("Amount_Income", "3500");
		String desiredAmount = value[0];
		String purpose = value[1];
		pLoanReqPage.submitRequest(desiredAmount, purpose);
		
		//submit contact information
		logger.info("Submit contact information");
		String[] contact = tDataBook.getARandomRow("Contact_Info");
		Misc.sleepMiniSec(1000);
		contactPage.submitContactInfo(contact);
		
		//submit income info	
		logger.info("Submit income info");
		String income = value[2];
		String aIncome = value[3];
		Misc.sleepMiniSec(1000);
		incomePage.submitIncome(income, aIncome);
		
		//fill email and password to create account
		logger.info("Fill email and password to create account");
		String[] credential = tDataBook.getARandomRow("Credential");
		createAccountPage.createAccount(credential[0], credential[1]);
		
		//fill in SIN to get result
		logger.info("Fill in SIN to get result");
		String[] items = tDataBook.getARandomRow("SIN");
		String sin = items[0];
//		Misc.sleepMiniSec(5000);
		sinPage.submitSINForRate(sin);
//		Misc.sleepMiniSec(5000);
		//retrieve result and save the value
		logger.info("Retrieve result and save the value");
	//	Misc.sleepMiniSec(5000);
		myRate = myRatePage.retrieveResult();

	}

	@Override
	public void validateQuotationResult() {
		// TODO Auto-generated method stub

	}

}
