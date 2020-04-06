package utilities;

import java.io.File;

public class ConfigUtils {

	public static final int PORT_NUMBER = 4723;
	public static final String URL = "http://127.0.0.1:4723/wd/hub";
	public static final String DEVICE_NAME = "RahulEmulator"; //RahulEmulator //Android Device
	public static final String PLATFORM_NAME = "Android";
	public static final int TIMEOUT = 60;
	public static final String AUTOMATIONNAME = "UiAutomator2";
	public static final String BROWSER = "Chrome";
	public static final String APP_PACKAGE = "io.appium.android.api";
	public static final String APP_ACTIVITY = "io.appium.android.api.ApiDemos";
	// General-Store.apk
	// ApiDemos-debug.apk
	public static final String APP_NAME="General-Store.apk";
//	appPackage and appActivity for calculator
//	"appPackage", "com.miui.calculator"
//	"appActivity","com.miui.calculator.cal.CalculatorActivity"
	public static final String TASK_KILL_COMMAND = "taskkill /F /IM node.exe";

	public static String getApp() {
		File src = new File("src");
		File app = new File(src, APP_NAME);
		String appPath = app.getAbsolutePath();
		return appPath;
	}

}
