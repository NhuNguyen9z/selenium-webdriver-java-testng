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



public class Topic_04_Web_Browser_Method_Parth_III {

	WebDriver driver;  // Khai báo
	
	String firstName = "Johney";
	String middleName = "Tomy";
	String lastName = "Deep";
	String emailAddress = getRandomEmail();
	String password = "1234567";
	

	@BeforeClass
	public void beforeClass() {
		//Open browser
		driver =  new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		
				
		}
	
	@Test
	public void TC_01_Register() {
			
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//driver.findElement(By.id("firstname")).clear();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("middlename")).sendKeys(middleName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys("1234567");
		driver.findElement(By.id("is_subscribed")).click();
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		String informationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div[@class='box-content']/p")).getText();
		
		System.out.println(informationText);
		Assert.assertTrue(informationText.contains(firstName + " " + middleName + " " + lastName));
		Assert.assertTrue(informationText.contains(emailAddress));
		
	
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		sleepInSecond(5);
		
		
			}
	
	
	@Test
	public void TC_02_Login() {
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		//driver.findElement(By.id("email")).clear();
		//
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
	
		driver.findElement(By.id("pass")).sendKeys(password);
		
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(), "Hello," + " " + firstName + " " + middleName + " " + lastName + "!" );
		String informationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div[@class='box-content']/p")).getText();
		System.out.println("LOGIN");
		System.out.println(informationText);
		Assert.assertTrue(informationText.contains(firstName + " " + middleName + " " + lastName));
		Assert.assertTrue(informationText.contains(emailAddress));
		sleepInSecond(5);
	}
	
		
	public String getRandomEmail() {
		Random rand = new Random();
		return "johney" + rand.nextInt(1000) + "@gmail.com";
	}
		
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void affterClass() {
		driver.quit();
	}
	
}
	
	
	

