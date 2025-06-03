package Saswatautomation.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Saswatautomation.PageObjects.CartPage;
import Saswatautomation.PageObjects.ProductCatalogue;
import Saswatautomation.TestComponents.BaseTest;
import Saswatautomation.TestComponents.Retry;


public class ErrorValidationsTest extends BaseTest{
	String productName="ZARA COAT 3";
	
	 @Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
     public void loginPageErrorValidation() {
		landingPage.loginApplication("supremesaswat214@gmailc.om", "Anbesivam@123");
        String errorMessage=landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", errorMessage);
		}
	 @Test
	 public void productPageErrorValidation() {
			ProductCatalogue productCatalogue=landingPage.loginApplication("sujit1@gmail.com", "Devil@#2512");
	        productCatalogue.addProductToCart(productName);
	        CartPage cartPage=productCatalogue.goToCartPage();
			boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
			}
	
}  
