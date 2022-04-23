package com.selenium.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage {
	 WebDriver ldriver;
	
	
	public LoginPage(WebDriver rdriver)
	 {
		 ldriver=rdriver;
		 PageFactory.initElements(rdriver, this);
		 
	 }

			
	 @FindBy(xpath ="//a[contains(text(),'Sign in')]")
	 WebElement signInBtn;
	 
	 @FindBy(id="login-username")
	 WebElement username;
	 
	 @FindBy(id="login-signin")
	 WebElement nxtBtn;
	 
	 @FindBy(id="login-passwd")
	 WebElement password;	
	 @FindBy(xpath ="//a[contains(text(),'Finance')]")
	 WebElement finTab;
	
	
	 public void selectFinTab()
	 {
		 finTab.click();
	 }
	 
	 public void clickSignIn()
	 {
		
		 signInBtn.click();
	 }
	 public void setUserName(String uname)
	 {
		 username.sendKeys(uname);
	 }
	 public void clickNext()
	 {
		 nxtBtn.click();
	 }
	 public void setPassword(String pwd)
	 {
		 password.sendKeys(pwd);
	 }

}
