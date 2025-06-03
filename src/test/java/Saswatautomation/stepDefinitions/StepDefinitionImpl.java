package Saswatautomation.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import Saswatautomation.PageObjects.CartPage;
import Saswatautomation.PageObjects.CheckOutPage;
import Saswatautomation.PageObjects.ConfirmationPage;
import Saswatautomation.PageObjects.LandingPage;
import Saswatautomation.PageObjects.ProductCatalogue;
import Saswatautomation.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;
    public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage=launchApplication();	
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password) {
		productCatalogue=landingPage.loginApplication(username,password);
    }
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		cartPage=productCatalogue.goToCartPage();
		boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage=checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation page")
	public void message_is_displayed_on_Confirmation_page(String string) {
		String confirmmessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		String errorMessage=landingPage.getErrorMessage();
        Assert.assertEquals(string, errorMessage);
        driver.close();
	}
}
