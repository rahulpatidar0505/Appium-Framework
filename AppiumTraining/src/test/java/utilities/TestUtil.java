package utilities;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;

public class TestUtil extends Base {

	static TouchAction action = new TouchAction(driver);

	public static void scrollToText(String text) {
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
		// UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
	}

	public static int checkClickable() {
		// driver.findElementsByAndroidUIAutomator("new UiSelector().property(value)");
		int size = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size();
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

	public static void scrollIntoWeb() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,480)", "");
	}
}
