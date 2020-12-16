package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidPassword {

	@Test
	public void invalidLoginTest()  {
//Algoriithm
//step-1. open webpage using url in a targeted browser, say chrome: Create Chrome Browser Instance 
//step-2. enter login details: (username, password, click enter)
//step-3. validate succesful login, after 2-3 validation checks  (updated url, logout button visible, succesful login message, )
// step-4. : Close Chrome Browser Instance
//Start:Code

		//step-1. open webpage using url in a targeted browser, say chrome 
        //pre-req-1.0: Create chrome driver, specifying its availability in a relative path
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe" );
		//System.setProperty("webdriver.chrome.logfile", "src/main/resources/chrome.log" );
		
		//pre-req-1.1. Create chrome web-driver variable
		System.out.println("Opening Webpage");
		try
		{
		WebDriver driver = new ChromeDriver();
		String url = "https://the-internet.herokuapp.com/login"; 
		driver.get(url);  //This API open webpage
		driver.manage().window().maximize();	//Maximize Browser WIndow
		new Util(50).sleep();
		System.out.println("Opened Webpage"); 

		//step-2. enter login details: (username, password, click enter)
		WebElement username = driver.findElement(By.id("username")); //testing find by id locator
		username.sendKeys("tomsmith");
		//Need to extract the value provided in Username
			new Util(50).sleep(); //for demo, not real
						
		WebElement password = driver.findElement(By.name("password")); //testing find by name locator
			password.sendKeys("WrongPassword!");
			new Util(50).sleep(); //for demo, not real
			
		//step-3. click login button
		WebElement loginButton = driver.findElement(By.tagName("button")); //testing find by tagName locator
		loginButton.click();
		new Util(50).sleep();

		//step-4. validate failed login, after 2-3 validation checks  (Failed login message, (and optionally(?)-1 ) NO updated url, (and optionally(?)-2 ) NO logout button visible )
		//4-1. Login button yet again
		//WebElement login2Button = driver.findElement(By.xpath("//button[@class='radius']]"));
		//Assert.assertTrue(login2Button.isDisplayed(), "login2Button is visible");
		
		//4-2. failed login message: by Id: 
		WebElement failedLoginMsg = driver.findElement(By.id("flash"));
		//WebElement successMsg = driver.findElement(By.xpath("//div[@id='flash']")); 
		String expectedMsg = "Your password is invalid!"; //TBD-2
		String actualMsg =failedLoginMsg.getText();
		//Assert.assertEquals(actualMsg,expectedMsg,"Actual message is not same as expected message");
		Assert.assertTrue(actualMsg.contains(expectedMsg), "Actual msg does not contain expected message:+\nActual Message:"+actualMsg + "\nExpected Message:" + expectedMsg);
	
		//4-3. NO updated in url for failed login
		String expectedurl="https://the-internet.herokuapp.com/login";
		
		String actualurl=driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual page(Re-login page) not same as Expected Url"); 
		//Assert.assertEquals(actualurl,url,"Actual page not same as Expected Url"); //demo to validate Assertion actually works

		// step-5. : Close Chrome Browser Instance (with an optional dealy of 2s)
		new Util(50).sleep();
		System.out.println("ChromeDriver Quitting, Browser closing");
        driver.quit();
        System.out.println("ChromeDriver Quit, Browser closed");

		} catch(Exception e) {
			e.printStackTrace();
		}
	//End:Code
		
	}
 
	   
}
