package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test2 {

	public static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) {
		DesiredCapabilities caps = new DesiredCapabilities();

		// caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		// caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		/*
		 * caps.setCapability(MobileCapabilityType.UDID, "54fb9e9b0903");
		 * caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		 * caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		 */
		// caps.setCapability(MobileCapabilityType.BROWSER_VERSION, "");
		caps.setCapability(MobileCapabilityType.APP,
				"E:\\Appium\\Training\\AppiumTraining\\apk files\\ApiDemos-debug.apk");

		// caps.setCapability("appPackage", "com.android.calculator2");
		// caps.setCapability("appActivity","com.android.calculator2.Calculator");

		try {
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, caps);

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
