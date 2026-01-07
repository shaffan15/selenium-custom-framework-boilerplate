package com.shaffan.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.shaffan.utils.DriverFactory;
import com.shaffan.utils.Helper;

import io.qameta.allure.Attachment;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count  = 0;
	private static final int MAX_RETRY_COUNT = 2;
	
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count < MAX_RETRY_COUNT) {
			
			Helper.saveScreenshotPng(result.getName() + "_Retry_" + count);
			count++;
			return true;
		}
		
		return false;
	}

}
