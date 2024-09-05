package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement pasaswordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn ;

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasaswordEdt() {
		return pasaswordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	public void loginToApp(String url,String username,String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		pasaswordEdt.sendKeys(password);
		loginBtn.click();
	}
}
