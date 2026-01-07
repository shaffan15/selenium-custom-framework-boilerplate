package com.shaffan.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shaffan.utils.WaitUtil;

public class BasePage {

	protected WebDriver driver;
	protected WaitUtil waitUtil;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.waitUtil = new WaitUtil(driver, 10);
	}
	
	protected void type(String text, WebElement element) {
		waitUtil.waitForVisibility(element);
		element.clear();
		element.sendKeys(text);
		System.out.println("Typed '" + text + "' into element '" + element.toString() + "'");
	}
	
	protected void click(WebElement element) {
		waitUtil.waitForClickability(element);
		element.click();
	}
	
	protected String getText(WebElement element) {
		waitUtil.waitForVisibility(element);
		return element.getText();
		
	}
	
	protected boolean isDisplayed(WebElement element) {
		waitUtil.waitForVisibility(element);
		return element.isDisplayed();
	}
}
