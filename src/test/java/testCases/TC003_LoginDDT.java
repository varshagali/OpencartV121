package testCases;
/*
 * Data is valid - login success -test pass -Logout
 * Data is valid - login failed - test fail
 * 
 * Data is invalid - login success - test fail - logout
 * Data is invalid - login failed - test pass
 */

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT  extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")  //getting data providers from different class
	public void verify_loginDDT(String email, String pws, String exp) {
		
		logger.info("****stating TC_003_LoginDDT****");
		
		try {
		//home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		//login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setpassword(pws);
		lp.clickLogin();
				
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
		
		/* Data is valid - login success -test pass -Logout
		     			 - login failed - test fail
		  
		  Data is invalid - login success - test fail - logout
		  				  - login failed - test pass
		*/
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				
				map.clicklogout();
				Assert.assertTrue(true);
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				
				map.clicklogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
			
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("****finished TC_003_LoginDDT****");
		
	}
	
	

}
