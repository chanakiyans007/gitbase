package practice.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenshot {

	
	@Test
	public void amazonTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
	/*	//step-1 : create an object to EventFiring WebDriver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		
		//step-2 : use getScreenshot method to get file type of screenshot
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		
		//step-3 : store screen on local driver
		FileUtils.copyFile(srcFile, new File("./screenshot/test.png")); */
		
		
        //to take ScreenShot Of webelement
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		 File src = new File("./screenshot/Instagram.png");
		 FileHandler.copy(temp, src);

		//To Take ScreenShot Of Webpage
			TakesScreenshot ts1 = (TakesScreenshot) driver;
			File temp1 = ts1.getScreenshotAs(OutputType.FILE);
		    File src1 = new File("./screenshot/Flipkart.png");
		    FileHandler.copy(temp1, src1);
	}
}
