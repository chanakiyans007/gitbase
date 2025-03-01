package com.comcast.crm.orgtest;

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
import com.comcast.crm.objectrepositoryUtility.CreatingnewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

public class CreateOrgNameWithPhNumUsingPomNew {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
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
		String Orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("org", 1, 5);

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
		driver.get(URL);
		wLib.toMaximize(driver);
		wLib.toWaitForElements(driver);
		// step 1 : Login to app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();
		// hp.navigateToCampaignsPage();

		// step 3 : click on create organization button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateOrgBtn().click();

		// step 4: enter all the details and create organization
		CreatingnewOrganizationPage cnop = new CreatingnewOrganizationPage(driver);
		cnop.createOrgManAndPh(Orgname, phoneNumber);
		cnop.getSaveBtn().click();

		// verify headerMsg Expected Results
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actgName = oip.getHeaderMsg().getText();
		if (actgName.contains(Orgname)) {
			System.out.println(Orgname + "is created==== Pass");
		} else {
			System.out.println(Orgname + "is  not created==== Pass");
		}

		String actOrgName = oip.getActOrgName().getText();
		if (actOrgName.equals(Orgname)) {
			System.out.println(Orgname + "name is Verified ==== PASS");
		} else {
			System.out.println(Orgname + "name is not Verified ==== FAIL");
		}

		// verify header phonenumber info expected results
		String actphNum = oip.getActPhNum().getText();
		if (actphNum.equals(phoneNumber)) {
			System.out.println(phoneNumber + " info is verified==== pass");
		} else {
			System.out.println(phoneNumber + " info is not verified==== fail");
		}

		// step 5 : logout
		hp.logOut();
		driver.quit();

	}

}
