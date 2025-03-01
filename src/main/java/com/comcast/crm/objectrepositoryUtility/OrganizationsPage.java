package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindAll({@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']") , @FindBy(xpath="//img[@title='Create Organization...']")})
	private WebElement createOrgBtn;
	

	public WebElement getCreateOrgBtn() {
		return createOrgBtn;
	}
	
	@FindBy(name="search_text")
	private WebElement searchbarORG;
	
	@FindBy(name="search_field")
	private WebElement searchEdtORG;
	
	@FindAll({@FindBy(name="submit") , @FindBy(xpath="//input[@type='button']")})
	private WebElement searchBtnORG;
	
	
	
	public WebElement getSearchBtnORG() {
		return searchBtnORG;
	}

	public WebElement getSearchbarORG() {
		return searchbarORG;
	}

	public WebElement getSearchEdtORG() {
		return searchEdtORG;
	}
	
	
	
	

}
