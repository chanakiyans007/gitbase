package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClasss;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener {
    
	public ExtentReports report;
	public static ExtentTest test; // your using this static keyword in this line so parallel execution is not work
									// and sequential execution only work

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env info and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backUp");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("====>" + result.getMethod().getMethodName() + "<== Start ==");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "===> STARTED <===");
	}

	

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("====>" + result.getMethod().getMethodName() + "<== End ==");
		test.log(Status.PASS, result.getMethod().getMethodName() + "===> COMPLETED <===");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		// Takes ScreenShot
		TakesScreenshot edriver = (TakesScreenshot) BaseClasss.sdriver;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64);
		// step-2 : use getScreenshot method to get file type of screenshot
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		// step-3 : store screen on local driver
		UtilityClassObject.getTest().log(Status.FAIL, result.getThrowable());
		test.addScreenCaptureFromBase64String(filepath, testName + "_" + time);
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===> FAILED <===");
        
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
