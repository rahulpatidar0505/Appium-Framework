package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.Base;
import utilities.TestUtil;

public class DragDropTest extends Base {

	@Test
	public void dragDropTest() {

		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();
		WebElement source = driver.findElementsByClassName("android.view.View").get(0);
		WebElement destination = driver.findElementsByClassName("android.view.View").get(1);
		TestUtil.dragAndDrop(source, destination);
		Assert.assertFalse(true);
	}

}
