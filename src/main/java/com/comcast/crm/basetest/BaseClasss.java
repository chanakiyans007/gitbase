package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.text.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class BaseClasss {
    public WebDriver driver = null;
    public static WebDriver sdriver = null;
	public DatabaseUtility dblib = new DatabaseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	
	
	
	@BeforeSuite (groups = {"smokeTest" , "regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("====Connect to DB , Report Config====");
		dblib.getDbconnection();
		
		
	}
	
	@Parameters ("BROWSER") //this is using only suite file execution time not for other time
	@BeforeClass (alwaysRun=true , groups = {"smokeTest" , "regressionTest"})
	public void configBC(@Optional ("BROWSER") String browser) throws IOException {
		System.out.println("===Launch The BROWSER===");
		String BROWSER = browser;
		BROWSER = flib.gatDataFromPropertiesFile("browser");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
			
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}

    /*@Parameters ("BROWSER") //this is using only suite file execution time not for other time
	@BeforeClass (groups = {"smokeTest" , "regressionTest"})
	public void configBC(@Optional ("BROWSER") String browser) throws IOException {
		System.out.println("===Launch The BROWSER===");
		String BROWSER = browser;
		BROWSER = flib.gatDataFromPropertiesFile("browser");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
			
		}
		sdriver = driver;
	}*/
	
	@BeforeMethod (groups = {"smokeTest" , "regressionTest"})
	public void configBM() throws IOException {
		System.out.println("==Login==");
		String URL = flib.gatDataFromPropertiesFile("url");
		String USERNAME = flib.gatDataFromPropertiesFile("username");
		String PASSWORD = flib.gatDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		wLib.toMaximize(driver);
		wLib.toWaitForElements(driver);
		
	}
	
	@AfterMethod (groups = {"smokeTest" , "regressionTest"})
	public void configAM() {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		hp.logOut();
		
	}
	
	@AfterClass (groups = {"smokeTest" , "regressionTest"})
	public void configAC() {
		System.out.println("===Close The BROWSER===");
		driver.quit();
		
	}
	
	@AfterSuite (groups = {"smokeTest" , "regressionTest"})
	public void configAS() {
		System.out.println("====Close To DB , Report Backup====");
		dblib.closeDbconnection();
		//report.flush();
	}	
	

}