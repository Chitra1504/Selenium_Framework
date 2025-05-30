package practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	By cart=By.cssSelector("div h1");
	public List<WebElement> getCartProducts() {
		waitForElementToAppear(cart);
		return cartProducts;
	}
	public Boolean productInCart(String productName) {
		Boolean match=getCartProducts().stream().anyMatch(cartProduct->
		cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage goToCheckOut() {
		checkOut.click();
		CheckOutPage checkOutPage=new CheckOutPage(driver);
		return checkOutPage;
	}
}
