package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	/**
	 * this method is used to maximize
	 * @param driver
	 */
	
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * this method is used to minimize
	 * @param driver
	 */
	
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * this method will wait untill the webelements loaded in webpage(implicit wait)
	 * @param driver
	 */
	
	public void toWaitForElements(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
		/**
		 * this method is used to wait until the element is clickable provided driver and element
		 * @param driver
		 * @param element
		 */
	public void toWaitUntilElementIsClickable(WebDriver driver, WebElement element) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
	}
	
	/**
	 * this method is used to wait until the element is visible provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toWaitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
		

	public void switchToTabOnURL(WebDriver driver , String partialURL) {
		Set<String> set =driver.getWindowHandles();
		Iterator<String> it =set.iterator();
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains("partialURL")) {
				break;
			}
		}
	}

		public void switchToTabOnTitle(WebDriver driver , String partialTitle) {
			Set<String> set =driver.getWindowHandles();
			Iterator<String> it =set.iterator();
			while(it.hasNext()) {
				String windowID = it.next();
				driver.switchTo().window(windowID);
				
				String actUrl = driver.getTitle();
				if(actUrl.contains("partialTitle")) {
					break;
				}
			}
		}
		
		public void switchToFrame(WebDriver driver , int index) {
			driver.switchTo().frame(index);
		}
		
		public void switchToFrame(WebDriver driver , String nameId) {
			driver.switchTo().frame(nameId);
		}
		
		public void switchToFrame(WebDriver driver , WebElement element) {
			driver.switchTo().frame(element);
		}
		
		public void switchToAlertAndAccept(WebDriver driver) {
			driver.switchTo().alert().accept();
		}
		
		public void switchToAlertAndCancel(WebDriver driver) {
			driver.switchTo().alert().dismiss();
		}
		
		/**
		 * this method is used to handle dropDown using index
		 * @param element
		 * @param index
		 */
	    public void toHandleDropdown(WebElement element, int index) {
	    	Select select = new Select(element);
	    	select.selectByIndex(index);
	    }		
	    
	    /**
	     * this method is used to handle dropDown using value
	     * @param element
	     * @param value
	     */
	    public void toHandleDropdown(WebElement element, String value) {
	    	Select select = new Select(element);
	    	select.selectByValue(value);
	    }
	    
	    /**
	     * this method is used to handle dropDown using text
	     * @param text
	     * @param element
	     */
	    public void toHandleDropdown(String text, WebElement element) {
	    	Select select = new Select(element);
	    	select.selectByVisibleText(text);
	    }
		
		public void mouseMoveOnElement(WebDriver driver , WebElement element) {
			Actions act = new Actions(driver);
			act.moveToElement(element).perform();
		}
		
		public void doubleClick(WebDriver driver , WebElement element) {
			Actions act = new Actions(driver);
			act.doubleClick(element).perform();
		}
		
		 /**
	     * this method is used to perform right click on webElement
	     * @param driver
	     * @param element
	     */
	    public void toRightClick(WebDriver driver , WebElement element) {
	    	Actions action = new Actions(driver); 
	    	action.moveToElement(element).perform();
	    }
	    
	    /**
	     * this method is used to perform drag and drop provided driver , source element and target locator 
	     * @param driver
	     */
	    public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
	    	Actions action = new Actions(driver);
	    	action.dragAndDrop(src, target).perform();
	    }
	    
	    /**
	     * this method is used to perform click and hold
	     * @param driver
	     * @param element
	     */
	    public void toClickAndHold(WebDriver driver, WebElement element) {
	    	Actions action = new Actions(driver);
	    	action.clickAndHold(element).perform();
	    }
		
	    /**
	     * this method is used to capture message in alert popup and accept it
	     * @param driver
	     * @return
	     */
	    public String toHandleAlertPopupAndCaptureText(WebDriver driver) {
	    	Alert alertPopup = driver.switchTo().alert();
	    	String popupMsg = alertPopup.getText();
	    	alertPopup.accept(); //ok button
	    	return popupMsg;
	    } 
	    
	    /**
	     * this method is used to capture message in confirmation popup and accept it
	     * @param driver
	     */
	    public void toHandleConfirmationPopupAndCaptureText(WebDriver driver) {
	    	Alert confirmationPopup = driver.switchTo().alert();
			System.out.println(confirmationPopup.getText());   //capture msg
			confirmationPopup.accept(); //click on OK button
			//confirmationPopup.dismiss(); //click on cancel button
	    	
	    } 
	    
	    /**
	     * this method is used to take screenshot provided driver and screenshot name
	     * @param driver
	     * @param screenshotname
	     * @throws IOException
	     */
	    public void toTakeScreenShot(WebDriver driver, String screenshotname) throws IOException {
	    	TakesScreenshot ts = (TakesScreenshot) driver;
	    	File temp = ts.getScreenshotAs(OutputType.FILE);
	    	File src = new File("./errorShots"+ screenshotname +".png");
	    	FileHandler.copy(temp, src);
	    	
	    }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
