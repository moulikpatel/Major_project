package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class MainTestPage extends BaseClass {
	
	
	@Test
	public void validateTitle() {
		String Title=driver.getTitle();
		String actualTitile="trivago.in - Compare hotel prices worldwide";
		boolean isValid=Title.equals(actualTitile);
		Assert.assertEquals(isValid, true);
	}
	@Test 
	public void validateLoginFunctionality() {
		
	}
	@Test
	public void validateSearchResults() {
		
	}
	@Test
	public void validateSocialMediaLinks() {
		
	}

	
	
}
