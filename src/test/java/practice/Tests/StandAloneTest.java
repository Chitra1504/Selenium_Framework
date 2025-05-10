package practice.Tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import practice.pageobjects.LandingPage;
public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prodName="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage lp=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("rrn@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Premam95@");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.xpath("//*[contains(@class,'mb-3')]"));
		//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3");
		/*for(WebElement product:products) {
			String productName=product.findElement(By.cssSelector("b")).getText();
			if(productName.equals("BANARSI SAREE"))
				product.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
		}*/
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.xpath("//*[contains(@routerlink,'cart')]")).click();
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->
		cartProduct.getText().equalsIgnoreCase(prodName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		/*driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		List<WebElement> countries=driver.findElements(By.cssSelector("div section span"));
		WebElement cnt=countries.stream().filter(country->
		country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		cnt.click();
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String orderId=driver.findElement(By.cssSelector(".em-spacer-1 label.ng-star-inserted")).getText();
		System.out.println(orderId);*/
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
