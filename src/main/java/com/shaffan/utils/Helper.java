package com.shaffan.utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

public class Helper {

	
	@Attachment(value = "Screenshot: {0}", type = "image/png")
	public static byte[] saveScreenshotPng(String name) {
		return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
	public static void takeScreenshot(String fileName, WebDriver driver) {
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String screenShotPath = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
			
			FileUtil.copyFile(src, new File(screenShotPath));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
