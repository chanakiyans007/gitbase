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
import com.comcast.crm.objectrepositoryUtility.CreatingnewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

public class CreateContactAndOrgTestUsingPomNew {

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
		String Orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String contectLastName = elib.getDataFromExcel("org", 1, 7) + jlib.getRandomNumber();

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

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();
		// hp.navigateToCampaignsPage();

		// step 3 : click on create organization button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateOrgBtn().click();

		// step 4: enter all the details and create organization
		CreatingnewOrganizationPage cnop = new CreatingnewOrganizationPage(driver);
		cnop.createOrg(Orgname);
		cnop.getSaveBtn().click();

		// verify headerMsg Expected Results
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actgName = oip.getHeaderMsg().getText();
		if (actgName.contains(Orgname)) {
			System.out.println(Orgname + "name is created==== Pass");
		} else {
			System.out.println(Orgname + "name is  not created==== fail");
		}

		String actOrgName = oip.getActOrgName().getText();
		if (actOrgName.equals(Orgname)) {
			System.out.println(Orgname + "name is Verified ==== PASS");
		} else {
			System.out.println(Orgname + "name is not Verified ==== FAIL");
		}

		// step 5: navigate to contact module

		hp.getContectLnk().click();

		// step 6 : click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step 7: enter all the details and create oation
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(contectLastName);
		cncp.getNewConOrg().click();

		// driver.findElement(By.name("lastname")).sendKeys(contectLastName);

		// driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();

		wLib.switchToTabOnURL(driver, "module=Accounts");
		 cncp.getSearchBar().sendKeys(Orgname);
		 cncp.getSearchBtn().click();

		//driver.findElement(By.name("search_text")).sendKeys(Orgname);
		//driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + Orgname + "']")).click();// dynamic xpath

		wLib.switchToTabOnURL(driver, "Contact&action");

		cncp.getSaveBtn().click();
		
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actConName = cip.getHeaderMsg().getText();
		if(actConName.contains(contectLastName)) {
			System.out.println(contectLastName + " is created==== Pass");
		} else {
			System.out.println(contectLastName + " is  not created==== fail");
		}
		
		String actConName1 = cip.getActConName().getText();
		if(actConName1.equals(contectLastName)) {
			System.out.println(contectLastName + " name is Verified ==== PASS");
		} else {
			System.out.println(contectLastName + " name is not Verified ==== FAIL");
		}
		
		// step 8 : logout
		hp.logOut();
		driver.quit();

	}
}
