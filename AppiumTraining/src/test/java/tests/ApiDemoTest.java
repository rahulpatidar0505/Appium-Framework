package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.Preferences;
import utilities.Base;

public class ApiDemoTest extends Base {

	@Test
	public void apiDemoTest(String input) throws IOException, InterruptedException {

		HomePage h = new HomePage(driver);
		// Constructor of class will be invoked when you create object for the class

		// default constructor be called

		// constructor can be defined with arguments

		// driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();

		// You can call the methods or variables of the class with class object

		h.Preferences.click();

		Preferences p = new Preferences(driver);
		// driver.findElementByXPath("//android.widget.TextView[@text='3. Preference
		// dependencies']").click();
		p.dependencies.click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys("Rahul");
		// driver.findElementsByClassName("android.widget.Button").get(1).click();
		p.buttons.get(1).click();
		service.stop();

	}

}
