package com.shaffan.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shaffan.base.BasePage;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	@FindBy(name = "username")
	WebElement userNameField;
	
	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(css = "button[type='submit']")
	WebElement submitButton;
	
	@FindBy(xpath = "//p[contains(., 'Invalid')]")
	WebElement errorBox;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Entering username: {0}")
    public void enterUsername(String username) {
		type(username, userNameField);
    }

    @Step("Entering password: {0}")
    public void enterPassword(String password) {
    	type(password, passwordField);
    }

    @Step("Clicking on Login button")
    public void clickSubmit() {
    	click(submitButton);
    }
	
    @Step("Logging into OrangeHRM with username {0} and password {1}")
	public void loginWith(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickSubmit();
	}
	
    public boolean isErrorBoxDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	wait.until(ExpectedConditions.visibilityOf(errorBox));
    	return errorBox.isDisplayed();
    }
    
}
