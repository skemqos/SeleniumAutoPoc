package com.herokuapp.theinternet;

//import java.util.ArrayList; 

import java.io.File;
import java.io.FileFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.google.common.io.Files;

public class PEMSLoginTests {

	@Parameters({ "username", "password", "expectedMessage" })
	// @Test(priority=1, groups = {"positiveTests", "smokeTests"})
	public void positiveLoginTest(String username, String password, String expectedMessage) {

		// step-1. open webpage using url in a targeted browser, say chrome
		WebDriver driver = openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("UserName"));
		usernameElement.sendKeys(username);
		new Util(50).sleep(); // for demo, not real

		WebElement passwordElement = driver.findElement(By.id("Password")); // Locating WebElement By: name
		passwordElement.sendKeys(password);
		new Util(50).sleep(); // for demo, not real

		WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
		loginButton.click();
		new Util(50).sleep();

		// step-3. validate successful login, after 2-3 validation checks (updated
		// url,logout button visible, successful login message)
		// 3-1. logout button

		WebElement logoutButton = driver.findElement(By.id("print"));
		Assert.assertTrue(logoutButton.isDisplayed(), "PRINT PAGE is visible");
		// WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button
		// secondary radius']"));
		// Assert.assertTrue(logoutButton.isDisplayed(), "logoutButton is visible");
		/*
		 * // 3-2. successful login message: WebElement successMsg =
		 * driver.findElement(By.xpath("//div[@id='flash']"));
		 * 
		 * String expectedMsg = expectedMessage; String actualMsg =
		 * successMsg.getText();
		 * 
		 * Assert.assertTrue(actualMsg.contains(expectedMsg),
		 * "Actual msg does not contain expected message:+\nActual Message:" + actualMsg
		 * + "\nExpected Message:"+ expectedMsg);
		 */
		// 3-3. updated url
		String expectedurl = "http://pems.pemsportal.com/Home/Landing";

		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual page not same as Expected Url");

		// step-4. : Close Chrome Browser Instance (with an optional dealy of 2s)
		closeBrowser(driver);
	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting negativeLoginTests with" + username + "and" + password);

