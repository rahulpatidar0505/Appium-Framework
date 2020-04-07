package tests;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.Preferences;
import utilities.Base;

public class ApiDemoTest extends Base {


	@Test
	public void apiDemoTest() {
		
		HomePage homePage = new HomePage();
		Preferences preferencesPage = new Preferences();
		
		homePage.Preferences.click();
		
		preferencesPage.dependencies.click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys("Rahul");
		preferencesPage.buttons.get(1).click();

	}

}
