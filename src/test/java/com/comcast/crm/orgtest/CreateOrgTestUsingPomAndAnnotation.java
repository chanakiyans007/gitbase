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
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClasss;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.CreatingnewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

public class CreateOrgTestUsingPomAndAnnotation extends BaseClasss {

	@Test
	public void createOrgTest() throws IOException {

		// read testscript data from excel file
		String Orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

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
			System.out.println(Orgname + "name is Verified ==== PASS");
		} else {
			System.out.println(Orgname + "name is not Verified ==== FAIL");
		}

		String actOrgName = oip.getActOrgName().getText();
		if (actOrgName.equals(Orgname)) {
			System.out.println(Orgname + "is created==== pass");
		} else {
			System.out.println(Orgname + "is not created==== fail");
		}

	}

	@Test
	public void createOrgTestWithPhNumber() throws EncryptedDocumentException, IOException {
		// read testscript data from excel file
		String Orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("org", 1, 5);

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
	}

	@Test
	public void createOrgTestWithDropdowns() throws EncryptedDocumentException, IOException {
		// read testscript data from excel file
		String Orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String Industry = elib.getDataFromExcel("org", 1, 3);
		String Type = elib.getDataFromExcel("org", 1, 4);

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();
		// hp.navigateToCampaignsPage();

		// step 3 : click on create organization button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateOrgBtn().click();

		// step 4: enter all the details and create organization
		CreatingnewOrganizationPage cnop = new CreatingnewOrganizationPage(driver);
		cnop.createOrgDp(Orgname, Industry, Type);
		cnop.getSaveBtn().click();

		// verify headerMsg Expected Results
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actgName = oip.getHeaderMsg().getText();
		if (actgName.contains(Orgname)) {
			System.out.println(Orgname + "name is Verified ==== PASS");
		} else {
			System.out.println(Orgname + "name is not Verified ==== FAIL");
		}

		String actOrgName = oip.getActOrgName().getText();
		if (actOrgName.equals(Orgname)) {
			System.out.println(Orgname + "is created==== pass");
		} else {
			System.out.println(Orgname + "is not created==== fail");
		}

		// verify the dropdown details like industry and type info
		String actIndutry = oip.getActIndustry().getText();
		if (actIndutry.equals(Industry)) {
			System.out.println(Industry + " information is verified ==== pass");
		} else {
			System.out.println(Industry + " information is not verified ==== fail");
		}

		String actType = oip.getActType().getText();
		if (actType.equals(Type)) {
			System.out.println(Type + " information is verified ==== pass");
		} else {
			System.out.println(Type + " information is not verified ==== fail");
		}
	}

	@Test
	public void DeleteOrgTest() throws EncryptedDocumentException, IOException {

		// read testscript data from excel file
		String Orgname = elib.getDataFromExcel("org", 3, 2) + jlib.getRandomNumber();
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
			System.out.println(Orgname + "name is created ==== PASS");
		} else {
			System.out.println(Orgname + "name is not created ==== FAIL");
		}

		String actOrgName = oip.getActOrgName().getText();
		if (actOrgName.equals(Orgname)) {
			System.out.println(Orgname + "name is verified ==== pass");
		} else {
			System.out.println(Orgname + "name is not verified ==== fail");
		}

		// go back to organizations page
		hp.getOrgLnk().click();

		// Search for Organizationname
		cnp.getSearchbarORG().sendKeys(Orgname);
		wLib.toHandleDropdown("Organization Name", cnp.getSearchEdtORG());
		cnp.getSearchBtnORG().click();

		driver.findElement(By.xpath("//a[text()='" + Orgname + "']/../../td[8]/a[text()='del']")).click();
		// In dynamic Webtable select & delete org

		wLib.toHandleConfirmationPopupAndCaptureText(driver);
		System.out.println("yes.., That Created Org Is Deleted");

	}

}
