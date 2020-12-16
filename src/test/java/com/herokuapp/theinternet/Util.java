package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Util {
	
	 int sleeptimer;
	  
	public Util(int sleeptimer) {
 
		this.sleeptimer = sleeptimer;
	}

	 
		public Util() {
			super();
		 
		}

	public int getSleeptimer() {
		return sleeptimer;
	}

	public void setSleeptimer(int sleeptimer) {
		this.sleeptimer = sleeptimer;
	}

	public   void sleep() {
		try {
			Thread.sleep(sleeptimer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public   void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public  void closeBrowser(WebDriver driver) {
		sleep(1000);
		System.out.println(driver+"\nWebDriver Quitting, Browser closing");
		driver.quit();
		System.out.println(driver+"\nDriver Quit, Browser closed");
	}

	public static   WebDriver openWebBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		// System.setProperty("webdriver.chrome.logfile",
		// "src/main/resources/chrome.log" );

		// pre-req-1.1. Create chrome web-driver variable
		System.out.println("Opening Webpage");
		//try {
			WebDriver driver = new ChromeDriver();
			String url = "https://the-internet.herokuapp.com/login";
			driver.get(url);
			driver.manage().window().maximize();
			//sleep(1000);
			System.out.println("Webpage opened:"+url);
		return driver;
	}
}
