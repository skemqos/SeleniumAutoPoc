package com.herokuapp.theinternet;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class LoginTests {

	@Parameters({"username","password","expectedMessage"})
	@Test(priority=1, groups = {"positiveTests", "smokeTests"})
	public void positiveLoginTest(String username, String password,String expectedMessage) {

		// step-1. open webpage using url in a targeted browser, say chrome
		WebDriver driver = Util.openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		new Util().sleep(200); // for demo, not real

		WebElement passwordElement = driver.findElement(By.name("password")); // testing find by name
		passwordElement.sendKeys(password);
		new Util(100).sleep(200); // for demo, not real

		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		new Util(100).sleep();

		// step-3. validate successful login, after 2-3 validation checks (updated url,logout button visible, successful login message)
		// 3-1. logout button
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "logoutButton is visible");

		// 3-2. successful login message:
		WebElement successMsg = driver.findElement(By.xpath("//div[@id='flash']"));

		String expectedMsg = expectedMessage;
		String actualMsg = successMsg.getText();

		Assert.assertTrue(actualMsg.contains(expectedMsg),
				"Actual msg does not contain expected message:+\nActual Message:" + actualMsg + "\nExpected Message:"+ expectedMsg);
		// 3-3. updated url
		String expectedurl = "https://the-internet.herokuapp.com/secure";

		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual page not same as Expected Url");

		// step-4. : Close Chrome Browser Instance (with an optional dealy of 2s)
		new Util().closeBrowser(driver);
	}

	@Parameters({"username","password","expectedMessage"})
	@Test(priority=2, groups = {"negativeTests", "smokeTests"})
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting negativeLoginTests with" + username + "and" + password);

		// step-1. open webpage using url in a targeted browser, say chrome
		// pre-req-1.0: Create chrome driver, specifying its availability in a relative
		WebDriver driver = Util.openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("username")); // testing find by id locator
		usernameElement.sendKeys(username);
		WebElement passwordElement = driver.findElement(By.name("password")); // testing find by name locator
		passwordElement.sendKeys(password);

		// step-3. click login button
		WebElement loginButton = driver.findElement(By.tagName("button")); // testing find by tagName locator
		loginButton.click();
		new Util().sleep(200);

		// 4-2. failed login message: by Id:
		WebElement failedLoginMsg = driver.findElement(By.id("flash"));

		String expectedMsg = expectedErrorMessage;
		String actualMsg = failedLoginMsg.getText();

		Assert.assertTrue(actualMsg.contains(expectedMsg),
				"Actual msg does not contain expected message:+\nActual Message:" + actualMsg + "\nExpected Message:"
						+ expectedMsg);

		// step-5. : Close Chrome Browser Instance (with an optional dealy of 2s)
		new Util().sleep(200);

		new Util().closeBrowser(driver);
	}

}
