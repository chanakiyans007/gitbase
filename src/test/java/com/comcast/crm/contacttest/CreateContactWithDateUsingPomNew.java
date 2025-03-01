package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.ContactInfoPage;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class CreateContactWithDateUsingPomNew {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub

		/* Create Object */
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		// read common data from propertyfile

		// step 3 : get the value based on the key
		String BROWSER = flib.gatDataFromPropertiesFile("browser");
		String URL = flib.gatDataFromPropertiesFile("url");
		String USERNAME = flib.gatDataFromPropertiesFile("username");
		String PASSWORD = flib.gatDataFromPropertiesFile("password");

		// read testscript data from excel file

		String LASTNAME = elib.getDataFromExcel("org", 1, 7) + jlib.getRandomNumber();

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		// WebDriver driver = new ChromeDriver();
		// step :1 login to app
		//driver.get(URL);
		wLib.toMaximize(driver);
		wLib.toWaitForElements(driver);

		// step 1 : Login to app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

		// step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContectLnk().click();

		// step 3 : click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		String stDate = jlib.getSystemDateYYYYDDMM();
		String edDate = jlib.getReguiredDateYYYYDDMM(30);

		// step 4: enter all the details and create organization
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContactWithDate(LASTNAME, stDate, edDate);
		cncp.getSaveBtn().click();

		ContactInfoPage cip = new ContactInfoPage(driver);
		String actConName = cip.getHeaderMsg().getText();
		if(actConName.contains(LASTNAME)) {
			System.out.println(LASTNAME + " is created==== Pass");
		} else {
			System.out.println(LASTNAME + " is  not created==== fail");
		}
		
		String actConName1 = cip.getActConName().getText();
		if(actConName1.equals(LASTNAME)) {
			System.out.println(LASTNAME + " name is Verified ==== PASS");
		} else {
			System.out.println(LASTNAME + " name is not Verified ==== FAIL");
		}

		// verify startDate info expected results
		String actStDate = cip.getActStDate().getText();
		if (actStDate.equals(stDate)) {
			System.out.println(stDate + " startDate is verified ==== pass");
		} else {
			System.out.println(stDate + " startDate is not verified ==== fail");
		}

		// verify endDate info expected results
		String actEdDate = cip.getActEdDate().getText();
		if (actEdDate.equals(edDate)) {
			System.out.println(edDate + " endDate is verified ==== pass");
		} else {
			System.out.println(edDate + " endDate is not verified ==== fail");
		}

		// step 5 : logout
		hp.logOut();
		driver.quit();
	}

}
