package orgTestBatch;

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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClasss;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.crm.objectrepositoryUtility.CreatingnewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationsPage;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class CreateOrgTestNew extends BaseClasss {

	@Test (groups = "smokeTest")
	public void createOrgTest() throws IOException {

		UtilityClassObject.getTest().log(Status.INFO, "read the data from excel");
		
		// read testscript data from excel file
		String Orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// step 2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();
		// hp.navigateToCampaignsPage();

		// step 3 : click on create organization button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateOrgBtn().click();

		// step 4: enter all the details and create organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new org");
		CreatingnewOrganizationPage cnop = new CreatingnewOrganizationPage(driver);
		cnop.createOrg(Orgname);
		cnop.getSaveBtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, Orgname + "====>Create a new org");

		// verify headerMsg Expected Results
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actgName = oip.getHeaderMsg().getText();
		boolean status = actgName.equals(Orgname);
		Assert.assertEquals(status , true);
	   

		String actOrgName = oip.getActOrgName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actOrgName, Orgname);
		soft.assertAll();
		UtilityClassObject.getTest().log(Status.INFO, "Create a new org");

	}

	@Test (groups = "regressionTest")
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
		boolean status1 = actgName.contains(Orgname);
		Assert.assertEquals(status1, true);

		String actOrgName = oip.getActOrgName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actOrgName, Orgname);
        soft.assertAll();
        
		// verify header phonenumber info expected results
		String actphNum = oip.getActPhNum().getText();
		boolean status2 = actphNum.contains(phoneNumber);
		Assert.assertEquals(status2, true);
	}

	@Test (groups = "regressionTest")
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
		boolean status3 = actgName.contains(Orgname);
		Assert.assertEquals(status3, true);

		String actOrgName = oip.getActOrgName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actOrgName, Orgname);
		soft.assertAll();

		// verify the dropdown details like industry and type info
		String actIndutry = oip.getActIndustry().getText();
		soft.assertEquals(actIndutry, Industry);
		soft.assertAll();
		
		String actType = oip.getActType().getText();
		soft.assertEquals(actType, Type);
		soft.assertAll();
	}

	@Test (groups = "regressionTest")
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
		boolean status5 =actgName.contains(Orgname);
		Assert.assertEquals(status5, true);

		String actOrgName = oip.getActOrgName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actOrgName, Orgname);
		soft.assertAll();

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
