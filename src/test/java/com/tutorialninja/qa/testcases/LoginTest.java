package com.tutorialninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.qa.utils.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest extends Base {

	public WebDriver driver;
	LoginPage loginPage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		// loadPropertiesFile();
		driver = initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		// driver.findElement(By.xpath("//span[text()='My Account']")).click();
		homePage.clickOnMyAccount();
		// driver.findElement(By.linkText("Login")).click();
		//homePage.selectLoginOption(); 
		
		loginPage = homePage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {

		//LoginPage loginPage = new LoginPage(driver);

		// driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		loginPage.enterEmailId(prop.getProperty("validEmail"));

		// driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginPage.enterPassword(prop.getProperty("validPassword"));

		// driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		//loginPage.clickOnLoginButton();
		AccountPage accountPage = loginPage.clickOnLoginButton();

		// Assert.assertTrue(driver.findElement(By.linkText("Edit your account
		// information")).isDisplayed(),"Edit your account information is not
		// displayed");
		// AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your account information is not displayed");
	}

	@Test(priority = 6, dataProvider = "validCredentialSupplier")
	public void verifyLoginWithValidCredentialsUsingDataProvider(String email, String password) {

		//LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailId(email);
		// driver.findElement(By.id("input-email")).sendKeys(email);
		loginPage.enterPassword(password);
		// driver.findElement(By.id("input-password")).sendKeys(password);
		loginPage.clickOnLoginButton();
		// driver.findElement(By.xpath("//input[@value='Login']")).click();

		// Assert.assertTrue(driver.findElement(By.linkText("Edit your account
		// information")).isDisplayed(),"Edit your account information is not
		// displayed");
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your account information is not displayed");

	}

	@DataProvider(name = "validCredentialSupplier")
	public Object[][] supplyTestData() {

		Object[][] data = { { "bitstreet01@gmail.com", "12345" }, { "bitstreet02@gmail.com", "12345" },
				{ "bitstreet03@gmail.com", "12345" } };
		return data;
	}

	@Test(priority = 7, dataProvider = "validCredentialSupplierUsingExcel")
	public void verifyLoginWithValidCredentialsUsingExcel(String email, String password) {

		//LoginPage loginPage = new LoginPage(driver);

		// driver.findElement(By.id("input-email")).sendKeys(email);
		loginPage.enterEmailId(email);
		// driver.findElement(By.id("input-password")).sendKeys(password);
		loginPage.enterPassword(password);
		// driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();

		// Assert.assertTrue(driver.findElement(By.linkText("Edit your account
		// information")).isDisplayed(),"Edit your account information is not
		// displayed");
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your account information is not displayed");
	}

	@DataProvider(name = "validCredentialSupplierUsingExcel")
	public Object[][] supplyTestDataUsingExcel() {

		Object[][] data = Utilities.getTestDatFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		//LoginPage loginPage = new LoginPage(driver);

		// driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		loginPage.enterEmailId(Utilities.generateEmailWithTimestamp());

		// driver.findElement(By.id("input-password")).sendKeys("Bitstreet1@18");
		loginPage.enterPassword("Bitstreet1@18");
		// driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();

		// String actualWarningMessage
		// =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected error message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailValidPassword() {

		//LoginPage loginPage = new LoginPage(driver);
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		loginPage.enterEmailId(Utilities.generateEmailWithTimestamp());

		// driver.findElement(By.id("input-password")).sendKeys("Bitstreet1@18");
		loginPage.enterPassword("Bitstreet1@18");

		// driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();

		// String actualWarningMessage =
		// driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected error message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailInvalidPassword() {

		//LoginPage loginPage = new LoginPage(driver);
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		loginPage.enterEmailId(Utilities.generateEmailWithTimestamp());

		// driver.findElement(By.id("input-password")).sendKeys("Bitstreet1@18");
		loginPage.enterPassword("Bitstreet1@1800");

		// driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();

		// String actualWarningMessage =
		// driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected error message is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		//LoginPage loginPage = new LoginPage(driver);
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		loginPage.enterEmailId("");

		// driver.findElement(By.id("input-password")).sendKeys("Bitstreet1@18");
		loginPage.enterPassword("");

		// driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();

		// String actualWarningMessage =
		// driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected error message is not displayed");

	}

}
