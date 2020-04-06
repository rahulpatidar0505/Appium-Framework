package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utilities.Base;
import utilities.TestUtil;

public class LongpressTest extends Base {

	@Test
	public void test() {

		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		WebElement expandList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
		TestUtil.tap(expandList);
		driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
		WebElement pn = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
		TestUtil.longPress(pn, 2);
		System.out.println(driver.findElementById("android:id/title").isDisplayed());

	}
}
