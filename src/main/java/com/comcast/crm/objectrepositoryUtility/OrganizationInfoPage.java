package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement actOrgName;
	
	public WebElement getActOrgName() {
		return actOrgName;
	}
	
	@FindBy(id="dtlview_Industry")
	private WebElement actIndustry;
	
	@FindBy(id="dtlview_Type")
	private WebElement actType;
	
	@FindBy(id="dtlview_Phone")
	private WebElement actPhNum;
	
	
	
	public WebElement getActPhNum() {
		return actPhNum;
	}

	public WebElement getActIndustry() {
		return actIndustry;
	}

	public WebElement getActType() {
		return actType;
	}
	
	
	

}
