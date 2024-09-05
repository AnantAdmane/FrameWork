 package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
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
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener{
	public static ExtentReports report;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("report config");
		//spark report config
		String time=new Date().toString().replace(" ", "_").replace(":","_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK); 
		
		//add env info and create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("BROWSER", "chrome");
	}
	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		test.log(Status.INFO,"==report backup==");	
	}

	@Override
	public void onTestStart(ITestResult result) {
	test=report.createTest(result.getMethod().getMethodName());
	test.log(Status.INFO,"==>"+result.getMethod().getMethodName()+"<=STARTED=");
	UtilityClassObject.setTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,"==>"+result.getMethod().getMethodName()+"<=COMPLETED=");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String time=new Date().toString().replace(" ", "_").replace(":","_");
		String testName=result.getMethod().getMethodName();
		TakesScreenshot ts=(TakesScreenshot)UtilityClassObject.getWebDriver();
		//TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String filepath=ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath,testName+" "+time);
		test.log(Status.FAIL,"==>"+result.getMethod().getMethodName()+"<=FAILED=");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
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
