package com.tutorialninja.qa.listerners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utils.ExtentReporter;
import com.tutorialninja.qa.utils.Utilities;

public class ListernerTest implements ITestListener {

	// for this interface change scope of testng dependency from test to compile
	// else unable to add ItestListner

	ExtentReports extentReports;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {

		// System.out.println("Execution of Project Tests started");

		extentReports = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {

		
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName() + " got executed successfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("screenshot taken");

		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL,result.getName() + " got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP,result.getName() + " got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
		
		String pathofExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html";
		//to automatically open the report without manual refresh
		File extentReport = new File(pathofExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
}