		// step-1. open webpage using url in a targeted browser, say chrome
		// pre-req-1.0: Create chrome driver, specifying its availability in a relative
		WebDriver driver = openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("username")); // Locating WebElement By: id locator
		usernameElement.sendKeys(username);
		WebElement passwordElement = driver.findElement(By.name("password")); // Locating WebElement By: name locator
		passwordElement.sendKeys(password);

		// step-3. click login button
		WebElement loginButton = driver.findElement(By.tagName("button")); // Locating WebElement By: tagName locator
		loginButton.click();
		new Util(50).sleep();

		// 4-2. failed login message: by Id:
		WebElement failedLoginMsg = driver.findElement(By.id("flash"));
		String actualMsg = failedLoginMsg.getText(); // Extracts Text from the web element:
		String expectedMsg = expectedErrorMessage;

		Assert.assertTrue(actualMsg.contains(expectedMsg),
				"Actual msg does not contain expected message:+\nActual Message:" + actualMsg + "\nExpected Message:"
						+ expectedMsg);

		// step-5. : Close Chrome Browser Instance (with an optional dealy of 2s)
		new Util(50).sleep();

		closeBrowser(driver);
	}

	@Parameters({ "username", "password", "expectedMessage" })
	// @Test(priority=1, groups = {"positiveTests", "smokeTests"})
	public void clientSelectTest(String username, String password, String expectedMessage) {

		// step-1. open webpage using url in a targeted browser, say chrome
		WebDriver driver = openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("UserName"));
		usernameElement.sendKeys(username);
		new Util(50).sleep(); // for demo, not real

		WebElement passwordElement = driver.findElement(By.id("Password")); // Locating WebElement By: name
		passwordElement.sendKeys(password);
		new Util(50).sleep(); // for demo, not real

		WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
		loginButton.click();
		new Util(50).sleep();

		// step-3. validate successful login, after 2-3 validation checks (updated
		// url,logout button visible, successful login message)
		// 3-1. logout button

		WebElement logoutButton = driver.findElement(By.id("print"));
		Assert.assertTrue(logoutButton.isDisplayed(), "PRINT PAGE is visible");
		// WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button
		// secondary radius']"));
		// Assert.assertTrue(logoutButton.isDisplayed(), "logoutButton is visible");
		/*
		 * // 3-2. successful login message: WebElement successMsg =
		 * driver.findElement(By.xpath("//div[@id='flash']"));
		 * 
		 * String expectedMsg = expectedMessage; String actualMsg =
		 * successMsg.getText();
		 * 
		 * Assert.assertTrue(actualMsg.contains(expectedMsg),
		 * "Actual msg does not contain expected message:+\nActual Message:" + actualMsg
		 * + "\nExpected Message:"+ expectedMsg);
		 */
		// 3-3. updated url
		String expectedurl = "http://pems.pemsportal.com/Home/Landing";

		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual page not same as Expected Url");

		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.id("LNGTrial4Customer"));
		actions.doubleClick(elementLocator).perform();

		WebElement divElement = driver.findElement(By.id("client"));
		Assert.assertTrue(divElement.isDisplayed(), "LNG Trial 4");

		// step-4. : Close Chrome Browser Instance (with an optional dealy of 2s)
		closeBrowser(driver);
	}

	@Parameters({ "username", "password", "expectedMessage" })
	//@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void inquireEventsTest(String username, String password, String expectedMessage) throws InterruptedException {

		// step-1. open webpage using url in a targeted browser, say chrome
		WebDriver driver = openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("UserName"));
		usernameElement.sendKeys(username);
		new Util(50).sleep(); // for demo, not real

		WebElement passwordElement = driver.findElement(By.id("Password")); // Locating WebElement By: name
		passwordElement.sendKeys(password);
		new Util(50).sleep(); // for demo, not real

		WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
		loginButton.click();
		new Util(100).sleep();

		// step-3. validate successful login, after 2-3 validation checks (updated
		// url,logout button visible, successful login message)
		// 3-1. logout button

		WebElement logoutButton = driver.findElement(By.id("print"));
		Assert.assertTrue(logoutButton.isDisplayed(), "PRINT PAGE is NOT visible");

		// 3-3. validate updated url
		String expectedurl = "http://pems.pemsportal.com/Home/Landing";
		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual page not same as Expected Url");

		// select client
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.id("LNGTrial4Customer"));
		actions.doubleClick(elementLocator).perform();

		Thread.sleep(2000);
		WebElement divElement = driver.findElement(By.id("client"));
		Assert.assertTrue(divElement.isDisplayed(), "Failed to select client: LNG Trial 4");

		WebElement outerElement = driver.findElement(By.linkText("Event Management"));  
			actions.moveToElement(outerElement ).click();
			
		//WebElement innerrElement = driver.findElement(By.cssSelector("div:nth-of-type(4) .submenu  .menu-link"));  
		WebElement innerrElement = driver.findElement(By.xpath("//div[@id='left-navigation']/div[4]//ul[@class='submenu']//a[@href='http://pems.pemsportal.com:80/LNG Trial 4/pems/Events/Index']"));  
		actions.moveToElement(innerrElement ).click().build().perform();
		
		// 3-3. validate updated url
		expectedurl = "http://pems.pemsportal.com/LNG%20Trial%204/pems/Events/Index";
		actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual Event page not same as Expected Url");

		
		WebElement searchElement = driver.findElement(By.id("btnSearch"));
		searchElement.click();
		new Util().sleep(1000);
		//validate event inquiry page load
		WebElement resultElement = driver.findElement(By.linkText("Event Code"));  
		Assert.assertTrue(resultElement.isDisplayed(), "Failed to load Events in PEMS EventInquiry");		
		
		//validate export to xls
		WebElement exportElement = driver.findElement(By.id("exportExcel"));
		
		int initialXlFileCount = getFileCount();
		Thread.sleep(1000);
		exportElement.click();
		new Util().sleep(1000);
		int presentXlFileCount = getFileCount();
		//validate file exported to xls in local drive
		//WebElement resultElement = driver.findElement(By.linkText("Event Code"));  
		//Assert.assertTrue(resultElement.isDisplayed(), "Failed to load Events in PEMS EventInquiry");		
		/*
		 * File f = new
		 * File("C:\\Users\\SK\\Downloads\\Java-Maven-Selenium-TestNG\\SummaryEvents_11_21_2020 4_21_43 AM.xls"
		 * ); Assert.assertTrue(f.exists());
		 */
		 
		
		 int diff = presentXlFileCount - initialXlFileCount ;
		 Assert.assertTrue( diff == 1 );
		 
		// step-4. : Close Chrome Browser Instance (with an optional dealy of 2s)
		Thread.sleep(2000);
		closeBrowser(driver);
	}

	@Parameters({ "username", "password", "expectedMessage" })
	//@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void inquireFileUploadTest(String username, String password, String expectedMessage) throws InterruptedException {

		// step-1. open webpage using url in a targeted browser, say chrome
		WebDriver driver = openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("UserName"));
		usernameElement.sendKeys(username);
		new Util(50).sleep(); // for demo, not real

		WebElement passwordElement = driver.findElement(By.id("Password")); // Locating WebElement By: name
		passwordElement.sendKeys(password);
		new Util(50).sleep(); // for demo, not real

		WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
		loginButton.click();
		new Util(100).sleep();

		// step-3. validate successful login, after 2-3 validation checks (updated
		// url,logout button visible, successful login message)
		// 3-1. logout button

		WebElement logoutButton = driver.findElement(By.id("print"));
		Assert.assertTrue(logoutButton.isDisplayed(), "PRINT PAGE is NOT visible");

		// 3-3. validate updated url
		String expectedurl = "http://pems.pemsportal.com/Home/Landing";
		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual page not same as Expected Url");

		// select client
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.id("LNGTrial4Customer"));
		actions.doubleClick(elementLocator).perform();

		Thread.sleep(2000);
		WebElement divElement = driver.findElement(By.id("client"));
		Assert.assertTrue(divElement.isDisplayed(), "Failed to select client: LNG Trial 4");

		WebElement outerElement = driver.findElement(By.linkText("Asset Configuration"));  
			actions.moveToElement(outerElement ).click();
			
		//WebElement innerrElement = driver.findElement(By.cssSelector("div:nth-of-type(4) .submenu  .menu-link"));  
		WebElement innerElement = driver.findElement(By.xpath("//div[@id='left-navigation']/div[3]//ul[@class='submenu']//a[@href='http://pems.pemsportal.com:80/LNG Trial 4/pems/FileUpload/Index']"));  
		actions.moveToElement(innerElement ).click().build().perform();
		
		// 3-3. validate updated url
		expectedurl = "http://pems.pemsportal.com/LNG%20Trial%204/pems/FileUpload/Index";
		actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual File Upload Inquiry page not same as Expected Url");

		WebElement addFileElement = driver.findElement(By.linkText("Add File"));  
		addFileElement.click();
		new Util().sleep(2000);
		/*
		 * //select dropdown menu web element (at index 0) WebElement webelement
		 * =driver.findElement(By.xpath(
		 * "/html//div[@id='dialog']//div[@class='two-column']//span[@role='listbox']//span[@class='k-input']"
		 * )); //
		 * /html//div[@id='dialog']//div[@class='two-column']//span[@role='listbox']//
		 * span[@class='k-input'] Select select = new Select(webelement); int
		 * selectIndex = 0; //hardcoded select.selectByIndex(selectIndex);
		 */
		
		//pass value of web element (at index 0), a text field, hidden from user    
	 //   WebElement fileTypeElement = driver.findElement(By.xpath("/html//div[@id='dialog']//div[@class='two-column']//span[@role='listbox']//span[@class='k-input']"));
	//	JavascriptExecutor jse = (JavascriptExecutor)driver;
	//	jse.executeScript("document.getElementsByName('fileType')[0].setAttribute('type', 'text');");
		//WebElement elementLocator = driver.findElement(By.id("LNGTrial4Customer"));
		
		/*
		 * Integer obj = new Integer(8); String selectedFileType=obj.toString(16);
		 * //hardcoded driver.findElement(By.xpath("//input[@id='fileType']")).sendKeys(
		 * selectedFileType);
		 */

		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//fileTypeElement.sendKeys(String.valueOf(16));
		/*
		 * List hiddenFormElm = WebUI.findWebElements(findTestObject(‘input_hidden’),
		 * 30); WebUI.executeJavaScript(“arguments[0].value = ‘my form entry’”,
		 * Arrays.asList(hiddenFormElm));
		 */
		new Util().sleep(2000);
		
		/*
		 * WebElement searchElement = driver.findElement(By.id("btnSearch"));
		 * searchElement.click(); new Util().sleep(1000); //validate event inquiry page
		 * load WebElement resultElement =
		 * driver.findElement(By.linkText("Event Code"));
		 * Assert.assertTrue(resultElement.isDisplayed(),
		 * "Failed to load Events in PEMS EventInquiry");
		 * 
		 * //validate export to xls WebElement exportElement =
		 * driver.findElement(By.id("exportExcel"));
		 * 
		 * int initialXlFileCount = getFileCount(); Thread.sleep(1000);
		 * exportElement.click(); new Util().sleep(1000); int presentXlFileCount =
		 * getFileCount();
		 * 
		 * 
		 * int diff = presentXlFileCount - initialXlFileCount ; Assert.assertTrue( diff
		 * == 1 );
		 */
		 
		// step-4. : Close Chrome Browser Instance (with an optional dealy of 2s)
		Thread.sleep(2000);
		closeBrowser(driver);
	}
	
	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public  void inquireAlarmsTest(String username, String password, String expectedMessage) throws InterruptedException {

		// step-1. open webpage using url in a targeted browser, say chrome
		WebDriver driver = openWebBrowser();

		// step-2. enter login details: (username, password, click enter)
		WebElement usernameElement = driver.findElement(By.id("UserName"));
		usernameElement.sendKeys(username);
		new Util(50).sleep(); // for demo, not real

		WebElement passwordElement = driver.findElement(By.id("Password")); // Locating WebElement By: name
		passwordElement.sendKeys(password);
		new Util(50).sleep(); // for demo, not real

		WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
		loginButton.click();
		new Util(100).sleep();

		// step-3. validate successful login, after 2-3 validation checks (updated
		// url,logout button visible, successful login message)
		// 3-1. logout button

		WebElement logoutButton = driver.findElement(By.id("print"));
		Assert.assertTrue(logoutButton.isDisplayed(), "PRINT PAGE is NOT visible");

		// 3-3. validate updated url
		String expectedurl = "http://pems.pemsportal.com/Home/Landing";
		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "Actual page not same as Expected Url");

		// select client
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.id("LNGTrial4Customer"));
		actions.doubleClick(elementLocator).perform();

		Thread.sleep(2000);
		WebElement divElement = driver.findElement(By.id("client"));
		Assert.assertTrue(divElement.isDisplayed(), "Failed to select client: LNG Trial 4");

		WebElement outerElement = driver.findElement(By.linkText("Alarm Management"));  
			actions.moveToElement(outerElement ).click();
			
		//WebElement innerrElement = driver.findElement(By.cssSelector("div:nth-of-type(4) .submenu  .menu-link"));  
			WebElement innerElement = driver.findElement(By.xpath("//div[@id='left-navigation']/div[5]//ul[@class='submenu']//a[@href='http://pems.pemsportal.com:80/LNG Trial 4/pems/Alarms/Index']"));  
		actions.moveToElement(innerElement ).click().build().perform();
		
		// 3-3. validate updated url
		expectedurl = "http://pems.pemsportal.com/LNG%20Trial%204/pems/Alarms/Index";
		actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl, "ActualAlarm Inquiry page not same as Expected Url");
		//
		

		/*
		 * WebElement addFileElement = driver.findElement(By.linkText("Add File"));
		 * addFileElement.click(); new Util().sleep(2000);
		 */
		WebElement select_dropdownlist = driver.findElement(By.xpath("//*[@data-role='dropdownlist' and @data-text-field='Text']"));
		Select dropdownlist = new Select(select_dropdownlist);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(select_dropdownlist));
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		dropdownlist.selectByValue("1");
		//Thread.sleep(3000);
		  //select dropdown menu web element (at index 0) WebElement webelement
		/*
		 * WebElement selectElement =driver.findElement(By.id( "ddlAlarmAssetState" ));
		 * selectElement.click(); Select select = new Select(selectElement);
		 * WebDriverWait wait = new WebDriverWait(driver, 10);
		 */
		//wait.until(ExpectedConditions.visibilityOf(selectElement));
		//Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html//select[@id='ddlAlarmAssetState']")));
		//Wait.until(ExpectedConditions.ElementIsVisible(By.xpath("/html//select[@id='ddlAlarmAssetState']")));

		// select.selectByVisibleText("Operational");
		 
		
		//pass value of web element (at index 0), a text field, hidden from user    
	 //   WebElement fileTypeElement = driver.findElement(By.xpath("/html//div[@id='dialog']//div[@class='two-column']//span[@role='listbox']//span[@class='k-input']"));
	//	JavascriptExecutor jse = (JavascriptExecutor)driver;
	//	jse.executeScript("document.getElementsByName('fileType')[0].setAttribute('type', 'text');");
	

		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//fileTypeElement.sendKeys(String.valueOf(16));
		/*
		 * List hiddenFormElm = WebUI.findWebElements(findTestObject(‘input_hidden’),
		 * 30); WebUI.executeJavaScript(“arguments[0].value = ‘my form entry’”,
		 * Arrays.asList(hiddenFormElm));
		 */
		new Util().sleep(2000);
		
		/*
		 * WebElement searchElement = driver.findElement(By.id("btnSearch"));
		 * searchElement.click(); new Util().sleep(1000); //validate event inquiry page
		 * load WebElement resultElement =
		 * driver.findElement(By.linkText("Event Code"));
		 * Assert.assertTrue(resultElement.isDisplayed(),
		 * "Failed to load Events in PEMS EventInquiry");
		 * 
		 * //validate export to xls WebElement exportElement =
		 * driver.findElement(By.id("exportExcel"));
		 * 
		 * int initialXlFileCount = getFileCount(); Thread.sleep(1000);
		 * exportElement.click(); new Util().sleep(1000); int presentXlFileCount =
		 * getFileCount();
		 * 
		 * 
		 * int diff = presentXlFileCount - initialXlFileCount ; Assert.assertTrue( diff
		 * == 1 );
		 */
		 
		// step-4. : Close Chrome Browser Instance (with an optional dealy of 2s)
		Thread.sleep(2000);
		closeBrowser(driver);
	}
	
	private int getFileCount() {
		File dir = new File("C:\\Users\\SK\\Downloads");
		final String id = "SummaryEvents_"; // needs to be final so the anonymous class can use it
		File[] matchingFiles = dir.listFiles(new FileFilter() {
		    public boolean accept(File pathname) {
		        return pathname.getName().contains(id);
		    }
		});
		return matchingFiles.length;
	}

	public static WebDriver openWebBrowser() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		// System.setProperty("webdriver.chrome.logfile",
		// "src/main/resources/chrome.log" );

		// pre-req-1.1. Create chrome web-driver variable
		System.out.println("Opening Webpage");
		// try {
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		String url = "http://pems.pemsportal.com";
		driver.get(url); // opens webpage for the url
		driver.manage().window().maximize(); // maximizes browser window for visiblity
		// new Util(50).sleep();
		new Util().sleep(100);
		System.out.println("Webpage opened:" + url);
		return driver;
	}

	public static void closeBrowser(WebDriver driver) {
		new Util(50).sleep();
		System.out.println(driver + "\nWebDriver Quitting, Browser closing");
		driver.quit();
		System.out.println(driver + "\nDriver Quit, Browser closed");
	}
}
