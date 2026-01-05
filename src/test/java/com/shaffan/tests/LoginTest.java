package com.shaffan.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaffan.base.BaseTest;
import com.shaffan.pages.LoginPage;
import com.shaffan.utils.ConfigReader;
import com.shaffan.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("User Management")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

	LoginPage login;

	@DataProvider(name = "invalidLoginData", parallel = true)
	public static Object[][] invalidLoginData() {
		return ExcelUtil.getExcelData("LoginData.xlsx", "Invalid Users");
	}
	
	@Test(dataProvider = "invalidLoginData", description = "Verify OrangeHRM Login with Invalid credentials - 1")
	@Severity(SeverityLevel.BLOCKER)
	@Story("User should not be able to login with invalid credentials")
	public void testLoginWithInvalidCreds(String userName, String password) {
		login = new LoginPage(getDriver());
		login.loginWith(userName, password);
		assertThat(login.isErrorBoxDisplayed()).isTrue();
	}
	
	@Test(description = "Verify OrangeHRM Login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Story("User should be able to login with admin credentials")
	public void testLogin() {
		login = new LoginPage(getDriver());
		String username = ConfigReader.getProperty("username");
		String password = ConfigReader.getProperty("password");
		
		login.loginWith(username, password);
		
		assertThat(getDriver().getCurrentUrl()).contains("dashboard/index");
	}
}
