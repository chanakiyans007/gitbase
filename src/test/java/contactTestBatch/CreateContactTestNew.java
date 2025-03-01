package contactTestBatch;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClasss;
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


/**
 * 
 * @author chana
 * 
 * 
 */


public class CreateContactTestNew extends BaseClasss {

	@Test(groups = "smokeTest")
	public void createContactTest() throws IOException {

		/*read testscript data from excel file*/
		String LASTNAME = elib.getDataFromExcel("org", 1, 7) + jlib.getRandomNumber();

		// step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContectLnk().click();

		// step 3 : click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step 4: enter all the details and create contact
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(LASTNAME);
		cncp.getSaveBtn().click();

		// verify headerMsg Expected Results
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actConName = cip.getHeaderMsg().getText();
		boolean status = actConName.contains(LASTNAME);
		Assert.assertEquals(status, true);

		String actConName1 = cip.getActConName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actConName1, LASTNAME);
		soft.assertAll();
	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDate() throws EncryptedDocumentException, IOException {
		// read testscript data from excel file

		String LASTNAME = elib.getDataFromExcel("org", 1, 7) + jlib.getRandomNumber();
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
		boolean status = actConName.contains(LASTNAME);
		Assert.assertEquals(status, true);

		String actConName1 = cip.getActConName().getText();
		boolean status1 = actConName1.equals(LASTNAME);
		Assert.assertEquals(status1, true);

		// verify startDate info expected results
		String actStDate = cip.getActStDate().getText();
		boolean status2 = actStDate.equals(stDate);
		Assert.assertEquals(status2, true);

		// verify endDate info expected results
		String actEdDate = cip.getActEdDate().getText();
		boolean status3 = actEdDate.equals(edDate);
		Assert.assertEquals(status3, true);
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException {
		// read testscript data from excel file
		String Orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String contectLastName = elib.getDataFromExcel("org", 1, 7) + jlib.getRandomNumber();
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
		boolean status = actgName.contains(Orgname);
		Assert.assertEquals(status, true);

		String actOrgName = oip.getActOrgName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actOrgName, Orgname);

		// step 5: navigate to contact module
		
		hp.getContectLnk().click();

		// step 6 : click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step 7: enter all the details and create oation
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(contectLastName);
		cncp.getNewConOrg().click();


		wLib.switchToTabOnURL(driver, "module=Accounts");
		cncp.getSearchBar().sendKeys(Orgname);
		cncp.getSearchBtn().click();

		driver.findElement(By.xpath("//a[text()='" + Orgname + "']")).click();// dynamic xpath

		wLib.switchToTabOnURL(driver, "Contact&action");

		cncp.getSaveBtn().click();
		
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actConName11 = cip.getHeaderMsg().getText();
		boolean status111 =actConName11.contains(contectLastName);
		Assert.assertEquals(status111, true);
	}

}
