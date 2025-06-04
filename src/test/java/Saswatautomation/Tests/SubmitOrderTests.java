package Saswatautomation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Saswatautomation.PageObjects.CartPage;
import Saswatautomation.PageObjects.CheckOutPage;
import Saswatautomation.PageObjects.ConfirmationPage;
import Saswatautomation.PageObjects.OrderPage;
import Saswatautomation.PageObjects.ProductCatalogue;
import Saswatautomation.TestComponents.BaseTest;

//new comments
public class SubmitOrderTests extends BaseTest{
	String productName="ZARA COAT 3";
	
	 @Test(dataProvider="getData",groups= {"purchase"})
     public void SubmitOrder(HashMap<String,String> input) 
	 //public void SubmitOrder(String email,String password,String Product)- if input is coming through Object[][]
     // public void SubmitOrder(HashMap<String,String> input) - if input is is coming through HashMap
     //loginApplication(input.get("email"),input.get("password")
     {
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage=productCatalogue.goToCartPage();
		boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		String confirmmessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}
	
	 @Test(dependsOnMethods= {"SubmitOrder"})
	 public void orderHistoryTest() {
		ProductCatalogue productCatalogue=landingPage.loginApplication("supremesaswat214@gmailc.om", "Anbesivam@123#"); 
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	 }
	 
	 @DataProvider
	 public Object[][] getData() throws IOException {
		 List<HashMap<String,String>> data=
		 super.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Saswatautomation\\data\\PurchaseOrder.json");
		 return new Object[][] {{data.get(0)},{data.get(1)}};
		 
//		 HashMap<String,String> array=new HashMap<String,String>();
//		 array.put("email", "sujit1@gmail.com");
//		 array.put("password", "Devil@#2512");
//		 array.put("product", "ZARA COAT 3");
//		 
//		 HashMap<String,String> array1=new HashMap<String,String>();
//		 array.put("email", "supremesaswat214@gmailc.om");
//		 array.put("password", "Anbesivam@123#");
//		 array.put("product", "ADIDAS ORIGINAL");
//		 
//		 return new Object[][] {{array},{array1}};--Using HashMap
//		 return new Object[][] {{"supremesaswat214@gmailc.om","Anbesivam@123#","ZARA COAT 3"},
//			 {"sujit1@gmail.com","Devil@#2512","ADIDAS ORIGINAL"}};--Using Object Array
	 }
}  
