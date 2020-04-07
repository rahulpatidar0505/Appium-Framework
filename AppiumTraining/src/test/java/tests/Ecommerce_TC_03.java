package tests;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import utilities.Base;
import utilities.TestUtil;

public class Ecommerce_TC_03 extends Base {

	@Test
	public void test() throws InterruptedException {

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		TestUtil.scrollToTextAndClick("Argentina");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);

		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum = 0;
		for (int i = 0; i < count; i++) {
			String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i)
					.getText();
			double amount = getAmount(amount1);
			sum = sum + amount;// 280.97+116.97
		}

		System.out.println(sum + "sum of products");

		String total = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();

		total = total.substring(1);

		double totalValue = Double.parseDouble(total);

		System.out.println(totalValue + "Total value of products");

		Assert.assertEquals(sum, totalValue);

//Mobile Gestures

		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));

		TouchAction t = new TouchAction(driver);

		t.tap(tapOptions().withElement(element(checkbox))).perform();

		WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

		driver.findElement(By.id("android:id/button1")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

	}

	public static double getAmount(String value)

	{

		value = value.substring(1);

		double amount2value = Double.parseDouble(value);

		return amount2value;

	}

}
