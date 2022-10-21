package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadConfig {
	Properties prop = new Properties();
	public void readData() throws IOException {
		FileInputStream fi = new FileInputStream("resources/config.properties");
		prop.load(fi);
	}
	
	public String getBrowser() {
		return prop.getProperty("browserType");
	}
	public String getURL() {
		return prop.getProperty("url");
	}
	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("implicitWait"));
	}
	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("explicitWait"));
	}
	public int getPageLoadWait() {
		return Integer.parseInt(prop.getProperty("pageLoadWait"));
	}
	
	public String getConfig(String key) {
		return prop.getProperty(key);
	}
	
}
