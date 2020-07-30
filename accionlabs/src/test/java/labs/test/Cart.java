package labs.test;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import labs.utils.ExtentReport;
import labs.utils.ReadExcel;

public class Cart extends ExtentReport{
	
	
	
	public static void BrowserLaunch () {
		try {
			setProperty("data", "chrome", "Chrome_Path");
			openChrome();
			maximizeWindow();
			implicitlywait(30);
			test.log(LogStatus.INFO, "The chrome browser is opened successfully"); 
		    System.out.println("The chrome browser is opened successfully");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			test.log(LogStatus.INFO, "The chrome browser is is failed to open"); 
			System.out.println("The chrome browser is is failed to open");
		}
		
		
	}
	
	public static void OpenURL () {
	
		try {
			BrowserLaunch();
			driver.get(labs.utils.Read_PropertyFile.ReadFile("data", "AutoURL"));
		 if (driver.getPageSource().equalsIgnoreCase("My Store")){
			 test.log(LogStatus.PASS, "The URL is opened successfully"); 
			  System.out.println("The URL is opened successfully");			 
		 }
		 else 	{
			 test.log(LogStatus.FAIL, "The URL is failed to open"); 
			    System.out.println("The URL is failed to open");		 
		 }			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.FAIL, "The URL is failed to open"); 
		    System.out.println("The URL is failed to open");
		}	
				
	}
	    // Scenario 1: Search a product and validate that the search result is the correct product being displayed
		public static void SearchProduct () {
			
			explicitWaitForClickable(3000, "data", "Search_Section");
			enterTextboxValue("data", "Search_Section", "ReadData", "Product", 1, 1);
			clickWebelement("data", "Search_Button");	
			scrollToElement("data", "Grid_View");
			String ExpectedProductName = ReadExcel.readData("ReadData", "Product", 1, 1);
			String ActualProductName = Xpath("data", "Product_Name").getText();
			if (ActualProductName.equalsIgnoreCase(ExpectedProductName)){
				test.log(LogStatus.PASS, "Correct product being displayed"); 
				  System.out.println("Correct product being displayed");	
			}
			
			else {
				test.log(LogStatus.FAIL, "Incorrect product is displayed"); 
			    System.out.println("Incorrect product is displayed");
				
				
			}
			
			
		}
		// Scenario 2: Scroll down from the homepage and add a product to cart and validate the addition of the product is successful to the cart.
		public static void addtoCart() {
			
			movetoElement("data", "Product_Name");
			clickWebelement("data", "Add_To_Cart"); 
			explicitWaitForClickable(2000, "data", "Continue_Shopping");
			String ProductAdded = Xpath("data", "Product_Added").getText();
			if (ProductAdded.equalsIgnoreCase("Product successfully added to your shopping cart"))
			{
				test.log(LogStatus.PASS, "The product added to the Cart successfully"); 
				  System.out.println("The product added to the Cart successfully");	
			}
			
			else {
				test.log(LogStatus.FAIL, "The product is not added to the cart"); 
			    System.out.println("The product is not added to the cart");
				
				
			}
			clickWebelement("data", "Continue_Shopping");
			threadWait(2000);
			movetoElement("data", "Product_Name");
			}
			
		//Scroll down to a product to a mouse over and click on More, validate the resulting page has the correct information and also the functionality in that page is working by validating increment and decrement of quantity, size and then Add to Cart
		
		public static void More() {
			
			explicitWaitForVisibility(2000, "data", "Product_Name");
			movetoElement("data", "Product_Name");
			clickWebelement("data", "More"); 
			explicitWaitForVisibility(3000, "data", "Product_Name_More");
			String ExpectedProductName = ReadExcel.readData("ReadData", "Product", 1, 1);
			String ActualProductName = Xpath("data", "Product_Name_More").getText();
			if (ActualProductName.equalsIgnoreCase(ExpectedProductName)){
				test.log(LogStatus.PASS, "Correct product being displayed"); 
				  System.out.println("Correct product being displayed");				}
			else {
				test.log(LogStatus.FAIL, "Incorrect product is displayed"); 
			    System.out.println("Incorrect product is displayed");			}
			     movetoElement("data", "Primary_Row");
			     int cal = incrementclick(3);
			     clickWebelement("data","Add_To_Cart");
			     explicitWaitForClickable(2000, "data", "Continue_Shopping");
			     String val=String.valueOf(cal);
			     String value = Xpath("data", "Added_Quantity").getText();
			     
			     if (value.equals(val)) {
			    	 test.log(LogStatus.PASS, "Increment is correct"); 
					  System.out.println("Increment is correct");			    	 
			     }
			     else {
			    	 test.log(LogStatus.FAIL, "Increment is incorrect"); 
					    System.out.println("Increment is incorrect"); 
			    	 
			     }		
					
					  String ProductAdded = Xpath("data", "Product_Added").getText(); if
					  (ProductAdded.
					  equalsIgnoreCase("Product successfully added to your shopping cart")) {
					  test.log(LogStatus.PASS, "The product added to the Cart successfully");
					  System.out.println("The product added to the Cart successfully"); }
					  
					  else { test.log(LogStatus.FAIL, "The product is not added to the cart");
					  System.out.println("The product is not added to the cart");
					  
					  
					  }
					 
			     
			     clickWebelement("data", "Continue_Shopping");
				 threadWait(2000);
			     
		}

		public static int incrementclick(int attempt) {
			int attempts = 1;
			
			while(attempts  < attempt) {
		        try {
		        	Xpath("data", "increment_Button").click();
		           
		        } catch(StaleElementReferenceException e) {

		        }
		        attempts++;
		    }
			
		    return attempt;
		}
			
		
		//Scenario 4: Create a login page failure validation
		public static void LoginError() {
			
			movetoElement("data", "Login");
			clickWebelement("dataq", "Login");
			movetoElement("data", "LoginForm");
			enterTextboxValue("data", "loginid", "ReadData", "Product", 3, 1);
			enterTextboxValue("data", "password", "ReadData", "Product", 4, 1);
			clickWebelement("data", "SignIn");
			explicitWaitForVisibility(3000, "data", "Error");
			String Login = Xpath("data", "Error").getText();
		     
		     if (Login.equalsIgnoreCase("error")) {
		    	 test.log(LogStatus.PASS, "The login page validated successfully for invalid credentials"); 
				  System.out.println("The login page validated successfully for invalid credentials");			    	 
		     }
		     else {
		    	 test.log(LogStatus.FAIL, "Incorrect login page validation"); 
				    System.out.println("Incorrect login page validation"); 
		    	 
		     }	
			
			
		}
		
		
		}
			     
			     



