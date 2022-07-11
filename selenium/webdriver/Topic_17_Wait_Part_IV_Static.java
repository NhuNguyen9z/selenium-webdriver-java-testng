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



public class Topic_17_Wait_Part_IV_Static {

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
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
@Test
public void TC_01_Less_Than() {
	
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(buttonStart).click();
	
	// Time bị thiếu 3s
	sleepInSecond(2);
	
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
	
	
}
 

@Test
public void TC_02_Enough() {
	
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(buttonStart).click();
	
	// Time vừa đủ
	// Khó biết bao nhiêu s sẽ đủ
	// Lúc này nó xx time
	// Lúc khác nó lại là < xx time
	// Lúc khác nó lại là > xx time
	sleepInSecond(5);
	
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
	
	}

@Test
public void TC_03_Greater_Than(){	
	
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	driver.findElement(buttonStart).click();
	
	// Time bị dư
	sleepInSecond(10);
	
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");	
	
}

//@Test
public void TC_04_() {
	
}

//@Test
public void TC_05_() {
	
	
}



@AfterClass
public void afterClass() {
	driver.quit();
}


public void sleepInSecond(long time) {
	try {
		Thread.sleep(time*1000);  // 1000 milisecond = 1 giây nên muốn tham số time nhận giây thì nhân với 1000
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
