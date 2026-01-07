package com.shaffan.listeners;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.shaffan.base.BaseTest;
import com.shaffan.utils.DriverFactory;
import com.shaffan.utils.FileUtil;
import com.shaffan.utils.Helper;
import com.shaffan.utils.TimeUtil;

import io.qameta.allure.Attachment;

public class TestListener implements ITestListener {

	
	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriver();
		
		try {
			if(driver != null) {
				Helper.saveScreenshotPng(result.getName() + "_FAILED_AT_" + TimeUtil.now());
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtil.copyScreenshot(src, result.getName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
}
