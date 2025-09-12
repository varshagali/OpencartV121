package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		
		super(driver);
	}
	@FindBy(xpath="//h2[text()='My Account']") //my account page account
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")    //added in step-6
	WebElement logout;
	
	public boolean isMyAccountPageExists() {
		
		try {
			return (msgHeading.isDisplayed());
			
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clicklogout() {
		logout.click(); 
	}



}
