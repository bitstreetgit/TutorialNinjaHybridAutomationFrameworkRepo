package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropDown;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchBtn;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// actions

	public void clickOnMyAccount() {
		myAccountDropDown.click();
	}

	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}

	public void enterProductInSearchBox(String productText) {
		searchBox.sendKeys(productText);
	}

	public SearchPage clickOnSearchButton() {
		searchBtn.click();
		return new SearchPage(driver);
	}
}
