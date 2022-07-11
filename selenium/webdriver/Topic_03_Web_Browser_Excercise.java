package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Web_Browser_Excercise {
	WebDriver driver;


	@BeforeClass
	public void beforeClass() {
		//Open browser
		driver =  new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				
		}
	
	@Test(enabled = false)
	public void TC_01_Verify_Url() {
       // Open app
		driver.get("http://live.demoguru99.com/");
		
		// Click to My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// Kiem tra 1 điều kiện mong muốn bằng với thực tế (expected = actual)
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		// Click to Create An Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		
	}
	
	@Test(enabled = false)
	public void TC_02_Verify_Title() {
		// Open app
		driver.get("http://live.demoguru99.com/");
		
		// Click My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Click button Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test(enabled = false)
	public void TC_03_Navigate() {
		// Open app
		driver.get("http://live.demoguru99.com/");
		
		// Click My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// Click button Create An Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.navigate().back();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}	
	
	@Test(enabled = false)
	public void TC_04_Page_Source() {
		// Open app
		driver.get("http://live.demoguru99.com");
		
		// Click My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
	  Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		// Click button Create An Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	@Test
	public void TC_05_Create_An_Account() {
		// Open app
		driver.get("http://live.demoguru99.com");
		
		// Click My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// Click to Create an Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys("Nhu");
		driver.findElement(By.id("middlename")).sendKeys("Thi Cam");
		driver.findElement(By.id("lastname")).sendKeys("Nguyen");
		driver.findElement(By.id("email_address")).sendKeys("nhunguyen" + getRandomNumber() +"@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.id("confirmation")).sendKeys("123123");
		driver.findElement(By.xpath("//label[(text()='Sign Up for Newsletter')]")).click();
		driver.findElement(By.cssSelector("button[title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		//Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span[(text()='Thank you for registering with Main Website Store.')]")).getText(), "Thank you for registering with Main Website Store.");
		
		
		
		
	}
	
	@AfterClass
	public void affterClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt();
	}
	
		
	}
