package tests;

import org.testng.annotations.Test;

import utilities.Base;
import utilities.TestUtil;

public class ScrollTest extends Base {

	@Test
	public void test() {

		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		TestUtil.scrollToText("WebView");

	}
}
