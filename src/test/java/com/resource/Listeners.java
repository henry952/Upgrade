package com.resource;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.data.DriverType;
import com.test.common.TestBase;

public class Listeners implements ITestListener{
	TestBase tb = new TestBase(DriverType.CHROME);
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("*********** test success "+ result.getName());
	//	tb.getScreenshot(result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("$$$$$$$$$$$$ test fail " + result.getName());
//		tb.getScreenshot(result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
