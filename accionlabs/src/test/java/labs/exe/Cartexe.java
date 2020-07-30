package labs.exe;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import labs.test.Cart;
import labs.utils.GetScreenShot;

public class Cartexe extends Cart{
	
	  
	@BeforeTest(groups = {"Search Product","Login", "Add to Cart", "More", "Smoke", "Regression"}) 
		  public static void Initialize() {	  
		  initialization();
		 
		  }
	
	
	@Test (priority = 1, groups = {"Search Product","Login", "Add to Cart", "More", "Smoke", "Regression"})
	public static void OpenWebPage() {
		test = Report.startTest("Verify openning the Webpage"); 
		OpenURL ();
		
	}
	
	@Test (priority = 2, groups = {"Search Product", "Smoke", "Regression"}) 
	
		public static void ProductSearch() {
		test = Report.startTest("Verify search the product"); 
		SearchProduct();	
	
	

}
	@Test (priority = 3, groups = {"Add to Cart", "Smoke", "Regression"}) 
	
		public static void AddToCart() {
		test = Report.startTest("Verify adding the product to cart"); 		
		addtoCart();
		
}
	@Test (priority = 4, groups = {"More", "Smoke", "Regression"}) 	
		public static void More_info() {
		test = Report.startTest("Verify More about the Product"); 		
		More();
}
	
	@Test (priority = 5, groups = {"Login", "Smoke", "Regression"}) 	
	public static void Login_Error() {
	test = Report.startTest("Verify Login error"); 		
	LoginError();
}
	
	
	
	
	
	
	@AfterMethod( groups = {"Search Product","Login", "Add to Cart", "More", "Smoke", "Regression"})
			  public static void getStatusWithScreenshot(ITestResult result) throws IOException 
			  { 
				  if  (result.getStatus() == ITestResult.FAILURE) 
			  { 
			  String screenShotPath =  GetScreenShot.capture(driver); 
			  test.log(LogStatus.FAIL, result.getThrowable()); 
			  test.log(LogStatus.FAIL, "Snapshot below: " +
			  test.addScreenCapture(screenShotPath)); 
			  
			  
			   } 
				  
			  Report.endTest(test);
			   
			  }
	
	
	 @AfterTest
	 public static void Generate() { 
				  generateReport();			  
			  
			  }
	
	 @AfterClass 
	 public static void close() { 
	 driver.quit(); }
			  
			  }
	
	
	
