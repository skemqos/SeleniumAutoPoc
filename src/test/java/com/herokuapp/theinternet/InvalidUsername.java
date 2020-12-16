package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidUsername {

	@Test
	public void invalidLoginTest() {
		// step-1. open webpage using url in a targeted browser, say chrome
		// pre-req-1.0: Create chrome driver, specifying its availability in a relative
		
		// Specify Name and Path of WebDriver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		// System.setProperty("webdriver.chrome.logfile",

		// pre-req-1.1. Create chrome web-driver variable
		System.out.println("Opening Webpage");
		try {
			//create chrome web driver
			WebDriver driver = new ChromeDriver();
			
			//open webpage, and maximize window
			String url = "https://the-internet.herokuapp.com/login";
			driver.get(url); // This API open webpage
			driver.manage().window().maximize();
			new Util(50).sleep();
			System.out.println("Opened Webpage");

			// step-2-1. enter login details: (username)
			WebElement username = driver.findElement(By.id("username")); // testing find by id locator
			username.sendKeys("albatross");
			// new Util(50).sleep(); //for demo, not real
			
			// step-2-2. enter login details: (username)
			WebElement password = driver.findElement(By.name("password")); // testing find by name locator
			password.sendKeys("SuperSecretPassword!");
			// new Util(50).sleep(); //for demo, not real

			// step-3. click login button
			WebElement loginButton = driver.findElement(By.tagName("button")); // testing find by tagName locator
			loginButton.click();
			//new Util(50).sleep();

			// 4-2. validate invalidUsername: (By Id)
			WebElement failedLoginMsg = driver.findElement(By.id("flash"));
			String actualMsg = failedLoginMsg.getText();
			String expectedMsg = "Your username is invalid!";
			Assert.assertTrue(actualMsg.contains(expectedMsg),
					"Actual msg does not contain expected message:" + "\nActual Message:" + actualMsg + "\nExpected Message:" + expectedMsg);

			// 4-3. NO updated in url for failed login
			String expectedurl = "https://the-internet.herokuapp.com/login";

			String actualurl = driver.getCurrentUrl();
			Assert.assertEquals(actualurl, expectedurl, "Actual page(Re-login page) not same as Expected Url");
			// Assert.assertEquals(actualurl,url,"Actual page not same as Expected Url");
			// //demo to validate Assertion actually works

			// step-5c Close Chrome webdriver (with an optional dealy of 2s)
			new Util(50).sleep();
			System.out.println("ChromeDriver Quitting, Browser closing");
			driver.quit();
			System.out.println("ChromeDriver Quit, Browser closed");

		} catch (Exception e) {
			e.printStackTrace();
		}
		// End:Code
	}
 
}
