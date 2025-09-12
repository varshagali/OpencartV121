package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.BasePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	

	 @Test(groups={"Sanity","Master"})
	public void verify_login() {
		
		try {
		logger.info("***starting TC002_LoginTest**** ");
		 
		//home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage,true,"Logn failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("***finished TC002_LoginTest**** ");
	}

}
