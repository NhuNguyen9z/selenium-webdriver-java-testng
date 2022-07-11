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



public class Topic_10_Alert2 {

	WebDriver driver;	
	WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir");
	Alert alert; //thư viện Alert, khai báo một biến alert
	
	@BeforeClass
	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,10); //timeout truyen den driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
//@Test
public void TC_01_Accept_Alert_1() {
	
	driver.get("https://demo.guru99.com/v4/");
	driver.findElement(By.name("btnLogin")).click();
	
	// Switch qua Alert
	//alert = driver.switchTo().alert(); // define 1 object alert kiểu Alert -- switch trả về 1 biến alert
	
	// Wait chờ Alert xuất hiện +  Switch to alert
	alert = explicitWait.until(ExpectedConditions.alertIsPresent()); //chờ alert xuất hiện trong 10s
	sleepInSecond(5);
	
	// Verify text
	Assert.assertEquals(alert.getText(), "User or Password is not valid");
	
	// Accept
	alert.accept();
}
 
//@Test
public void TC_01_Accept_Alert_2() {
	
	driver.get("https://automationfc.github.io/basic-form/");
	driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	sleepInSecond(3);
	
	// Wait chờ Alert xuất hiện + Switch vào alert
	alert = explicitWait.until(ExpectedConditions.alertIsPresent()); // chờ alert xuất hiện trong 10s
	
	Assert.assertEquals(alert.getText(), "I am a JS Alert");
	
	alert.accept();
	sleepInSecond(3);
	
	Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	
	}

//@Test
public void TC_02_Confirm_Alert() {
	
	driver.get("https://automationfc.github.io/basic-form/");
	driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	sleepInSecond(3);
	
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	
	Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	alert.dismiss();
	sleepInSecond(3);
	
	Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	
	}

//@Test
public void TC_03_Prompt_Alert(){
	driver.get("https://automationfc.github.io/basic-form/");
	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	sleepInSecond(3);
	
	String fullName = "Automation FC";
	
	// chờ cho alert xuất hiện và Switch vào alert
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	
	Assert.assertEquals(alert.getText(), "I am a JS prompt");
	
	// Senkey to alert
	alert.sendKeys(fullName);
	sleepInSecond(3);
	
	alert.accept(); // Accept/ Cancel alert -> Mất Alert
	
	Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + fullName);
	
}

//@Test
public void TC_04_Authentication_Alert() {
	driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
}

@Test
public void TC_05_Authentication_Alert() {
	driver.get("http://the-internet.herokuapp.com/");
	String hrefValue = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	System.out.println(hrefValue);
	
	passValueToUrl(hrefValue, "admin", "admin");
	
	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	
}

public void passValueToUrl(String url, String user, String pass) {
	String[] hrefValue =  url.split("//");
	url = hrefValue[0] + "//" + user + ":" + pass + "@" + hrefValue[1];
	driver.get(url);
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
