package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage 
{
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
@FindBy(name="accountname")
private WebElement orgNameEdt;

@FindBy(xpath="//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

@FindBy(name="industry")
private WebElement industryDD;

@FindBy(name="accounttype")
private WebElement typeDD;

public WebElement getTypeDD()
{
	return typeDD;
}
@FindBy(id="phone")
private WebElement phoneNumberEdt;

public WebElement getPhoneNumberEdt()
{
	return phoneNumberEdt;
}
public WebElement getIndustry() {
	return industryDD;
}

public WebElement getOrgNameEdt() {
	return orgNameEdt;
}

public WebElement getSaveBtn() {
	return saveBtn;
}
public void createOrg(String orgName){
	orgNameEdt.sendKeys(orgName);
	saveBtn.click();
}
public void createOrg(String orgName,String industry,String type){
	orgNameEdt.sendKeys(orgName);
	Select sel1=new Select(industryDD);
	sel1.selectByVisibleText(industry);
	Select sel2=new Select(typeDD);
	sel2.selectByVisibleText(type);
	saveBtn.click();
}
}
