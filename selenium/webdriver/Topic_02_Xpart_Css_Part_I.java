package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpart_Css_Part_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Xpath() {
		driver.get("http://live.demoguru99.com");
		
		driver.findElement(By.xpath("//a[@title='My Account'")).click();
		
		
		
	}
		
	

//	@Test
//	public void TC_02_Classname() {
//		
//	}
//	@Test
//	public void TC_03_Name() {
//		
//	}
//
//	@Test
//	public void TC_04_Tagname() {
//	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
