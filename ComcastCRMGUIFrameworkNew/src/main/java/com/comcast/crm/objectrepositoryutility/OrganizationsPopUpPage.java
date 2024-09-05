package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPopUpPage {
	WebDriver driver=null;
	public OrganizationsPopUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="search_text")
	private WebElement searchTextEdt;
	
	public WebElement getSearchTextEdt()
	{
		return searchTextEdt;
	}
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	public WebElement getSearchBtn()
	{
		return searchBtn;
	}
	
	
	
	
}
