package Saswatautomation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Saswatautomation.abstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	public boolean verifyProductDisplay(String productName) {
		boolean match=productTitles.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() {
		checkOutEle.click();
		CheckOutPage checkOutPage=new CheckOutPage(driver);
		return checkOutPage;
	}
	}
