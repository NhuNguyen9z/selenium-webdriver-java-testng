package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_17_Wait_Part_III_Implicit {

	WebDriver driver;	
	//WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	By buttonStart = By.cssSelector("div#start>button");
	By loadingIcon = By.cssSelector("div#loading");
	By helloWorld = By.cssSelector("div#finish h4");
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//explicitWait = new WebDriverWait(driver,10); // time out truyền đến driver timeout 10 second
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
		
	}

// NẾU KO SET Implicit Wait THÌ COI NHƯ findElement = 0
	
//@Test
public void TC_01_() {
	driver.get("https://vi-vn.facebook.com/");
	driver.findElement(By.name("lastname")).sendKeys("auto");
	
}
 

//@Test
public void TC_02_() {
	driver.get("https://vi-vn.facebook.com/");
	
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	driver.findElement(By.name("firstname")).sendKeys("FC");
	
	}

//@Test
public void TC_03_(){	
	driver.get("https://vi-vn.facebook.com/");
	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	driver.findElement(By.name("abktgh")).sendKeys("automation");
	
}

@Test
public void TC_04_Less_Than() {
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(buttonStart).click();
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
	
}

@Test
public void TC_05_Enough() {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(buttonStart).click();
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
	
}

@Test
public void TC_06_Greater_Than() {
	driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(buttonStart).click();
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
	
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
