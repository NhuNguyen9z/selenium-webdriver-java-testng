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



public class Topic_00_Template {

	WebDriver driver;	
	//WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//explicitWait = new WebDriverWait(driver,10); // map driver vào, time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}
	
@Test
public void TC_01_() {
	
	
}
 

//@Test
public void TC_02_() {
	
	
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
	driver.quit();
}


public void sleepInSecond(long timeoutInSecond) {
	try {
		Thread.sleep(timeoutInSecond*1000); // 1000 milisecond = 1 giây nên muốn tham số timeoutInSecond nhận giây thì sẽ nhân với 1000
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
/*
 * 1, try ... catch: case happy sẽ nhảy vào try nếu có exception thì catch sẽ bắt exception lại để cho các step bên dưới vẫn chạy dc sau khi chạy xong hết sẽ throw ra exception
   
   2, try ... finally: try vẫn chạy vào nếu gặp exception thì nó vẫn throw vẫn đánh fail testcase nhưng bắt buộc phải chạy thằng Finally chứ ko dc bỏ qua
   
 * */
