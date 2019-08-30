package tests;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utilities.Base;
import utilities.TestUtil;

public class ClickableCheck extends Base {

	public static void main(String[] args) {

		AndroidDriver<MobileElement> driver = utilities();
		System.out.println("======================Testing=========================");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// driver.findElementByAndroidUIAutomator("attribute("value")")

		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

		// driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

		// Validate clickable feature for all options
		// driver.findElementsByAndroidUIAutomator("new UiSelector().property(value)");

		System.out.println(TestUtil.checkClickable());
	}
}
