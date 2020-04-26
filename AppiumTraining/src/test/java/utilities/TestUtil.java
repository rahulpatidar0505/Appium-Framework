package utilities;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;

public class TestUtil extends Base {

	static TouchAction action = new TouchAction(driver);

	public static void scrollToText(String text) {
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
		// UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
	}

	public static void scrollToTextAndClick(String text) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));").click();
	}

	public static int checkClickable() {
		// driver.findElementsByAndroidUIAutomator("new UiSelector().property(value)");
		int size = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size();
		return size;
	}

	public static int checkChecked() {
		int size = driver.findElementsByAndroidUIAutomator("new UiSelector().checked(true)").size();
		return size;
	}

	public static int checkScrollable() {
		int size = driver.findElementsByAndroidUIAutomator("new UiSelector().scrollable(true)").size();
		return size;
	}

	public static int checkEnabled() {
		int size = driver.findElementsByAndroidUIAutomator("new UiSelector().enabled(true)").size();
		return size;
	}

	public static void dragAndDrop(WebElement source, WebElement destination) {
		// t.longPress(longPressOptions().withElement(element(source))).moveTo(element(destination)).release().perform();
		action.longPress(element(source)).moveTo(element(destination)).release().perform();
	}

	public static void tap(WebElement element) {
		action.tap(tapOptions().withElement(element(element))).perform();
	}

	public static void longPress(WebElement element, int time) {
		action.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(time))).release()
				.perform();
	}

	public static void swipe(WebElement swipeFrom, WebElement swipeTo, int time) {
		action.longPress(longPressOptions().withElement(element(swipeFrom)).withDuration(ofSeconds(time)))
				.moveTo(element(swipeTo)).release().perform();
	}

	public static void horizontalScroll(WebElement element, int x, int y) {
		action.longPress(ElementOption.element(element)).moveTo(ElementOption.element(element, x, y)).release()
				.perform();
	}

	public static void scrollIntoWeb() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,480)", "");
	}

	public static void sendkeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	public static void clickOn(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

	public static void setScreenOrientation(String screenOrientationType) {
		if (screenOrientationType.equals("LANDSCAPE")) {
			driver.rotate(ScreenOrientation.LANDSCAPE);
		} else if (screenOrientationType.equals("PORTRAIT"))
			driver.rotate(ScreenOrientation.PORTRAIT);
		else {
			System.out.println("Please provide screenOrientationType");
		}
	}
}
