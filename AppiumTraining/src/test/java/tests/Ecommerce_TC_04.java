package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.FormPage;
import utilities.Base;
import utilities.TestUtil;

public class Ecommerce_TC_04 extends Base {

	@Test
	public void totalValidation() throws IOException, InterruptedException {

		FormPage formPage = new FormPage();
		formPage.getNameField().sendKeys("hello");
		driver.hideKeyboard();
		formPage.femaleOption.click();
		formPage.getcountrySelection().click();
		TestUtil.scrollToTextAndClick("Argentina");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum = 0;
		CheckoutPage checkOutPage = new CheckoutPage();

		for (int i = 0; i < count; i++) {
			String amount1 = checkOutPage.productList.get(i).getText();
			double amount = getAmount(amount1);
			sum = sum + amount;// 280.97+116.97.
		}
		System.out.println(sum + "sum of products");

		String total = checkOutPage.totalAmount.getText();

		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		System.out.println(totalValue + "Total value of products");
		Assert.assertEquals(sum, totalValue);

	}

	public static double getAmount(String value) {
		value = value.substring(1);
		double amount2value = Double.parseDouble(value);
		return amount2value;

	}

}
