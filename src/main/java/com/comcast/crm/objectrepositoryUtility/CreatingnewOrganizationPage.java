package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingnewOrganizationPage {
	
	WebDriver driver;
	public CreatingnewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="accountname")
	private WebElement orgnameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(name="industry")
	private WebElement industryDd;
	
	@FindBy(name="accounttype")
	private WebElement typeDp;
	
	@FindBy(id="phone")
	private WebElement PhoneDp;
	
	
	
	public WebElement getIndustryDd() {
		return industryDd;
	}

	public WebElement getTypeDp() {
		return typeDp;
	}

	public WebElement getPhoneDp() {
		return PhoneDp;
	}

	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgname) {
		orgnameEdt.sendKeys(orgname);
		
	}

	public void createOrgDp(String orgname , String industry , String type) {
		
		orgnameEdt.sendKeys(orgname);
		Select sel = new Select(industryDd);
		sel.selectByVisibleText(industry) ;
		Select sel1 = new Select(typeDp);
		sel1.selectByVisibleText(type);
		
	}
	
	public void createOrgManAndPh(String orgname , String phonenum) {
		orgnameEdt.sendKeys(orgname);
		PhoneDp.sendKeys(phonenum);
	
	}

	
	

}
