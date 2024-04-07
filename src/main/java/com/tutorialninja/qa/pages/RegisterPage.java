package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	// objects
	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(id = "input-confirm")
	private WebElement confirmPassword;

	@FindBy(name = "agree")
	private WebElement privacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//input[@name='newsletter' and @value ='1']")
	private WebElement yesNewsLetterOption;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailWarning;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailIdWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// actions

	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastName.sendKeys(lastNameText);
	}

	public void enterTelephone(String telephoneText) {
		telephone.sendKeys(telephoneText);
	}

	public void enterEmail(String emailText) {
		email.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPassword.sendKeys(confirmPasswordText);
	}

	public void selectPrivacyPolicy() {
		privacyPolicy.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueBtn.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsLetterOption() {
		yesNewsLetterOption.click();
	}

	public String retrieveDuplicateEmailWarning() {

		String duplicateEmailWarningText = duplicateEmailWarning.getText();
		return duplicateEmailWarningText;
	}

	public String retrievePrivacyPolicyWarning() {

		String privacyPoilicyWarningText = privacyPolicyWarning.getText();
		return privacyPoilicyWarningText;
	}

	public String retrieveFirstNameWarning() {

		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}

	public String retrieveLastNameWarning() {

		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}

	public String retrieveEmailIdWarning() {

		String emailIdWarningText = emailIdWarning.getText();
		return emailIdWarningText;
	}

	public String retrieveTelephoneWarning() {

		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}

	public String retrievePasswordWarning() {

		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
}
