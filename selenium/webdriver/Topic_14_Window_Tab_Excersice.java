package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_14_Window_Tab_Excersice {

	WebDriver driver;	
	JavascriptExecutor jsExecutor;
	//WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		//explicitWait = new WebDriverWait(driver,10); // time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
//@Test
public void TC_01_Window_Tab_1() {
	driver.get("https://kyna.vn/");
	String parentID = driver.getWindowHandle();
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='k-footer'] //img[@alt='facebook']")));
	driver.findElement(By.xpath("//div[@id='k-footer'] //img[@alt='facebook']")).click();
	// Switch qua facebook tab
	switchToWindowByTitle("Kyna.vn - Home | Facebook");	
	// Find element để định danh facebook tab
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("automationfc@gmail.com");
	driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@name='pass']/parent::label/parent::div/following-sibling::div//span[text()='Log In']")).click();
	Assert.assertEquals(driver.findElement(By.cssSelector("div._9ay7>a")).getText(), "Forgotten password?");
	
	// Switch qua trang parent là https://kyna.vn/
	switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");	
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='k-footer'] //img[@alt='youtube']")));
	driver.findElement(By.xpath("//div[@id='k-footer'] //img[@alt='youtube']")).click();
	sleepInSecond(5);
	// Switch qua youtube kyna.vn tab
	switchToWindowByTitle("Kyna.vn - YouTube");	
	// Find element để định danh youtube page
	driver.findElement(By.cssSelector("div#search-input>input#search")).sendKeys("Tự tin trước đám đông");
	driver.findElement(By.cssSelector("button#search-icon-legacy")).click();
	sleepInSecond(2);
	
	
	// Switch về trang parent kyna.vn
	switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
	sleepInSecond(2);
	// Scroll xuống bottom
	jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	sleepInSecond(5);
	//Switch vào facebook kyna.vn iframe    
	driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));	
	driver.findElement(By.xpath("//a[text()='Kyna.vn']")).click();
	sleepInSecond(5);
	// Switch vào facebook kyna.vn tab
	switchToWindowByTitle("Kyna.vn - Home | Facebook");
	// Find element để định danh facebook tab
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("automationfc@gmail.com");
	driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@name='pass']/parent::label/parent::div/following-sibling::div//span[text()='Log In']")).click();
	Assert.assertEquals(driver.findElement(By.cssSelector("div._9ay7>a")).getText(), "Forgotten password?");
	
	// Switch về trang parent kyna.vn
	switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
	// Scroll xuống bottom
	jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
	driver.findElement(By.cssSelector("a.col-lg-6:nth-of-type(1)")).click();
	sleepInSecond(3);
	// Switch vào tab: Thông tin website thương mại điện tử - Online.Gov.VN 
	switchToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
	// Find element để định danh đã vào đúng page đó chưa
	driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
	
	
	// Switch về trang parent trước đó là kyna.vn
	switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
	// Scroll xuống bottom
	jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
	driver.findElement(By.cssSelector("a.col-lg-6:nth-child(2)")).click();
	sleepInSecond(3);
	// Switch vào tab: Thông tin website thương mại điện tử - Online.Gov.VN
	switchToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
	driver.findElement(By.xpath("//li[@class='header-desk-register']/a[text()='Đăng ký']")).click();
	
	
	// close all window trừ parent window
	closeToWindowWithoutParent(parentID);
	driver.findElement(By.cssSelector("a.register-btn")).click();
	
}
 

@Test
public void TC_02_() {
	driver.get("https://www.facebook.com/kyna.vn");
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("automationfc@gmail.com");
	driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@name='pass']/parent::label/parent::div/following-sibling::div//span[text()='Log In']")).click();
	Assert.assertEquals(driver.findElement(By.cssSelector("div._9ay7")).getText(), "The password that you've entered is incorrect. Forgotten password?");
	System.out.println(driver.getTitle());
	}

//@Test
public void TC_03_(){	
	
	
}

//@Test
public void TC_04_() {
	
}

//@Test
public void TC_05_() {
	
	
}



@AfterClass
public void afterClass() {
	//driver.quit();
}


public void switchToWindowByTitle (String expectTitle) {
	// Set String get ra tất cả window
	Set<String> allWindow = driver.getWindowHandles();
	
	// Dùng vong lặp duyệt qua từng window
	for (String id : allWindow) {
		// Switch vào từng window
		driver.switchTo().window(id);
		
		// Get ra title từng window
		String title = driver.getTitle();
		System.out.println(title);
		
		// Kiểm tra nếu title == title mong muốn thì Stop
		if(title.equals(expectTitle)) {
			break;
		}
		
	}
	
	
}

public void closeToWindowWithoutParent(String parentID) {
	// Get all windows
	Set<String> allWindow = driver.getWindowHandles();
	// duyệt qua từng window
	for (String id : allWindow) {
		// Switch vào từng window
		driver.switchTo().window(id);
		// nếu window con ko bằng parent window thì close
		if(!id.equals(parentID)) {
			driver.close();
		}
		
	}
	// Switch về parent window
	driver.switchTo().window(parentID);
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
