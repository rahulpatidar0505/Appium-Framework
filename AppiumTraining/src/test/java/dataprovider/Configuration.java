package dataprovider;

import java.io.File;

public class Configuration {

	public static final int PORT_NUMBER = 4723;
	public static final String URL = "http://127.0.0.1:4723/wd/hub";
	public static final String DEVICE_NAME = "Android Device";
	public static final String PLATFORM_NAME = "Android";
	public static final int TIMEOUT = 60;
	public static final String AUTOMATIONNAME = "UiAutomator2";
	public static final String BROWSER="Chrome";
	public static final String APP_PACKAGE="io.appium.android.api";
	public static final String APP_ACTIVITY="io.appium.android.api.ApiDemos";
	public static final String TASK_KILL_COMMAND="taskkill /F /IM node.exe";
	
	public static String getApiDemoAPP() {
		File src = new File("src");
		File app = new File(src, "ApiDemos-debug.apk");
		String apiDemoApkPath=app.getAbsolutePath();
		return apiDemoApkPath;
	}

}
