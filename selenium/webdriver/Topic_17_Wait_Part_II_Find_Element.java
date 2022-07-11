package webdriver;

import java.util.Date;
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



public class Topic_17_Wait_Part_II_Find_Element {

	WebDriver driver;	
	//WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//explicitWait = new WebDriverWait(driver,10); // time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://vi-vn.facebook.com/");
		
	}
/* driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 * Điểm chung:
 * - Nó đều chịu ảnh hưởng bởi time out implicit 
 * - 5s này sẽ là thời gian tối đa để chờ cho element có trong DOM (HTML)
 * - Nếu như xuất hiện ngay trong vòng ít hơn 5s (1 - 2s xuất hiện thì 3s còn lại ko cần chờ nữa)
 * - Trong thời gian chờ thì có cơ chế tìm lại sau mỗi nửa giấy (0.5s)
 * - Nếu như sau 5s mà vẫn chưa xuất hiện thì tùy vào hàm đang sử dụng findElement/findElements 
 * 
 * TÓM TẮT implicit Wait
 * - Được sử dụng cho findElement/ findElements
 * - Tìm thấy element thì ko cần chờ hết timeout
 * - Ko tìm thấy thì có cơ chế tìm lại -> Tìm lại ko thấy luôn -> Output (findElement/ findElements)
 * - Nếu như ko set thì mặc định = 0
 * - Nếu như set rồi mà sau đó ở 1 step sau dc set lại thì sẽ bị overide lại
 * 
 * */
	


//@Test
public void TC_01_Find_Element() {
	// Case 1- Tìm thấy 1 matching node
	// - Ko cần chờ time out nên chạy rất nhanh (milisecond)
	//System.out.println("Start: " + getDayTimeNow());
	//driver.findElement(By.id("email")).sendKeys("automation@gmail.com");	
	//System.out.println("End: " + getDayTimeNow());
	
	
	// Case 2 - Ko tìm thấy node nào hết
	// - Chờ hết timeout
	// - Ném ra 1 ngoại lệ (exception): NoSuchElementException
	// - Đánh testcase đó fail luôn tại step đó
	// - Ko chạy step tiếp theo nữa
	//System.out.println("Start 2: " + getDayTimeNow());
	//try {
	//	driver.findElement(By.id("tiki")).isDisplayed();
	//} finally {
	//	System.out.println("End 2: " + getDayTimeNow());
	//}	
	//driver.findElement(By.id("pass")).sendKeys("123456");
	
	
	// Case 3 - Tìm thấy nhiều hơn 1 node
	// - Nếu như nhiều hơn 1 node thì sẽ thao tác node đầu tiên
	System.out.println("Start 3: " + getDayTimeNow());
	WebElement links = driver.findElement(By.cssSelector("div#pageFooter a"));
	System.out.println("End 3: " + getDayTimeNow());
	links.click();
	
	
	/*
	 * 1, try ... catch: case happy sẽ nhảy vào try nếu có exception thì catch sẽ bắt exception lại để cho các step bên dưới vẫn chạy dc sau khi chạy xong hết sẽ throw ra exception
       
       2, try ... finally: try vẫn chạy vào nếu gặp exception thì nó vẫn throw vẫn đánh fail testcase nhưng bắt buộc phải chạy thằng Finally chứ ko dc bỏ qua
       
	 * */
	
}
 

@Test
public void TC_02_Find_Eleemnts() {
	// Một List có thế chứa 1 hoặc nhiều hoặc ko chứa gì hết (rỗng)
	// Find multiple elements
	List<WebElement> elements;
	
	// Case 1: tìm thấy 1 matching node
	// - Nó sẽ trả về 1 list chứa 1 element (node) đó => size = 1
	System.out.println("Start 1: " + getDayTimeNow());
	elements = driver.findElements(By.cssSelector("input#email"));
	System.out.println("End 1: " + getDayTimeNow());
	System.out.println("List 1: " + elements.size());
	
	
	// Case 2: ko tìm thấy node nào hết
	// - Cũng phải chờ hết timeout của implicit
	// - Trong thời gian chờ cơ chế tìm lại mỗi nữa giây 1 lần
	// - Chờ hết timeout thì ko đánh fail testcase
	// - Ko ném ra ngoại lệ nào hết
	// - Vẫn chạy các step tiếp theo
	System.out.println("Start 2: " + getDayTimeNow());
	elements = driver.findElements(By.cssSelector("input#tiki"));
	System.out.println("End 2: " + getDayTimeNow());
	System.out.println("List 2: " + elements.size());
	
	
	// Case 3: Tìm thấy nhiều hơn 1 node
	//- Nó sẽ trả về 1 list chứa n element (node) đó => size = n
	System.out.println("Start 3: " + getDayTimeNow());
	elements = driver.findElements(By.cssSelector("div#pageFooter a"));
	System.out.println("End 3: " + getDayTimeNow());
	System.out.println("List 3: " + elements.size());
	
	
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

public String getDayTimeNow() {
	Date date = new Date();
	return date.toString();
	
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
