package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	WebDriver driver;

	public ContactInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "dtlview_Last Name")
	private WebElement actLastName;

	public WebElement getActLastName() {
		return actLastName;
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement actHeaderText;

	public WebElement getActHeaderText() {
		return actHeaderText;
	}
}
