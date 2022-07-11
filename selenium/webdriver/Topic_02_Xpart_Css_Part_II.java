package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpart_Css_Part_II {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@Test
	public void Login_01_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Empty Email
				driver.findElement(By.id("email")).sendKeys("");
				
				//Empty Password				
				driver.findElement(By.name("login[password]")).sendKeys("");
				
		//Empty Click Button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Expected text tra ve = requirement
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		}

	@Test
	public void Login_02_Invalid_Email_Address() {
		driver.get("http://live.demoguru99.com");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("123@123.345");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		}
	
	@Test
	public void Login_03_Invalid_Password() {
		driver.get("http://live.demoguru99.com");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("nhunguyensg@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

	}
	@Test
	public void Login_04_Incorrect_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("nhunguyensg@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1234  56");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
		
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
