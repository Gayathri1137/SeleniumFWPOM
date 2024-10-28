package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.automation.base.BaseTestTG;
import com.automation.pages.login.LoginPage;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

public class AutomationScript extends BaseTestTG {
	@Test
	public void testValidLogin() {
		String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "password");
		LoginPage page = new LoginPage(driver);
		page.enterUsername(usernameData);
		page.enterPassword(passwordData);
		page.clickLogin();

	}
}
