package tests;

import org.testng.annotations.Test;

import utilities.Base;
import utilities.TestUtil;

public class CheckProperties extends Base {

	@Test
	public void test() {

		// driver.findElementByAndroidUIAutomator("attribute("value")")
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		System.out.println(TestUtil.checkClickable());
		System.out.println(TestUtil.checkChecked());
		System.out.println(TestUtil.checkEnabled());
		System.out.println(TestUtil.checkScrollable());
	}
}
