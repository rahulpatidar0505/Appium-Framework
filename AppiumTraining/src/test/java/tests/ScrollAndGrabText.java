package tests;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import utilities.Base;
import utilities.TestUtil;

public class ScrollAndGrabText extends Base {

	@Test
	public void test() throws MalformedURLException {
//		driver.scrollTo("Preference").click();
		TestUtil.scrollToText("Preference");
		List<MobileElement> text = driver.findElements(By.id("android:id/text1"));
		System.out.println(text.get(8).getText());
	}

}