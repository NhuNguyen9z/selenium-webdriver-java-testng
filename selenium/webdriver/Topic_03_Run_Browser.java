package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_Run_Browser {
	WebDriver driver;
	String projectPath =  System.getProperty("user.dir");
	
	@Test
	public void TC_01_Run_On_Firefox() {
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com");
		
		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome_Window() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
	}
	
	@Test
	public void TC_03_Run_On_Chrome_MAC() {
		//Mac
		//Step1
		//Step 2: Set permission for chromedriver
		System.setProperty("webdriver.chrome.driver", projectPath + "//browserDrivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
		
		
	}
	
	@Test
	public void TC_04_Run_On_Edge_Window() {
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
	}
	
	@Test
	public void TC_05_Run_On_Edge_MAC() {
		//Mac
		//Step1
		//Step 2: Set permission for chromedriver
		System.setProperty("webdriver.edge.driver", projectPath + "//browserDrivers//msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://demo.nopcommerce.com");
		driver.quit();
	}
}
