package com.comcast.crm.contacttest;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPopUpPage;

public class CreateContactTest extends BaseClass {
	@Test(groups="smokeTest")
	public void CreateContactTest() throws Throwable {
		String lastName = eLib.getDataFromExcel("contacts", 1, 2) + jLib.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.getLastNameEdt().sendKeys(lastName);
		ccp.getSaveBtn().click();
		ContactInformationPage cip = new ContactInformationPage(driver);
		String actLastName = cip.getActLastName().getText();
		String headerText = cip.getActHeaderText().getText();
		boolean status=headerText.contains(lastName);
		
		Assert.assertEquals(status, true);
		Assert.assertEquals(actLastName, lastName);
	}

	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws Throwable {
		String orgName = eLib.getDataFromExcel("contacts", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contacts", 7, 3) + jLib.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);
		cnop.getSaveBtn().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String ActualorgNameInfo = oip.getActOrgName().getText();
		Assert.assertEquals(ActualorgNameInfo, orgName);
		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(lastName);
		cncp.getSelectBtn().click();

		wLib.switchToTabOnUrl(driver, "Accounts&action");
		OrganizationsPopUpPage opup = new OrganizationsPopUpPage(driver);
		opup.getSearchTextEdt().sendKeys(orgName);
		opup.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		wLib.switchToTabOnUrl(driver, "Contacts&action");
		cncp.getSaveBtn().click();
		ContactInformationPage cip=new ContactInformationPage(driver);
		String header = cip.getActHeaderText().getText();
		
		boolean status=header.contains(lastName);
		Assert.assertTrue(status);
	}

	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {
		String lastName = eLib.getDataFromExcel("contacts", 4, 2) + jLib.getRandomNumber();
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		WebElement supportStartDate = driver.findElement(By.name("support_start_date"));
		supportStartDate.clear();
		supportStartDate.sendKeys(startDate);
		WebElement supportEndDate = driver.findElement(By.name("support_end_date"));
		supportEndDate.clear();
		supportEndDate.sendKeys(endDate);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String actStartDate = driver.findElement(By.id("mouseArea_Support Start Date")).getText().trim();
		String actEndDate = driver.findElement(By.id("mouseArea_Support End Date")).getText().trim();
		
		Assert.assertEquals(actStartDate, startDate);
		Assert.assertEquals(actEndDate, endDate);
	}
}
