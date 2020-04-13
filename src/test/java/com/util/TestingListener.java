package com.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.data.DriverType;
import com.test.common.TestBase;

public class TestingListener  implements ITestListener{
	TestBase testBase = new TestBase(DriverType.CHROME);
	
	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		testBase.takeScreenshot(result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
//	    ITestContext context = result.getTestContext();
//	    WebDriver driver = (WebDriver)context.getAttribute("WebDriver");
	
//	    Object testClass = result.getInstance();
//	    WebDriver driver = ((BaseTest) testClass).getDriver();
//		Screenshot.takeScreenshot(result.getName(), driver);
		testBase.takeScreenshot(result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
//		System.out.println("The name of the testcase Skipped is :"+result.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
