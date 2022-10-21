package Utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	
	public WebDriver startUP(WebDriver driver,String browserType,String Url,int pageLoadWait,int implicitWait,int explicitWait) {
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		
		switch(browserType) {
		
		case "Chrome":
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--incognito");
			co.merge(dc);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(co);
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(dc);
			break;
		case "Safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver(dc);
			break;
			
		default:
				System.out.println("Invalid browser type");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(Url);
		driver.manage().timeouts().pageLoadTimeout(pageLoadWait, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, explicitWait);
		return driver;
	}
	
	public void quitBrowser(WebDriver driver) {
		driver.quit();
	}
	
}
