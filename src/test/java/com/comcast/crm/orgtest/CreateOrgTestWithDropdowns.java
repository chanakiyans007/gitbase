package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgTestWithDropdowns {

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
		
		String Industry =elib.getDataFromExcel("org", 1, 3);
		String Type =elib.getDataFromExcel("org", 1, 4);
		

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

		wLib.toHandleDropdown(driver.findElement(By.name("industry")), Industry);
		wLib.toHandleDropdown(driver.findElement(By.name("accounttype")), Type);
		// dropdowns handling check properly
	/*	WebElement drop1 = driver.findElement(By.name("industry"));
		Select sel1 = new Select(drop1);
		sel1.selectByVisibleText(Industry);*/

	/*	WebElement drop2 = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(drop2);
		sel2.selectByVisibleText(Type);*/

		// save option
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/*
		 * String Headerinfo =
		 * driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 * if(Headerinfo.contains(Orgname)) {
		 * System.out.println(Headerinfo+"---passed"); }else {
		 * System.out.println(Headerinfo+"---Failed"); }
		 * 
		 * //verify header Orgname info expected results String actOrgname=
		 * driver.findElement(By.id("dtlview_Organization Name")).getText();
		 * if(actOrgname.equals(Orgname)) { System.out.println(Orgname +
		 * "is created==== pass"); }else { System.out.println(Orgname +
		 * "is not created==== fail"); }
		 */

		// verify the dropdown detailos like industry and type info
		String actIndutry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndutry.equals(Industry)) {
			System.out.println(Industry + " information is verified ==== pass");
		} else {
			System.out.println(Industry + " information is not verified ==== fail");
		}

		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(Type)) {
			System.out.println(Type + " information is verified ==== pass");
		} else {
			System.out.println(Type + " information is not verified ==== fail");
		}

		// step 5 : logout
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();

	}

}
