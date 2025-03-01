package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
    private WebElement orgLnk;
	
	@FindBy(linkText="Products")
    private WebElement productLnk;
	
	public WebElement getProductLnk() {
		return productLnk;
	}

	@FindBy(linkText="Contacts")
	private WebElement contectLnk;
	
	@FindBy(linkText="More")
	private WebElement moreLnk;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminLnk;
	
	public WebElement getAdminLnk() {
		return adminLnk;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;
	
	
	public WebElement getOrgLnk() {
		return orgLnk;
	}

	public WebElement getContectLnk() {
		return contectLnk;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getMoreLnk() {
		return moreLnk;
	}

	public WebElement getCampaignLnk() {
		return campaignLnk;
	}

	public void navigateToCampaignsPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLnk).perform();;
		campaignLnk.click();
	}

    public void logOut () {
    	Actions act = new Actions(driver);
		act.moveToElement(adminLnk).perform();
		signOutLnk.click();
    }






}








