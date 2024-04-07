package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	SearchPage searchPage;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenURL(prop.getProperty("browserName"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		HomePage homePage = new HomePage(driver);

		// driver.findElement(By.name("search")).sendKeys("HP");
		homePage.enterProductInSearchBox("HP");

		// driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		searchPage = homePage.clickOnSearchButton();

		//SearchPage searchPage = new SearchPage(driver);
		// Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product HP is not displayed");
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(), "Valid product HP is not displayed");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		HomePage homePage = new HomePage(driver);

		//driver.findElement(By.name("search")).sendKeys("Honda");
		homePage.enterProductInSearchBox("Honda");
		
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		//homePage.clickOnSearchButton();
		searchPage = homePage.clickOnSearchButton();
		
		//SearchPage searchPage = new SearchPage(driver);
		
		//String actualSearchMessage = driver.findElement(By.xpath("//input[@id ='button-search']/following-sibling::p")).getText();
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria12.","Error message not displayed");
	}

	@Test(priority = 3, dependsOnMethods={"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		HomePage homePage = new HomePage(driver);

		//driver.findElement(By.name("search")).sendKeys("");
		
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		//homePage.clickOnSearchButton();
		 searchPage = homePage.clickOnSearchButton();
		
		//SearchPage searchPage = new SearchPage(driver);
		
		//String actualSearchMessage = driver.findElement(By.xpath("//input[@id ='button-search']/following-sibling::p")).getText();
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.","Error message not displayed");
	}
}
