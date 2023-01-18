package DataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "data-provider")
	public Object[][] dpMethod() {
		return new Object[][] { { "Value Passed" } };
	}

}
