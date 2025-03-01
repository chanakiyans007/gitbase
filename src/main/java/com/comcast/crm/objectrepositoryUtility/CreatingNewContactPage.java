package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	
	@FindBy(name="lastname")
	private WebElement actcontectLastName;
	
	
	public WebElement getContectLastName() {
		return actcontectLastName;
	}

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="support_start_date")
	private WebElement startDate;
	
	@FindBy(name="support_end_date")
	private WebElement endDate;
	
	@FindBy(xpath="(//img[@src='themes/softed/images/select.gif'])")
	private WebElement newConOrg;
	
	@FindBy(name="search_text")
	private WebElement searchBar;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	

	public WebElement getSearchBar() {
		return searchBar;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


	public WebElement getNewConOrg() {
		return newConOrg;
	}


	public WebElement getStartDate() {
		return startDate;
	}


	public WebElement getEndDate() {
		return endDate;
	}


	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}


	public void createContact(String lastname ) {
		lastnameEdt.sendKeys(lastname);
		
	}
	

	public void createContactWithDate(String lastname , String stDate , String edDate) {
		lastnameEdt.sendKeys(lastname);
		startDate.clear();
		startDate.sendKeys(stDate);
		endDate.clear();
		endDate.sendKeys(edDate);
		
	}
	
	
}
