package tests;

import Annotations.FrameworkAnnotation;
import baseTest.BaseTest;
import enums.CategoryType;
import enums.ModulePages;
import enums.TestCaseType;

public class Test extends BaseTest {

	@FrameworkAnnotation(category = { CategoryType.SMOKE }, type = TestCaseType.UI, page = ModulePages.MYPROFILEPAGE)
	@org.testng.annotations.Test
	public void main() {

		page.navigate("https://passbook.epfindia.gov.in/MemberPassBook/Login");

	}

}
