package tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utilities.Base;
import utilities.ConfigUtils;
import utilities.TestUtil;

public class ScrollTest extends Base {

	public static void main(String[] args) {

		AndroidDriver<MobileElement> driver = capabilities(ConfigUtils.getApp());
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		TestUtil.scrollToText("WebView");

	}
}
