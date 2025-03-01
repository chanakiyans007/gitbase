package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClasss;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

/**
 * test class for contact module
 * @author chana
 * 
 */
public class SearchContactTest  extends BaseClasss{
	
	/**
	 * Scenario ===> login() ===> navigateContact ===> createContact ===> Verify
	 * 
	 */
	
	
	@Test
	public void searchContactTest() {
		//step 1 : login to app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
		
	}

}
