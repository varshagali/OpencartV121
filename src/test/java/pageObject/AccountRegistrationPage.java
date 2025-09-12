package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	WebDriver driver;
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	

@FindBy(xpath="//input[@id='input-firstname']")
WebElement textfirstName;
@FindBy(xpath="//input[@id='input-lastname']") 
WebElement textlastName;
@FindBy(xpath="//input[@id='input-email']")
WebElement textEMail;
@FindBy(xpath="//input[@id='input-telephone']")
WebElement textnum;

@FindBy(xpath="//input[@id='input-password']") 
WebElement textpassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement textconfirm;
@FindBy(xpath="//input[@name='agree']")
WebElement chkagree;
@FindBy(xpath="//button[normalize-space()='Continue']") 
WebElement btncontinue;
@FindBy(xpath="//h1[normalize-space()='your account has been created']")
WebElement msgconfirmation;


public void setfirstname(String fname) {
	textfirstName.sendKeys(fname);
}

public void setlastname(String lname) {
	textlastName.sendKeys(lname);
}

public void setemail(String email) {
	textEMail.sendKeys(email);
}
public void setTelephone(String telephone) {
	textnum.sendKeys(telephone);
}

public void setpassword(String pws) {
	textpassword.sendKeys(pws);
}
public void setConfirmP(String Confirmpws) {
	textconfirm.sendKeys(Confirmpws);
}

public void setprivacypolicy() {
	chkagree.click();
}

public void setClickcontinue() {
	//sol-1
	btncontinue.click();
	//sol-2
	//btncontinue.submit();
}

public String getConfirmationMsg() {
	try {
		return (msgconfirmation.getText());
	}
	catch(Exception e){
		return (e.getMessage());
		
	}
}




}
