package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_16_Up_Load_File_Part_I {

	WebDriver driver;	
	//WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	String dellName = "Dell.jpg";
	String razerName = "Razer.jpg";
	String thinkPadName = "ThinkPad.jpg";
	
	// Hàm separator sẽ tự động chỉnh dấu \ trên window hoặc là dấu / trên Mac nên chạy ko bị lỗi
	String dellFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dellName;
	String razerFilePath = projectPath + File.separator + "uploadFiles" + File.separator + razerName;
	String thinkPadFilePath = projectPath + File.separator + "uploadFiles" + File.separator + thinkPadName;



	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//explicitWait = new WebDriverWait(driver,10); // time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
// Hàm Senkeys chỉ cần element là presence 
// Hàm Click hoặc isDisplayed thì element phải là visible
	
//@Test
public void TC_01_SendKeys_One_File() {
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	By upLoadFile = By.xpath("//input[@type='file']");
	
	// Upload từng file
	driver.findElement(upLoadFile).sendKeys(dellFilePath);
	sleepInSecond(1);
	
	driver.findElement(upLoadFile).sendKeys(razerFilePath);
	sleepInSecond(1);
	
	driver.findElement(upLoadFile).sendKeys(thinkPadFilePath);
	sleepInSecond(1);
	
	// Verify các file đã upload thành công
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ dellName + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + razerName + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thinkPadName + "']")).isDisplayed());
	
	// Click để start button -> Upload
	List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
	
	// Dùng vòng lặp duyệt qua từng phần tử trong list
	for (WebElement start : startButtons) {
		
		// click button Start
		start.click();
		
	}
	
	// Verify các file đã upload thành công
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dellName +"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + razerName +"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkPadName +"']")).isDisplayed());
		

}
 

@Test
public void TC_02_Senkeys_Multiple_File() {
	
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	By upLoadFile = By.xpath("//input[@type='file']");
	
	// Upload từng file
	driver.findElement(upLoadFile).sendKeys(dellFilePath + "\n" + razerFilePath + "\n" + thinkPadFilePath);
	sleepInSecond(2);
	
		
	// Verify các file đã upload thành công
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ dellName + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + razerName + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thinkPadName + "']")).isDisplayed());
	
	// Click để start button -> Upload
	List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
	
	// Dùng vòng lặp duyệt qua từng phần tử trong list
	for (WebElement start : startButtons) {
		
		// click button Start
		start.click();
		
	}
	
	// Verify các file đã upload thành công
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dellName +"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + razerName +"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkPadName +"']")).isDisplayed());
		

	
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


public void sleepInSecond(long time) {
	try {
		Thread.sleep(time*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
