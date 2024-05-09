package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
//define repository for login element
	@FindBy(id="btnreset")
	WebElement objReset;
	@FindBy(name="username")
	WebElement objUser;
	@FindBy(xpath="//input[@id='password']")
	WebElement objPass;
	@FindBy(xpath="//button[@id='btnsubmit']")
	WebElement objLogin;
	//method for Login
	public void adminLogin(String user, String pass) {
		this.objReset.click();
		this.objUser.sendKeys(user);
		this.objPass.sendKeys(pass);
		this.objLogin.click();
	}
}
