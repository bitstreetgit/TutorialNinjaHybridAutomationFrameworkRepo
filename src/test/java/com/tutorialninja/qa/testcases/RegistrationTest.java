package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegistrationTest extends Base {

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegistrationTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		// driver.findElement(By.xpath("//span[text()='My Account']")).click();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		// driver.findElement(By.linkText("Register")).click();
		//homePage.selectRegisterOption();
		registerPage = homePage.selectRegisterOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegistrationWithMandatoryFields() {

		//RegisterPage registerPage = new RegisterPage(driver);

		// driver.findElement(By.id("input-firstname")).sendKeys("bitstreet");
		registerPage.enterFirstName("bitstreet");
		// driver.findElement(By.id("input-lastname")).sendKeys("tech");
		registerPage.enterLastName("tech");
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		registerPage.enterEmail(Utilities.generateEmailWithTimestamp());
		// driver.findElement(By.id("input-telephone")).sendKeys("9898098907");
		registerPage.enterTelephone("9898098907");
		// driver.findElement(By.id("input-password")).sendKeys("bit@123");
		registerPage.enterPassword("bit@123");
		// driver.findElement(By.id("input-confirm")).sendKeys("bit@123");
		registerPage.enterConfirmPassword("bit@123");
		// driver.findElement(By.name("agree")).click();
		registerPage.selectPrivacyPolicy();
		// driver.findElement(By.xpath("//input[@value='Continue']")).click();
		//registerPage.clickOnContinueButton();
		
		accountSuccessPage = registerPage.clickOnContinueButton();

		//AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);

		// String actualSuccessHeading = driver.findElement(By.xpath("//div[@id
		// ='content']/h1")).getText();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!",
				"Account Success Message not displayed");

	}

	@Test(priority = 2)
	public void verifyRegistrationWithAllFields() {

		//RegisterPage registerPage = new RegisterPage(driver);

		// driver.findElement(By.id("input-firstname")).sendKeys("bitstreet");
		registerPage.enterFirstName("bitstreet");
		// driver.findElement(By.id("input-lastname")).sendKeys("tech");
		registerPage.enterLastName("tech");
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		registerPage.enterEmail(Utilities.generateEmailWithTimestamp());
		// driver.findElement(By.id("input-telephone")).sendKeys("9898098907");
		registerPage.enterTelephone("9898098907");
		// driver.findElement(By.id("input-password")).sendKeys("bit@123");
		registerPage.enterPassword("bit@123");
		// driver.findElement(By.id("input-confirm")).sendKeys("bit@123");
		registerPage.enterConfirmPassword("bit@123");

		// driver.findElement(By.xpath("//input[@name='newsletter' and @value
		// ='1']")).click();
		registerPage.selectYesNewsLetterOption();

		// driver.findElement(By.name("agree")).click();
		registerPage.selectPrivacyPolicy();
		// driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		//AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);

		// String actualSuccessHeading = driver.findElement(By.xpath("//div[@id
		// ='content']/h1")).getText();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!",
				"Account Success Message not displayed");
	}

	@Test(priority = 3)
	public void verifyRegistrationWithExistEmailId() {

		//RegisterPage registerPage = new RegisterPage(driver);

		// driver.findElement(By.id("input-firstname")).sendKeys("bitstreet");
		registerPage.enterFirstName("bitstreet");
		// driver.findElement(By.id("input-lastname")).sendKeys("tech");
		registerPage.enterLastName("tech");
		// driver.findElement(By.id("input-email")).sendKeys("bitstreettech@gmail.com");
		registerPage.enterEmail("bitstreettech@gmail.com");
		// driver.findElement(By.id("input-telephone")).sendKeys("9898098907");
		registerPage.enterTelephone("9898098907");
		// driver.findElement(By.id("input-password")).sendKeys("bit@123");
		registerPage.enterPassword("bit@123");
		// driver.findElement(By.id("input-confirm")).sendKeys("bit@123");
		registerPage.enterConfirmPassword("bit@123");

		// driver.findElement(By.xpath("//input[@name='newsletter' and @value
		// ='1']")).click();
		registerPage.selectYesNewsLetterOption();

		// driver.findElement(By.name("agree")).click();
		registerPage.selectPrivacyPolicy();
		// driver.findElement(By.xpath("//input[@value='Continue']")).click();
		registerPage.clickOnContinueButton();

		// String actualWarning = driver.findElement(By.xpath("//div[@class='alert
		// alert-danger alert-dismissible']")).getText();
		String actualWarning = registerPage.retrieveDuplicateEmailWarning();
		Assert.assertEquals(actualWarning, "Warning: E-Mail Address is already registered!",
				"Warning message is not displayed for existing email");

	}

	@Test(priority = 4)
	public void verifyRegistrationWithoutAnyDetails() {

		//RegisterPage registerPage = new RegisterPage(driver);

		// driver.findElement(By.xpath("//input[@value='Continue']")).click();
		registerPage.clickOnContinueButton();

		//String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		
		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertEquals(actualPrivacyPolicyWarning, "Warning: You must agree to the Privacy Policy!",
				"Warning message is not displayed for privacy policy");

		//String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning, "First Name must be between 1 and 32 characters!",
				"Warning message is not displayed for firstName");

		//String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning, "Last Name must be between 1 and 32 characters!",
				"Warning message is not displayed for lastName");

		//String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		String actualEmailWarning = registerPage.retrieveEmailIdWarning();
		Assert.assertEquals(actualEmailWarning, "E-Mail Address does not appear to be valid!",
				"Warning message is not displayed for email");

		//String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		String actualTelephoneWarning = registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning, "Telephone must be between 3 and 32 characters!",
				"Warning message is not displayed for telephone");

		//String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		String actualPasswordWarning = registerPage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning, "Password must be between 4 and 20 characters!",
				"Warning message is not displayed for password");

	}

}
