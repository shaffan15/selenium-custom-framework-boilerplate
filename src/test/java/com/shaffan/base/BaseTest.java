package com.shaffan.base;


import com.shaffan.utils.ConfigReader;
import com.shaffan.utils.DriverFactory;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

@Listeners(AllureTestNg.class)
public class BaseTest {

	@Parameters({"browser"})
	@BeforeMethod
	public void setup(@Optional String browser) {
		System.out.println("Browser Name: " + browser);
		if(browser == null || browser.isEmpty()) {
			browser = ConfigReader.getProperty("browser");
		}
		
		boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("isHeadless"));
		DriverFactory.initDriver(browser, isHeadless);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String url = ConfigReader.getProperty("url");
		getDriver().get(url);
	}

	@Parameters("closeBrowser")
	@AfterMethod
	public void afterMethod(@Optional("true")String closeBrowser) {
		if(getDriver() != null && "true".equals(closeBrowser))
			DriverFactory.tearDown();
	}
	
	public WebDriver getDriver() {
		return DriverFactory.getDriver();
	}
	
	@Test
	public void test() {
		assertThat(getDriver().getCurrentUrl()).contains("google");
	}
	
}
