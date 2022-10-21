package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	public String getScreenshot(WebDriver driver,String name) throws IOException {
		String path = System.getProperty("user.dir")+"/target/"+name+System.currentTimeMillis()+".jpeg";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File f = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(path));
		return path;
	}
}
