package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationPage extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void verify_account_registration() {
        
        logger.info("*** Started TC001_AccountRegistrationPage ***");
        
        try {
            // Navigate to Home Page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount link");
            
            hp.clickRegister();
            logger.info("Clicked on Register link");
            
            // Fill Account Registration Form
            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
            regPage.setfirstname(randomString().toUpperCase());
            regPage.setlastname(randomString().toUpperCase());
            regPage.setemail(randomString() + "@gmail.com");
            regPage.setTelephone(randomString().indent(123445667));
            
            // Generate random password and enter
            String password = randomAlphaNumberic();
            regPage.setpassword(password);
            
            String passwordconfirm = randomAlphaNumberic();
            regPage.setConfirmP(passwordconfirm);
            
            // Accept privacy policy and continue
            regPage.setprivacypolicy();
            regPage.setClickcontinue();
            
            // Validate confirmation message
            logger.info("Validating expected message");
            String confmsg = regPage.getConfirmationMsg();
            
            Assert.assertEquals(confmsg, "Your Account Has Been Created!", 
                                "Test Failed: Confirmation message mismatch");
            
            logger.info("*** Finished TC001_AccountRegistrationPage Successfully ***");
        } 
        catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail();
        }
    }
}