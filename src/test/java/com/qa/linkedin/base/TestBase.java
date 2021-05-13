package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
//adding logger for this class
	private Logger log=Logger.getLogger(TestBase.class);
	public static WebDriver driver=null;
	public WebDriverWait wait=null;
	public Properties prop=null;
	//reading the data from properties file
		public String readPropertyValue(String key) throws IOException {
			log.info("create object for Properties");
			prop = new Properties();
			log.debug("read the properties file"); 
			try {
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
				log.info("load all the properties");
				prop.load(fis);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			return prop.getProperty(key);
		}
	/**
	*This method launches the browser and opens the application url
	*/
	
		@BeforeSuite
		public void setup() throws IOException {
			log.debug("Started executing the browser launching setup()");
			log.debug("fetch the browser value from properties file");
			String browserName=readPropertyValue("browser");
			
			if(browserName.equalsIgnoreCase("chrome")) {
				log.debug("setting my chrome browser exe file");
				WebDriverManager.chromedriver().setup();
				
				driver=new ChromeDriver();
			}else if(browserName.equalsIgnoreCase("firefox")) {
				log.debug("setting my firefox browser exe file");
				WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();	
				
			}else if(browserName.equalsIgnoreCase("ie")) {
				log.debug("setting my ie browser exe file");
				WebDriverManager.iedriver().setup();
				driver=new InternetExplorerDriver();
			}else if(browserName.equalsIgnoreCase("edge")) {
				log.debug("setting edge browser exe file ");
				driver=new EdgeDriver();
			}
			
			log.debug("maximize the browser");
			driver.manage().window().maximize();
			
			log.debug("add implicit wait ");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.debug("create object for WebDriverWait class");
			wait=new WebDriverWait(driver,30);
			log.debug("Open the application url:"+readPropertyValue("applicationUrl"));
			driver.get(readPropertyValue("applicationUrl"));
			
		}
		/**this method skills the browser*/
		@AfterSuite
		public void tearDown() {
			log.debug("triggering browser closing activity");
			if(driver!=null) {
				driver.close();
			}
		}
	
}
