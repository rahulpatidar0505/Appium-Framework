package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utilities.Base;
import utilities.ConfigUtils;

public class AppBrowserTest extends Base {

	public static void main(String[] args) {

		AndroidDriver<MobileElement> driver = capabilities(ConfigUtils.getApp());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		/*
		 * driver.get("http://facebook.com");
		 * driver.findElementByXPath("//*[@id='u_0_1']/div[1]/div/input").sendKeys(
		 * "qwerty"); driver.findElementByName("pass").sendKeys("12345");
		 * driver.findElementByXPath("//button[@value='Log In']").click();
		 */

		driver.get("http://cricbuzz.com");
		driver.findElementByXPath("//a[@href='#menu']").click();
		driver.findElementByCssSelector("a[title='Cricbuzz Home']").click();
		System.out.println(driver.getCurrentUrl());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,480)", "");
		Assert.assertTrue(
				driver.findElement(By.xpath("//h4[text()='Top Stories']")).getAttribute("class").contains("header"));

	}
}
