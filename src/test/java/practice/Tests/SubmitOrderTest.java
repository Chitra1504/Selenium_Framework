package practice.Tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practice.TestComponents.BaseTest;
import practice.pageobjects.CartPage;
import practice.pageobjects.CheckOutPage;
import practice.pageobjects.ConfirmationPage;
import practice.pageobjects.OrderPage;
import practice.pageobjects.ProductCatalogue;
public class SubmitOrderTest extends BaseTest {
	String prodName="ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"PurchaseOrder"})
	//public void submitOrder(String email,String password,String productName) throws IOException {
		// TODO Auto-generated method stub
	public void submitOrder(HashMap<String,String> input) throws IOException {	
		//ProductCatalogue productCatalogue=landingPage.loginApplication(email, password);
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		//productCatalogue.addToCart(productName);
		productCatalogue.addToCart(input.get("product"));

		CartPage cartPage=productCatalogue.goToCart();
		
		//Boolean match=cartPage.productInCart(productName);
		Boolean match=cartPage.productInCart(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		
		checkOutPage.enterCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.order();
		Assert.assertTrue(confirmationPage.getOrder().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	@Test(dataProvider="getData",dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest(HashMap<String,String> input) {
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrder();
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("product")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//practice//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	/*	@DataProvider
	public Object[][] getData(){
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email","rrn@gmail.com");
		map.put("password","Premam95@");
		map.put("product", "ZARA COAT 3");
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email","shetty@gmail.com");
		map1.put("password","Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		//return new Object[][] {{map},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
		return new Object[][] {{map},{map1}};
	}*/
}
