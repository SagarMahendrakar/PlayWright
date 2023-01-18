package baseTest;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;

import pages.HomePage;
import pages.LoginPage;
import playWrightFactory.PlaywrightFactory;

public class BaseTest {

	PlaywrightFactory playwrightFactory;
	public Page page;
	protected Properties properties;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		playwrightFactory = new PlaywrightFactory();

		properties = playwrightFactory.init_properties();

		if (browserName != null) {

			properties.setProperty("browser", browserName);
		}

		page = playwrightFactory.initBrowser(properties);

	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
