package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_06_Loop {
	
	WebDriver driver;	
	WebDriverWait  explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	String firstName, lastName, emailAddress, companyName, day, month, year;
	
	
	
	@BeforeClass	
	public void beforeClass() {
		
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		    driver = new FirefoxDriver();
			
		
		    explicitWait = new WebDriverWait(driver,10); // map driver vào, time out truyền đến driver timeout 10 second
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://demo.nopcommerce.com/");
			
			day = "10";
			month = "May";
			year = "2000";
			emailAddress = "Deep" + generateEmail();
			companyName = "Automation";
			firstName = "Jonny";
			lastName = "Deep";
			
			
		}
	
  @Test
  public void TC_01_Register_Nopcommerce() {
		
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Newsletter")).click();
		driver.findElement(By.id("Password")).sendKeys("12345678");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("12345678");
		
		
		driver.findElement(By.id("register-button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("result")));
		
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.ico-logout")));		
		driver.findElement(By.cssSelector("a.ico-logout")).click();
				
		
	}
	
  
  
  @Test
  public void TC_02_Login_Nopcommerce() {
  }
  
  @Test
  public void TC_03_Verify_Nopcommerce() {
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
  
  public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(999) + "@gmail.com";
	}

}
