package com.shaffan.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public static void tearDown() {
		if(tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}
	
	public static void initDriver(String browser, boolean isHeadless) throws RuntimeException {
	
		switch (browser.toLowerCase()) {
		case "chrome":
			if(isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				tlDriver.set(new ChromeDriver(options));
				return;
			}
			tlDriver.set(new ChromeDriver());
			return;
		case "firefox":
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				tlDriver.set(new FirefoxDriver(options));
				return;
			}
			tlDriver.set(new FirefoxDriver());
			return;
		default:
			throw new RuntimeException("Invalid Browser name: " + browser);
		}
	}
	
}
