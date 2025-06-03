package Saswatautomation.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Saswatautomation.PageObjects.CartPage;
import Saswatautomation.PageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findby){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear(WebElement findBy){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
