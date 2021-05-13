package com.qa.linkedin.test;

import org.testng.annotations.Test;
import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;
import com.qa.linkedin.page.LinkedinLoggedinPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDDrivenTest extends TestBase {

	private Logger log = Logger.getLogger(SearchDDrivenTest.class);
	LinkedinHomePage lHomePage = null;
	LinkedinLoggedinPage llPage = null;
	SearchResultsPage srPage = null;
	String fPath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\linkedin\\data\\searchData.xlsx";

	@BeforeClass
	public void beforeClass() throws InterruptedException, IOException {
		log.debug("initiate all the pages");
		lHomePage = new LinkedinHomePage();
		llPage = new LinkedinLoggedinPage();
		srPage = new SearchResultsPage();

	}

	@Test(description = "call all the home page and do login", priority = 1)
	public void homePageTest() throws InterruptedException, IOException {
		log.debug("Verify the linkedin home page title");
		lHomePage.verifyLinkedinHomePageTitle();
		lHomePage.verifylinkedinLogo();
		log.debug("perform the click on Sign link");
		lHomePage.clickSigninLink();
		lHomePage.verifyLinkedinLoginPageHeading();
		lHomePage.verifyLinkedinLoginPageTitle();
		llPage = lHomePage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
		log.debug("perfoming the people search");
		llPage.verifyLinkedinProfileRailCard();
	}

	@Test(dataProvider = "testData", priority = 2)
	public void searchTest(String str) throws Exception {
		srPage = llPage.doPeopleSearch(str);
		long count = 0;
		try {
			count = srPage.getResultCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("search results count for:" + str + " is:" + count);

		srPage.clickHomeTab();
		Thread.sleep(2000);
	}

	@DataProvider
	public Object[][] testData() throws InvalidFormatException, IOException {

		Object[][] data = new ExcelUtils().getTestData(fPath, "Sheet1");
		return data;
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		log.debug("Perform the logout");
		llPage.doLogout();
	}

}
