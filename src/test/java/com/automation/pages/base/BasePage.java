package com.automation.pages.base;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import com.automation.base.BaseTestTG;

public class BasePage {
	private WebDriver driver;
	private WebDriverWait wait = null;
	private Logger mylog = LogManager.getLogger(BasePage.class);
	
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@BeforeMethod
	public static void enterText(WebElement ele,String data,String objectName) {
		if(ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			System.out.println("data is entered in  "+objectName);
		}
		else{
			System.out.println(objectName+" textbox is not diplayed");
		}
	}
	
	public String getTextFromElement(WebElement ele,String objectName) {
		String data=null;
		if(ele.isDisplayed()) {
			data=ele.getText();
			System.out.println("text is extracted from"+objectName);
		}
		else {
			System.out.println(objectName+"textbox is not displayed");
		}
		return data;
	}
	
	public static void clickElement(WebElement ele,String objectName) {
		if(ele.isEnabled()) {
			ele.click();
			System.out.println(objectName+" button is clicked");
		}
		else{
			System.out.println(objectName+" button is not diplayed");
		}
	}
	
	public void selectCheckBox(WebElement ele,String objectName) {
		if(!ele.isSelected()) {
			ele.click();
			System.out.println(objectName+" button is Selected");
		}
		else{
			System.out.println(objectName+" button is already Selected");
		}
	}
	public void waitUntilAlertPresent(int sec) {
		 WebDriverWait wait=new  WebDriverWait(driver,sec);
		 wait.until(ExpectedConditions.alertIsPresent());
		
	}
	public Alert switchToAlert() {
		Alert alert=driver.switchTo().alert();
		System.out.println("switched to an alert");
		return alert;
	}
	public String getAlertText(Alert alert,String objectName) {
		System.out.println("extratcting text in "+objectName+"alert");
		String text=alert.getText();
		System.out.println("text is extracted from alert box="+text);
		return text;
		}
	public void AcceptAlert(Alert alert) {
		alert.accept();
		System.out.println("Alert accepted");
	}
	public void CancelAlert(Alert alert) {
		alert.dismiss();
		System.out.println("Alert cancelled");
	}
	
	public void selectByValueData(WebElement ele,String value) {
		Select select=new Select(ele);
		select.selectByValue(value);
	}
	
	public void selectByTextData(WebElement ele,String value) {
		Select select=new Select(ele);
		select.selectByVisibleText(value);
	}
	public void selectByIndexData(WebElement ele,int value) {
		Select select=new Select(ele);
		select.selectByIndex(value);;
	}
	public WebElement selectFromListUsingText(List<WebElement> list,String text) {
		WebElement element=null;
		for(WebElement i:list) {
			if(i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected="+i.getText());
				element=i;
				break;
			}
		}
		return element;
	}
	
	
	
	public static void selectElement(WebElement ele,String objectName) {
		if(!ele.isSelected()) {
			ele.click();
			System.out.println(objectName+" button is selected");
		}
		else{
			System.out.println(objectName+" button is already selected");
		}
	}
	
	public void mouseOveronElement(WebElement ele,String objectName) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println("cursor moved to web ele "+objectName);
		}
	
	public void ContextClickOnElement(WebElement ele,String objectName) {
		Actions action=new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click performed on  web ele "+objectName);
		}
	
	public void waitForVisibility1(WebElement ele,long timeInSec,String objectName) {
		System.out.println(objectName+"waiting fr visiblity fr max of "+timeInSec+"sec");
		wait=new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.visibilityOf(ele));
		}
	public void waitForAlertToPresent(long timeInSec,String objectName) {
		System.out.println(objectName+"waiting fr visiblity fr max of "+timeInSec+"sec");
		wait=new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.alertIsPresent());
		}
	public void waitForElementToClickable(WebElement ele,long timeInSec,String objectName) {
		System.out.println(objectName+"waiting fr visiblity fr max of "+timeInSec+"sec");
		wait=new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		}
	
	public void waitForVtextToBePresentInElement(WebElement ele,long timeInSec,String text,String objectName) {
		System.out.println(objectName+"waiting fr visiblity fr max of "+timeInSec+"sec");
		wait=new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
		}
	public void waitForVisibility(WebElement ele,int time,int pollingtime,String objectName) {
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(time))
		.pollingEvery(Duration.ofSeconds(pollingtime))
		.ignoring(ElementNotInteractableException.class)
		.withMessage(objectName+"is not visible.fluent wait time expiresis wait fr visibility using fluent wait");
		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println(objectName+"is waited fr visibility using fluent wait");

	}
}
