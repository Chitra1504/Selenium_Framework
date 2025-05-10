package practice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountry;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectFromDropdown;
	@FindBy(css=".action__submit")
	WebElement submitOrder;
	
	public void enterCountry(String country) {
		Actions a=new Actions(driver);
		a.sendKeys(selectCountry, country).build().perform();
		selectFromDropdown.click();
	}
	public ConfirmationPage order() {
		submitOrder.click();
		return new ConfirmationPage(driver);
	}
	
}
