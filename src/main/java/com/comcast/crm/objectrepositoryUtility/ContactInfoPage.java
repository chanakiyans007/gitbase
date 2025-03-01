package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	
	WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	@FindBy(id="dtlview_Last Name")
	private WebElement actConName;
	
	public WebElement getActConName() {
		return actConName;
	}
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement actStDate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement actEdDate;
	
	public WebElement getActStDate() {
		return actStDate;
	}

	public WebElement getActEdDate() {
		return actEdDate;
	}
	
	
	

}
