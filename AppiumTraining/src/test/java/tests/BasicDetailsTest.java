package tests;

import org.testng.annotations.Test;

import utilities.Base;

public class BasicDetailsTest extends Base {

	@Test
	public void test() {

		System.out.println(driver.currentActivity());
		System.out.println(driver.getContext());
		System.out.println(driver.getOrientation());
		System.out.println(driver.isDeviceLocked());
		System.out.println(driver.getBatteryInfo());
		System.out.println(driver.getCapabilities());
		System.out.println(driver.isBrowser());
		System.out.println(driver.getRemoteAddress());
		System.out.println(driver.getStatus());
		System.out.println(driver.getSessionId());
		
		//driver.hideKeyboard();
		//driver.findElementByAndroidUIAutomator("attribute("value")")
	}
}
