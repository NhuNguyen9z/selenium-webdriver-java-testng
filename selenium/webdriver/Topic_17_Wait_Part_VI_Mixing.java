package webdriver;

import java.util.Date;
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



public class Topic_17_Wait_Part_VI_Mixing {

	WebDriver driver;	
	WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//explicitWait = new WebDriverWait(driver,10); // map driver, time out truyền đến driver timeout 10 second
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// nếu khai báo wait explicit hoặc implicit trong before thì sẽ ảnh hưởng đến các testcase bên dưới
	}

/* TRỘN 2 LOẠI Wait VÀO VỚI NHAU THÌ NÓ SINH RA BAO NHIÊU TRƯỜNG HỢP -- implicit wait luôn luôn chạy trước explicit wait vì nó luôn find element trước
 * Trong dự án thực tế nên đặt timeout của implicit lớn hơn timeout của explicit, nếu như implicit ko set thì timeout = 0
 * Case 1: Tìm thấy element
 * => Output: ko cần chờ hết timeout - cũng ko có exception nào hết -> ko fail testcase
 * 
 * Case 2: Ko tìm thấy element
 *- 2.1: Chỉ sử dụng Implicit - ko dùng explicit
 *   -> chờ hết timeout tính lại sau mỗi 0.5 giây/ hết time out đánh fail testcase/ throw NoSuchElementException 
 *- 2.2: Chỉ sử dụng explicit - ko dùng implicit
 *   -> chờ hết timeout/ tìm lại sau mỗi 0.5 giây/ hết time out đánh fail testcase/ throw TimeoutException
 * 
 * - 2.3 Implicit < Explicit hoặc Implicit = Explicit hoặc Implicit > Explicit
 * Implicit > Explicit: timeout tổng cộng là nó lấy của implicit
 * -2.4: Explicit nhận tham số là By/ tham số là WebElement
 * */

	
//@Test
public void TC_01_Element_Implicit_Explicit_Found() {
	driver.get("https://vi-vn.facebook.com/");
	explicitWait = new WebDriverWait(driver, 5);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	System.out.println("Start explicit:" + getDateTimeNow());
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	System.out.println("End explicit: " + getDateTimeNow());
	
	
	System.out.println("Start implicit: " + getDateTimeNow());
	driver.findElement(By.id("pass"));
	System.out.println("End implicit: " + getDateTimeNow());	
	
}
 

//@Test
public void TC_02_1_Element_Not_Found_Only_Implicit() {
	driver.get("https://vi-vn.facebook.com/");
	
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	System.out.println("Start Implicit: " + getDateTimeNow());
	try {
		driver.findElement(By.id("passtest"));
	} finally {
		System.out.println("End Implicit: " + getDateTimeNow());
	}
		
	}

//@Test
public void TC_02_2_Not_Found_Only_Explicit(){	
	driver.get("https://vi-vn.facebook.com/");
	 explicitWait = new WebDriverWait(driver, 5);
	  System.out.println("Start Explicit:" + getDateTimeNow());
	try {
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailtest")));
	} finally {
		System.out.println("End Explicit: " + getDateTimeNow());
	}
	
	
}

//@Test
public void TC_02_3_Not_Found_Implicit_Explicit() {
	driver.get("https://vi-vn.facebook.com/");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	explicitWait = new WebDriverWait(driver, 3);
	
	System.out.println("Start implicit: " + getDateTimeNow());	
	// Nhận time out của implicit
	try {
		driver.findElement(By.id("tiki"));
		
	} catch (Exception e) {
		
	}
	System.out.println("End implicit: " + getDateTimeNow());
	
	
	System.out.println("Start explicit:" + getDateTimeNow());
	// Nhận timeout của cả 2 trong hàm: visibilityOfElementLocated
	// driver.findElement(locator) => bị ảnh hưởng timeout của implicit: 5s
	// elementIfVisible bị ảnh hưởng timeout của explicit: 3s
	try {
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tiki")));
	} catch (Exception e) {
		e.printStackTrace();
	}	
	
	System.out.println("End explicit: " + getDateTimeNow());
		
}

@Test
public void TC_02_4_Not_Found_Explicit_By() {
	
	driver.get("https://vi-vn.facebook.com/");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	explicitWait = new WebDriverWait(driver, 5);	
		
	System.out.println("Start explicit:" + getDateTimeNow());
	try {
		// Nhận tham số là By
		// Nhận timeout của cả 2 trong hàm: visibilityOfElementLocated
		// driver.findElement(locator) => bị ảnh hưởng timeout của implicit: 5s -- phải dùng findElement vì phải tìm được elemnent trước thì mới apply dc điều kiện
		// elementIfVisible bị ảnh hưởng timeout của explicit: 5s
		// Throw ra 1 exception: TimeoutException (Explicit)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tiki")));
		
		// Nhận tham số là WebElement
		// Chạy từ trong ra: findElement -> Nhận timeout của implicit
		// Tìm ko thấy element Chờ hết timeout 5s
		// Đánh fail
		// Throw exception: NoSuchElement (Implicit) 
		//explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("titki"))));
		//Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed()); // sẽ chạy tìm element trước có element thì sẽ check display trả về True/ False thì mới chạy assertTrue
	} catch (Exception e) {
		e.printStackTrace();
	}	
	
	System.out.println("End explicit: " + getDateTimeNow());
	
}

//@Test
public void TC_02_4_Not_Found_Explicit_WebElement() {
	
	driver.get("https://vi-vn.facebook.com/");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	explicitWait = new WebDriverWait(driver, 15);	
		
	System.out.println("Start explicit:" + getDateTimeNow());
	try {
			
		// Nhận tham số là WebElement
		// Chạy từ trong ra: findElement -> Nhận timeout của implicit
		// Chờ hết timeout 5s
		// Đánh fail
		// Throw exception: NoSuchElement (Implicit) 
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("titki"))));
		//Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed()); // sẽ chạy tìm element trước có element thì sẽ check display trả về True/ False thì mới chạy assertTrue
	} catch (Exception e) {
		e.printStackTrace();
	}	
	
	System.out.println("End explicit: " + getDateTimeNow());
	
}



@AfterClass
public void afterClass() {
	driver.quit();
}

public String getDateTimeNow() {
	Date date = new Date(); 
	return date.toString();
			
	
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
