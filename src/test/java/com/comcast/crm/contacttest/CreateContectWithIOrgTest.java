package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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


public class CreateContectWithIOrgTest {

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
		driver.get(URL);
		wLib.toMaximize(driver);
		wLib.toWaitForElements(driver);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step 2: navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();

		// step 3 : click on create organization button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// step 4: enter all the details and create organization
		driver.findElement(By.name("accountname")).sendKeys(Orgname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String Headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (Headerinfo.contains(Orgname)) {
			System.out.println(Orgname + " header is verified ---passed");
		} else {
			System.out.println(Orgname + "header is not verified ---Failed");

		}

		// step 5: navigate to contacts module
		driver.findElement(By.linkText("Contacts")).click();

		// step 6 : click on create organization button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7: enter all the details and create organization
		driver.findElement(By.name("lastname")).sendKeys(contectLastName);

		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
		
		wLib.switchToTabOnURL(driver, "module=Accounts");
		/*//switch to child window
		Set<String> set =driver.getWindowHandles();
		Iterator<String> it =set.iterator();
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains("module=Accounts")) {
				break;
			}
		}*/
		
		driver.findElement(By.name("search_text")).sendKeys(Orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+Orgname+"']")).click();// dynamic xpath
		
		wLib.switchToTabOnURL(driver, "Contact&action");

	/*	//switch to parent window
		Set<String> set1 =driver.getWindowHandles();
		Iterator<String> it1 =set1.iterator();
		while(it1.hasNext()) {
			String windowID = it1.next();
			driver.switchTo().window(windowID);
			
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains("Contact&action")) {
				break;
			}
		} */
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//header contactname results
			 Headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(Headerinfo.contains(contectLastName)) {
			System.out.println(contectLastName+"---passed");
		}else {
			System.out.println(contectLastName+"---Failed");
		}
		
		//verify header Orgname info expected results 
				String actOrgName= driver.findElement(By.id("mouseArea_Organization Name")).getText();
				System.out.println(actOrgName);
				
				if(actOrgName.trim().equals(Orgname)) {
					System.out.println(Orgname + " info is verified ==== pass");
				}else {
					System.out.println(Orgname + " info is not verified ==== fail");
				}

		// step 5 : logout
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();

	}

}
