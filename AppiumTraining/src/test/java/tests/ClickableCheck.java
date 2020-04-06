package tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utilities.Base;
import utilities.ConfigUtils;
import utilities.TestUtil;

public class ClickableCheck extends Base {

	public static void main(String[] args) {

		AndroidDriver<MobileElement> driver = capabilities(ConfigUtils.getApp());
		// driver.findElementByAndroidUIAutomator("attribute("value")")
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		System.out.println(TestUtil.checkClickable());
	}
}
