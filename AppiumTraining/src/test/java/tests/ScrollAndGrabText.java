package tests;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utilities.Base;
import utilities.ConfigUtils;

public class ScrollAndGrabText extends Base {

	@Test
	public void test() throws MalformedURLException {
		AndroidDriver<MobileElement> driver = capabilities(ConfigUtils.getApp());
//		driver.scrollTo("Preference").click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Preference\"));");
		List<MobileElement> b = driver.findElements(By.id("android:id/text1"));
		System.out.println(b.get(8).getText());
	}

}