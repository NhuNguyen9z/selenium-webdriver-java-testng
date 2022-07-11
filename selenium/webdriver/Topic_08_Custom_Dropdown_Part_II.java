package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_08_Custom_Dropdown_Part_II {

	WebDriver driver;	
	//WebDriverWait  explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver; //assign deiver cho jsExecutor rồi ép kiểu
		driver.manage().window().maximize();
		
	}
	
@Test
public void TC_01_JQuery() {
	
	driver.get("http://indrimuska.github.io/jquery-editable-select/");
	
	// Input
	driver.findElement(By.cssSelector("#default-place input")).sendKeys("Audi");
	sleepInSecond(3);
	driver.findElement(By.cssSelector("#default-place input")).sendKeys(Keys.TAB);
	sleepInSecond(3);
	
	// Verify
	String inputValue = (String) jsExecutor.executeScript("return document.querySelector('#default-place input').value");
	Assert.assertEquals(inputValue, "Audi");
}
 

//@Test
public void TC_02_() {
	
	
	}

//@Test
public void TC_03_t(){	
	
	
}

//@Test
public void TC_04_t() {
	
}



@AfterClass
public void afterClass() {
	driver.quit();
}


public void sleepInSecond(long time) {
	try {
		Thread.sleep(time*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
