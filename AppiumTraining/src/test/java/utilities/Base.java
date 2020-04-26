package utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {

	public static AndroidDriver<MobileElement> driver;
	public static AppiumDriverLocalService service;

	public String randStr = RandomStringUtils.randomAlphabetic(2);
	public String timeStamp = new SimpleDateFormat("dd_MMM_yy_HHmmss").format(Calendar.getInstance().getTime());
	ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
			System.getProperty("user.dir") + "./Reports/MyReport" + "_" + randStr + "_" + timeStamp + ".html");
	ExtentReports extentReports = new ExtentReports();;
	ExtentTest test;

	@BeforeSuite
	public void reportSetup() {

		extentSparkReporter.config().setDocumentTitle("Test Results");
		extentSparkReporter.config().setReportName("Automation Results");
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Author", "Rahul Patidar");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Host Name", "Rahul");
		extentReports.setSystemInfo("Environment", "QA");
	}

	@BeforeTest
	public void killAllNodesStartServerSetCapabilities() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(ConfigUtils.TASK_KILL_COMMAND);
		Thread.sleep(3000);
		startAppiumServer();
		capabilities();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public AppiumDriverLocalService startAppiumServer() {
		boolean flag = checkIfServerIsRunnning(ConfigUtils.PORT_NUMBER);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static AndroidDriver<MobileElement> capabilities() {
		DesiredCapabilities caps = new DesiredCapabilities();
		
		/*These are the optional capabilities that we can pass
		 * 
		 * caps.setCapability(MobileCapabilityType.LOCALE, "IN");
		 * caps.setCapability(MobileCapabilityType.LANGUAGE, "en");
		 * caps.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
		 * caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW, false);
		 * caps.setCapability(MobileCapabilityType.NO_RESET, false);
		 * caps.setCapability(MobileCapabilityType.FULL_RESET, false);
		 * caps.setCapability(MobileCapabilityType.ROTATABLE, true);
		 * caps.setCapability(MobileCapabilityType.ACCEPT_INSECURE_CERTS, true);
		 * caps.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS, true);
		 * caps.setCapability(MobileCapabilityType.APPLICATION_NAME,"Demo Application");
		 * caps.setCapability(MobileCapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, false);
		 * caps.setCapability(MobileCapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, false);
		 * caps.setCapability(MobileCapabilityType.SUPPORTS_SQL_DATABASE, true);
		 * caps.setCapability(MobileCapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
		 * caps.setCapability(MobileCapabilityType.SUPPORTS_JAVASCRIPT, true);
		 * caps.setCapability(MobileCapabilityType.SUPPORTS_LOCATION_CONTEXT, true);
		 * caps.setCapability(MobileCapabilityType.SUPPORTS_WEB_STORAGE,true);
		 * caps.setCapability(MobileCapabilityType.STRICT_FILE_INTERACTABILITY, true);
		 * caps.setCapability(MobileCapabilityType.HAS_NATIVE_EVENTS, false);
		 */
		
		
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigUtils.AUTOMATIONNAME);
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigUtils.DEVICE_NAME);
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigUtils.PLATFORM_NAME);		
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, ConfigUtils.TIMEOUT);
		caps.setCapability(MobileCapabilityType.APP, ConfigUtils.APP_NAME);

		// caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		/*
		 * caps.setCapability("appPackage", "io.appium.android.api");
		 * caps.setCapability("appActivity", "io.appium.android.api.ApiDemos");
		 */
		try {
			URL url = new URL(ConfigUtils.URL);
			driver = new AndroidDriver<MobileElement>(url, caps);

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return driver;
	}

	@BeforeMethod
	public void methodSetup(ITestResult result) {
		test = extentReports.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, "Test started : " + result.getMethod().getMethodName());
	}

	@AfterMethod
	public void methodTeardown(ITestResult result) throws IOException, InterruptedException {

		String temp = captureScreenshot(result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Failed : " + "  " + test.addScreenCaptureFromPath(temp));
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Skipped ");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Passed : " + result.isSuccess());
		}
		test.log(Status.INFO, "Test Ended : " + result.getMethod().getMethodName());
	}

	public static String captureScreenshot(String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName
				+ System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (IOException e) {
			e.getMessage();
		}
		return destination;
	}

	@AfterTest
	public void closeServer() throws IOException {
		boolean flag = checkIfServerIsRunnning(ConfigUtils.PORT_NUMBER);
		if (flag) {
			// This should stop when we are starting from automation code and not manually
			// so commenting for time being
//			service.stop();
		}
		driver.quit();
	}

	@AfterSuite
	public void reportTeardown() {
		extentReports.flush();
	}
}
