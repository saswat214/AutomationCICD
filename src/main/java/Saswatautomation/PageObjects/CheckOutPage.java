package Saswatautomation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Saswatautomation.abstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
        
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
	Actions a=new Actions(driver);
	a.sendKeys(country, countryName).build().perform();
	super.waitForElementToAppear(results);
	selectCountry.click();
}
	
	public ConfirmationPage submitOrder() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", submit);
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
	}
}
