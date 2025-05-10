package practice.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import practice.pageobjects.CartPage;
import practice.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponent(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[contains(@routerlink,'cart')]")
	WebElement cartButton;
	@FindBy(xpath="//*[contains(@routerlink,'myorders')]")
	WebElement ordersHeader;
	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForElementToDisappear(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPage goToCart() {
		cartButton.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrder() {
		ordersHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
}
