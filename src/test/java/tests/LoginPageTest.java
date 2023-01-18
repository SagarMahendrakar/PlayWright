package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import constants.AppConstants;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
	HomePage homePage;
	LoginPage loginPage;

	public LoginPageTest() {

		HomePage homePage = new HomePage(page);

	}

	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actLoginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("page act title: " + actLoginPageTitle);
		Assert.assertEquals(actLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test(priority = 3)
	public void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(properties.getProperty("username").trim(),
				properties.getProperty("password").trim()));
	}

}
