package com.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class LoginPage extends BasePage{
	WebDriver driver;
	  @FindBy(id = "username")WebElement usernameInput;
	    @FindBy(id = "password")WebElement passwordInput;
	    @FindBy(id = "Login")WebElement loginButton;
	    
	    public LoginPage(WebDriver driver) {
	    	 //PageFactory.initElements(driver, this);
	    	super(driver);
	    }
	    
	    public void enterUsername(String username) {
	        enterText(usernameInput, username, "Username");
	    }

	    public void enterPassword(String password) {
	        enterText(passwordInput, password, "Password");
	    }

	    public void clickLogin() {
	        clickElement(loginButton, "Login Button");
	    }
}

