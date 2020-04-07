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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {

	public static AndroidDriver<MobileElement> driver;
	public static AppiumDriverLocalService service;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String randStr = RandomStringUtils.randomAlphabetic(2);
	public String timeStamp = new SimpleDateFormat("dd_MMM_yy_HHmmss").format(Calendar.getInstance().getTime());

	public static AndroidDriver<MobileElement> capabilities(String appName) {

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigUtils.AUTOMATIONNAME);
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigUtils.DEVICE_NAME);
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigUtils.PLATFORM_NAME);
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, ConfigUtils.TIMEOUT);
		caps.setCapability(MobileCapabilityType.APP, getApp());

//		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

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


	public static String getApp() {
		File src = new File("src");
		File app = new File(src, ConfigUtils.APP_NAME);
		String appPath = app.getAbsolutePath();
		return appPath;
	}
	
	@BeforeSuite
	public void reportSetup() {
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "./Reports/MyReport" + "_" + randStr + "_" + timeStamp + ".html");
		htmlReporter.config().setDocumentTitle("AutomationTesting Demo Report");
		htmlReporter.config().setReportName("My Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Host Name", "Rahul");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Rahul Patidar");
		extent.attachReporter(htmlReporter);

	}

	@AfterSuite
	public void reportTeardown() {
		extent.flush();
	}

	@BeforeTest
	public void killAllNodesStartServerSetCapabilities() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(ConfigUtils.TASK_KILL_COMMAND);
		Thread.sleep(3000);
		startAppiumServer();
		capabilities(getApp());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeServer() throws IOException {
		boolean flag = checkIfServerIsRunnning(ConfigUtils.PORT_NUMBER);
		if (flag) {
			//This should stop when we are starting from automation code and not manually so commenting for time being
//			service.stop();
		}
		driver.quit();
	}

	@AfterMethod
	public void attachSreenshot(ITestResult result) throws IOException, InterruptedException {

		String temp = Base.captureScreenshot(driver, result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Failed " + test.addScreenCaptureFromPath(temp));
		} /*
			 * else if (result.getStatus() == ITestResult.SKIP) { test.log(Status.SKIP,
			 * "Test Skipped " + test.addScreenCaptureFromPath(temp)); } else if
			 * (result.getStatus() == ITestResult.SUCCESS) { test.log(Status.PASS,
			 * "Passed test " + test.addScreenCaptureFromPath(temp)); }
			 */
	}

	public static void startEmulator() throws IOException, InterruptedException {

		Runtime.getRuntime().exec("E:\\Appium\\Training\\AppiumTraining\\Emulator\\startEmulator.bat");
		Thread.sleep(6000);
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

	public AppiumDriverLocalService startAppiumServer() {
		boolean flag = checkIfServerIsRunnning(ConfigUtils.PORT_NUMBER);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;

	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
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

}
