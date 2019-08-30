package tests;

import org.testng.annotations.Test;

import utilities.Base;
import utilities.TestUtil;

public class CheckCilckable extends Base {

	@Test
	public void checkClick() {
		System.out.println(TestUtil.checkClickable());
	}
}
