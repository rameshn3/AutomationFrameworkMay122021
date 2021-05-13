package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{
//adding logger
	private Logger log = Logger.getLogger(LinkedinHomePage.class);
	
	//constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="nav__logo-link")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement SigninLink;
	
	@FindBy(css="h1.header__content__heading ")
	private WebElement signinHeading;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement SigninBtn;
	
	
	String homePageTitle="LinkedIn: Log In or Sign Up";
	String loginPageTitle="LinkedIn Login, Sign in | LinkedIn";
	
	public void verifyLinkedinHomePageTitle() {
		log.debug("Verifying the linkedin home page title");
		wait.until(ExpectedConditions.titleContains(homePageTitle));
		Assert.assertEquals(driver.getTitle(), homePageTitle);
	}
	
	public void verifyLinkedinLoginPageTitle() {
		log.debug("Verifying the linkedin login page title");
		wait.until(ExpectedConditions.titleContains(loginPageTitle));
		Assert.assertEquals(driver.getTitle(), loginPageTitle);
	}
	
	public void verifylinkedinLogo() {
		log.debug("Verifying the linkedin logo is present or not");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed());
	}
	
	public void verifyLinkedinLoginPageHeading() {
		log.debug("Verifying the linkedin login page heading is present or not");
		wait.until(ExpectedConditions.visibilityOf(signinHeading));
		Assert.assertTrue(signinHeading.isDisplayed());
	}
	
	
	public void clickSigninLink() throws InterruptedException {
		log.debug("performing the click action on signin link");
		click(SigninLink);
		
	}
	
	public void clickSigninButton() throws InterruptedException {
		log.debug("performing the click action on signin button in login page");
		click(SigninBtn);
		
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException {
		log.debug("started executing the doLogin() ");
		log.debug("clear the content in email editbox");
		clearText(emailEditbox);
		log.debug("type the email:"+uname+" in email editbox");
		sendKey(emailEditbox,uname);
		
		log.debug("clear the content in password editbox");
		clearText(passwordEditbox);
		log.debug("type the password:"+pwd+" in password editbox");
		sendKey(passwordEditbox,pwd);
		clickSigninButton();
		return new LinkedinLoggedinPage();
		
	}
	
	
	
}
