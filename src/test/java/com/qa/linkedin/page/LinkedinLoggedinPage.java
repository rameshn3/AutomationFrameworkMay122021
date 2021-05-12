package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{

	private Logger log = Logger.getLogger(LinkedinLoggedinPage.class);
	
	//constructor
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	private WebElement profilerailCard;

	@FindBy(xpath="//*[@class='global-nav__me-photo ember-view']")
	private WebElement profileImageIcon;
	
	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;
	
	@FindBy(xpath="//input[contains(@class,'search-global-typeahead')]")
	private WebElement searchEditbox;
	
	public void verifyLinkedinProfileRailCard() {
		log.debug("Verifying the linkedin loggedin page profile rail card is present or not");
		wait.until(ExpectedConditions.visibilityOf(profilerailCard));
		Assert.assertTrue(profilerailCard.isDisplayed());
	}

	public void doLogout() throws InterruptedException {
		log.debug("started logout function");
		click(profileImageIcon);
		click(signOutLink);
		log.debug("Logout operation has finished");	
		
	}
	
public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
	
	log.debug("started performing the search for:"+keyword);
	clearText(searchEditbox);
	
	log.debug("type the "+keyword+" in search editbox");
	sendKey(searchEditbox,keyword);
	
	Thread.sleep(1000);
	log.debug("Press the ENTER key");
	searchEditbox.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	
	return new SearchResultsPage();
}
	
	
	
}
