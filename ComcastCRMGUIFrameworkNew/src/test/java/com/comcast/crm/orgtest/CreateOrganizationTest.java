package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class CreateOrganizationTest extends BaseClass {
	@Test(groups= {"smokeTest"})
	public void createOrganizationTest() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO,"read data from excel");
		String orgName = eLib.getDataFromExcel("testCases", 1, 2).toString() + jLib.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		UtilityClassObject.getTest().log(Status.INFO,"navigate to Org Page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO,"navigate to create Org Page");
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+"new Org created");
		
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgNameHeader = oip.getHeaderMsg().getText();
		boolean status=actOrgNameHeader.contains(orgName);
		Assert.assertEquals(status, true);
		String ActualorgNameInfo = oip.getActOrgName().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(ActualorgNameInfo, orgName);
		}

	@Test(groups= {"regressionTest"})
	public void CreateOrganizationWithIndustryTest() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO,"read data from excel");
		String orgName = eLib.getDataFromExcel("testCases", 1, 2) + jLib.getRandomNumber();
		String Industry = eLib.getDataFromExcel("testCases", 7, 4).toString();
		String Type = eLib.getDataFromExcel("testCases", 7, 5).toString();
		UtilityClassObject.getTest().log(Status.INFO,"navigate to home page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		UtilityClassObject.getTest().log(Status.INFO,"navigate to organization ");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);
		cnop.createOrg(orgName, Industry, Type);
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		
		String actIndustry = oip.getActIndustry().getText();
		boolean status=actIndustry.contains(Industry);
		Assert.assertEquals(status, true);
		String actType = oip.getActType().getText();
		Assert.assertEquals(actType, Type);
	}

	@Test(groups= {"regressionTest"})
	public void createOrganizationWithNumberTest() throws Throwable {
		String orgName = eLib.getDataFromExcel("testCases", 1, 2).toString() + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("testCases", 7, 6).toString();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);
		cnop.getPhoneNumberEdt().sendKeys(phoneNumber);
		cnop.getSaveBtn().click();
		String actPhone = oip.getActPhone().getText();
		Assert.assertEquals(actPhone, phoneNumber);
	}
}
