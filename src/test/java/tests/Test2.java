package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pages.TestPage;
import playWrightFactory.PlaywrightFactory;

public class Test2 {
	@BeforeTest
	public void setup(String browserName) {
		TestPage a = new TestPage(5);

	}

}
