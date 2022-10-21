package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.w3c.dom.stylesheets.MediaList;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	
	public WebDriver driver;
	
	public BrowserFactory bf = new BrowserFactory();
	
	public LoadConfig lc;
	public ExtentReports report;
	public ExtentTest log;
	public CaptureScreenshot cs;
	public ExcelOperations eo;
	public String[][] cellData;
	public String path ;
	
	@BeforeTest(alwaysRun = true)
	public void readConfig() throws IOException {
		System.out.println("Inside before suite");
		lc = new LoadConfig();
		lc.readData();
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File("Reports/AmazonHomePage.html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		System.out.println("Inside bsuite");
	}
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public WebDriver driverSetUP(String browserName) {
		System.out.println("Inside before method");
		driver = bf.startUP(driver, browserName, lc.getURL(), lc.getPageLoadWait(), lc.getImplicitWait(), lc.getExplicitWait());
		System.out.println("Inside bmethod");
		return driver;
	}
	@AfterMethod(alwaysRun = true)
	public void driverQuit(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.SUCCESS) {
			log.pass("The test case is passed");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			cs = new CaptureScreenshot();
			String path = cs.getScreenshot(driver, result.getName());
			log.fail("The test case is failed",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		report.flush();
		bf.quitBrowser(driver);
	}
	
	@DataProvider(name = "testData")
	public String[][] getData(Method method) throws IOException{
		
		path = lc.getConfig("testDataPath");
		eo = new ExcelOperations(path);
		String sheet = method.getName();
		int rowCount = eo.getRowCount(sheet);
		int columnCount = eo.getColumnCount(sheet);
		cellData = new String[rowCount][columnCount];
		System.out.println("This is the rowcount "+rowCount+" and this is the column count : "+columnCount+" and sheet : "+sheet);
		for(int i = 1;i<=rowCount;i++) {
			for(int j = 0;j<columnCount;j++) {
				cellData[i-1][j] = eo.getData(i, j, sheet);
			}
		}
		return cellData;
		
	}
	
	
	
}
