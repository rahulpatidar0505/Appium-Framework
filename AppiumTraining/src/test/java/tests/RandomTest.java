package tests;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utilities.Base;
import utilities.ConfigUtils;

public class RandomTest extends Base {

	public static void main(String[] args) {

		AndroidDriver<MobileElement> driver = capabilities(ConfigUtils.getApp());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		System.out.println(driver.currentActivity());
		System.out.println(driver.getContext());
		// views - Native , Hybrid, Webview
		System.out.println(driver.getOrientation());
		System.out.println(driver.isDeviceLocked());
		// driver.hideKeyboard();
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		
	}
}
