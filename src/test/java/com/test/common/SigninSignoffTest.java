package com.test.common;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.DriverType;
import com.pages.MyRatePage;
import com.pages.SigninPage;
import com.pages.common.HomePage;
import com.testtasks.common.SigninSignoffTesting;
import com.util.TestingListener;

@Listeners(TestingListener.class)
public class SigninSignoffTest extends TestBase implements SigninSignoffTesting {
	HomePage homePage; 
	MyRatePage myRatePage;
	SigninPage signinPage;
	
	public SigninSignoffTest() {
		super(DriverType.CHROME);
		logger = LogManager.getLogger(SigninSignoffTest.class.getName());
		homePage = new HomePage(driver);
		myRatePage = new MyRatePage(driver);
		signinPage = new SigninPage(driver);
	}
	@Test(priority =1)
	@Override
	public void signin() {
		logger.info(" testing sign in");
		String homeUrl = property.getValue("upgradeHome");
		String expectedTitle = vDataBook.getAValueByKey("Webpage_Title", "Home");
		homePage.openHomePage(homeUrl, expectedTitle);
		String moduleName = "Sign In";
		String expectedModuleTitle = vDataBook.getAValueByKey("Webpage_Title", moduleName);
		homePage.gotoModule(moduleName, expectedModuleTitle);
		
		String userid = "Michael.Smithk7@hotmail.com";
		String pwd = "JohnSmithk7";
		expectedTitle = vDataBook.getAValueByKey("Webpage_Title", "My Rate");	
		signinPage.signin(userid, pwd, expectedTitle);
	}

	@Test(priority =2)
	@Override
	public void signoff() {
		String expectedTitle = vDataBook.getAValueByKey("Webpage_Title", "Sign Off");
		myRatePage.signOut(logger, expectedTitle);
	}
}
