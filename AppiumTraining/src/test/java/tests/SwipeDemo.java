package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utilities.Base;
import utilities.TestUtil;

public class SwipeDemo extends Base {

	@Test
	public void swipeDemo() {

		test = extent.createTest("MyFirstTest", "Sample description");
		test.info("==============Test started=============");
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
		driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
		driver.findElementByXPath("//*[@content-desc='9']").click();

		// long press //on element// 2 sec// move to another element and you release
		WebElement first = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement second = driver.findElementByXPath("//*[@content-desc='45']");
		TestUtil.swipe(first, second, 2);
	}

}
