package practice.Tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import practice.TestComponents.BaseTest;
import practice.TestComponents.Retry;
import practice.pageobjects.ProductCatalogue;


public class ErrorValidations extends BaseTest {
	@Test(groups= {"ErrorValidation"},retryAnalyzer=Retry.class)
	public void errorValidation() {
		landingPage.loginApplication("rrn@gmail.com", "Premam9");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		//Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	@Test(groups= {"ErrorValidation"})
	public void productErrorValidation() {
		String prodName="ZARA COAT";
		ProductCatalogue productCatalogue=landingPage.loginApplication("rrn@gmail.com", "Premam95@");
		WebElement product = productCatalogue.getProductByName(prodName);
		Assert.assertNull(product,"The product is not in the list");
		
	}
}
