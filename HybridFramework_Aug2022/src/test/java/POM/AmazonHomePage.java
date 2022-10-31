package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseClass;

public class AmazonHomePage extends BaseClass{

	@FindBy(xpath="//input[@id=\"twotabsearchtextbox\"]")WebElement searchTxtBox;
	@FindBy(xpath="//input[@id=\"nav-search-submit-button\"]")WebElement searchBtn;
	@FindBy(xpath="//span[text()=\"Account & Lists\"]")WebElement accounts_Lists;
	@FindBy(xpath="//span[text()=\"Account\"]")WebElement yourAccount;
	
	public void searchAmazon(WebDriver driver, String searchKeyword) throws InterruptedException {
		searchTxtBox.sendKeys(searchKeyword);
		Thread.sleep(3000);
		searchBtn.click();
		
		
		
		
		
		
	}

	public void yourAccount(WebDriver driver) throws InterruptedException {
		Actions a = new Actions(driver);
		a.moveToElement(accounts_Lists).build().perform();
		Thread.sleep(3000);
		//JavascriptExecutor je = (JavascriptExecutor)driver;
		//je.executeScript("arguments[0].scrollToView()", yourAccount);
		
		yourAccount.click();
	}
	
	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
