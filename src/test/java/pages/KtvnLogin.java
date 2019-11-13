package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KtvnLogin {
	
	@FindBy(id="formHorizontalEmail")
	public WebElement userName;
	
	
	@FindBy(id="formHorizontalPassword")
	public WebElement passWord;
	
	@FindBy(xpath="//div//a[@class='col-login__btn']")
	public WebElement btnLogin;
	
	
	@FindBy(xpath="//div//span//a[@class='navbar-brand']")
	public WebElement titleParentPortal;
	
	public void ktvnTestLogin(WebDriver driver) {
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	// Set username in textbox
	public void setUsername(String strUsername) {
		userName.sendKeys(strUsername);
	}
	
	// Set password in textbox
	public void setPassword(String strPassword) {
		passWord.sendKeys(strPassword);
	}
	
	// Click on Login button
	public void clickLogin() {	
		btnLogin.click();
	}
	
	// Get login text title
	public String getParentPortalTitle() {	
		return titleParentPortal.getText();	
	}
	
	public void loginToKtvnTest(String strtUsername,String strPassword) {
		// Fill username
		setUsername(strtUsername);
		// Fill password
		setPassword(strPassword);
		//Click Login button
		clickLogin();
	}
}
