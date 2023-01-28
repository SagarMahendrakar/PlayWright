package playWrightFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties properties;

	private static ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> threadLocalBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return threadLocalPlaywright.get();
	}

	public static Browser getBrowser() {
		return threadLocalBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return threadLocalBrowserContext.get();
	}

	public static Page getPage() {
		return threadLocalPage.get();
	}

	public Page initBrowser(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browserName);

		browserName = "chrome";

		// playwright = Playwright.create();
		threadLocalPlaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
			threadLocalBrowser
					.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			threadLocalBrowser
					.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			threadLocalBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			threadLocalBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		case "edge":
			threadLocalBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;

		default:
			System.out.println("please pass the right browser name......");
			break;
		}

		threadLocalBrowserContext.set(getBrowser().newContext());
		threadLocalPage.set(getBrowserContext().newPage());
		//getPage().navigate(prop.getProperty("url").trim());
		return getPage();

	}

	/**
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_properties() {

		try {
			FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;

	}

	/**
	 * take screenshot
	 * 
	 */

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		// getPage().screenshot(new
		// Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}

}
