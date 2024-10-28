package com.automation.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.tests.AutomationScripts;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestTG {
	protected static WebDriver driver=null;
	private WebDriverWait wait = null;
	private Logger mylog = LogManager.getLogger(BaseTestTG.class);
	@BeforeMethod
	 public void setUpBeforeMethod() {
		mylog.info("--setupbeforemethod----");
		 launchBrowser("Chrome");
		 String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "url");
	     goToUrl(url);
	     
	 }
	 @AfterMethod
	    public void tearDownAfterMethod() {
	    	closeDriver();
	    	mylog.info("----teardownaftertestmethod--");
	    }
	
	public static void launchBrowser(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;

		default:
			break;
		}
	}
	
	public void goToUrl(String url)  {
		driver.get(url);
		System.out.println(url + "is entered");
		
		
	}
	
	public void closeDriver() {
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
		
	
	public void takeScreenshot(String filepath) {
		TakesScreenshot screenCapture=(TakesScreenshot) driver;
		File src=screenCapture.getScreenshotAs(OutputType.FILE);
		File destFile=new File(filepath);
		try {
			Files.copy(src, destFile);
			System.out.println("screen captured");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("err occured while taking ss");
		}
		
	}
	

}