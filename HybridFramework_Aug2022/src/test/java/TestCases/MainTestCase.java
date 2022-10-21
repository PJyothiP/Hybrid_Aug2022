package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import POM.AmazonHomePage;
import Utilities.BaseClass;

public class MainTestCase extends BaseClass {



	@Test(priority = 2,dataProvider = "testData", groups = {"Regression","Smoke"})
	public void amazonSearch(String SearchKeyword) throws InterruptedException {
		AmazonHomePage ahp = new AmazonHomePage(driver);
		log = report.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.info("Started executing amazon Search test case");
		ahp.searchAmazon(driver,SearchKeyword);
		log.info("Search is completed");
		WebElement we = driver.findElement(By.xpath("//span[@class= \"a-size-medium-plus a-color-base a-text-normal\" and text()=\"RESULTS\"]"));
		log.info("before assertion");
		AssertJUnit.assertTrue(we.isDisplayed());
	}

	@Test(priority = 1,dataProvider = "testData",groups="Regression")
	public void yourAccount(String column1,String column2) throws InterruptedException {
		AmazonHomePage ahp = new AmazonHomePage(driver);
		log = report.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		ahp.yourAccount(driver);

		System.out.println("This is column 1 : "+column1+" and this is column 2 "+column2);

		String title = driver.getTitle();
		log.info("The title of the page is : "+title);
		/*

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(title, "Your Accoun");
		System.out.println("After assertion");
		sa.assertAll();
	*/
		AssertJUnit.assertEquals(title, "Your Account");
		System.out.println("After Assertion");
	}

	@Test(groups = "Smoke")
	public void dummy1() {
		System.out.println("This is a dummy test case");
	}

	@Test()
	public void demo() {
		System.out.println("This is a demo test");
	}
}
