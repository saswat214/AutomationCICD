package Saswatautomation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Saswatautomation.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//Thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
    test=extent.createTest(result.getMethod().getMethodName());	
    extentTest.set(test);//By pushing the test object into set(), It will assign a unique thread id to test obj.
	}

	@Override
	public void onTestSuccess(ITestResult result) {
    extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result){
	    String filepath=null;
	    try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
    try {
		filepath=getScreenshot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		e.printStackTrace();
	}
    extentTest.get().fail(result.getThrowable());
    extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		
	}

	@Override
	public void onStart(ITestContext context) {

		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
