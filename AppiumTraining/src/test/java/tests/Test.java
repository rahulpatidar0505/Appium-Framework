package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test {

	public static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) {

		File src = new File("src");
		File app = new File(src, "ApiDemos-debug.apk");

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		//caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		/*
		 * caps.setCapability("appPackage", "io.appium.android.api");
		 * caps.setCapability("appActivity", "io.appium.android.api.ApiDemos");
		 */

		try {
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, caps);

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
