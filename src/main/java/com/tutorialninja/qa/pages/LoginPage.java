package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	// objects
	@FindBy(id = "input-email")
	private WebElement emailId;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtn;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// actions

	public void enterEmailId(String emailText) {
		emailId.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}

	public AccountPage clickOnLoginButton() {
		loginBtn.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingWarningMessageText() {
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;
	}

}

