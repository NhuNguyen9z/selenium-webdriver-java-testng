package webdriver;

import java.util.List;
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



public class Topic_14_Window_Tab {
	// Driver chỉ apply cho 1 page mà nó đang đứng, nếu page khác mà click mở ra thì nó sẽ ko chờ để page dc load thành công
	WebDriver driver;	
	JavascriptExecutor jsExecutor;
	//WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	String childId;
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		//explicitWait = new WebDriverWait(driver,10); // time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
// Trong Selenium Wnhdow và Tab đều handle như nhau

//@Test // Testcase này handle cho 2 window/ tab
public void TC_01_Window_Tab() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	// Có 3 cách handle Window/ Tab
	// Cách 1: Get ra id của active Tab/ Window (driver đang đứng) --> 1 cái
	String parentID = driver.getWindowHandle();
	System.out.println(" Parent ID = " + parentID);
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	
	swichToWindowByID(parentID);
	String childID = driver.getWindowHandle();
	driver.findElement(By.cssSelector("#email")).sendKeys("child@gmail.com");
	
	swichToWindowByID(childID);
	driver.findElement(By.id("email")).sendKeys("parent@gmail.com");
	
	// Cách 2: Get ra ID của tất cả các tab/ Window đang có --> tất cả
	// Set thì ko cho phép lưu trùng, chỉ cho phép lưu giá trị duy nhất
	/*Set<String> allIDs = driver.getWindowHandles();
	for (String id : allIDs) {
		
		System.out.println("ID is = " + id);	
		if(!id.equals(parentID)) {
			childId = id;
			
		}
		
	} 
	
	*/	
	//System.out.println(driver.getTitle());
	
	//List<WebElement> buttons = driver.findElements(By.id(""));
	//List danh sách chứa những element -- tìm all những element có button lưu vào cái List
	//List cho phép lưu trùng element 
	
	
	// Cách 3: Switch vào tab/ window đó bằng ID
	// Cách 3.1: Switch qua sử dụng id --> Đúng cho 2 window/ tab
	//driver.switchTo().window(childId);
	//System.out.print(driver.getTitle());
	//driver.findElement(By.cssSelector("#email")).sendKeys("nhu@gmail.com");
	
	
}
 

@Test // Testcase này handle cho 2 hay nhiều hơn 2 window/ tab
public void TC_02_Window_Tab_2() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	String parentID = driver.getWindowHandle();
	// Click vào Facebook link
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	sleepInSecond(3);
	switchToWindowByTitle("Facebook – log in or sign up");	
	// Find element để đảm bảo switch qua đúng facebook
	driver.findElement(By.id("email")).sendKeys("facebook@gmail.com");
	
	
	// Switch qua Parent page lại (github.io)
	switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	// Click vào Tiki link
	driver.findElement(By.xpath("//a[text()='TIKI']")).click();
	sleepInSecond(3); 
	switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh"); // Switch vào tiki tab bằng title
	driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("iphone 13 promax");
	driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).click();

	
	// Switch qua Parent page lại (github.io)
	switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	// Click vào Lazada link -> hành vi của app tự động nhảy qua tab đó luôn
	driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
	sleepInSecond(3);
	switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");	// Switch vào Lazada tab bằng title
	driver.findElement(By.xpath("//input[@id='q']")).sendKeys("iphone 12 promax");
	driver.findElement(By.cssSelector("button.search-box__button--1oH7")).click();
	
	
	// Switch qua Parent page lại (github.io)
	switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	// Click vào Google link
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(1);
	switchToWindowByTitle("Google"); // Switch vào google tab bằng title
	driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Macbook pro");
	driver.findElement(By.cssSelector("div.FPdoLc input.gNO89b")).click();
	
	// Close all các tab con trừ tab Parent
	closeAllWindowWithoutParent(parentID);
	
	// Find element để đảm bảo swit qua đúng parent page
	WebElement  name = driver.findElement(By.id("name"));
	
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", name);
	name.sendKeys("automation name");
	sleepInSecond(2);
	
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("address")));
	driver.findElement(By.id("address")).sendKeys("auto address");
	sleepInSecond(2);
	
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.id("email")));
	driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
	sleepInSecond(2);
	
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.id("password")));
	driver.findElement(By.id("password")).sendKeys("123456");
	sleepInSecond(2);
	
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[text()='Submit']")));
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
	sleepInSecond(2);
	
	}

//@Test
public void TC_03_(){	
	driver.get("https://www.google.com.vn/");
	driver.findElement(By.xpath("//input[@name='q']")).sendKeys("iphone 13 promax");
	driver.findElement(By.cssSelector("div.FPdoLc input.gNO89b")).click();
	
	
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

// Hàm này Dùng cho đúng 2 windows/ tab
// Kiểm tra cái id trước
// Id khác với Parent thì mới switch
public void swichToWindowByID(String parentID) {
	
	// Get ra tất cả các tab/ window đang có
	Set<String> allWindows = driver.getWindowHandles();
	
	// Dùng vòng lặp để duyệt qua từng window
	for (String id : allWindows) {
		
		// Nếu như id nào mà khác với parentID
		if(!id.equals(parentID)) {
			// Switch vào id đó
			driver.switchTo().window(id);
		}
	}
}




// Hàm này Dùng cho 2 hoặc nhiều hơn 2 windows/ tab
// Switch vào từng window trước
// Get ra title của window đó
// Kiểm tra title với title mong muốn
// Nếu như mà bằng thì Stop không kiểm tra tiếp nữa
public void switchToWindowByTitle(String expectTitle) {
	// Dùng Set String để Get ra all window đang có
	Set<String> allWindow = driver.getWindowHandles();
	
	// Dùng vòng lặp để duyệt qua từng window
	for (String id : allWindow) {
		// Switch vào từng window
		driver.switchTo().window(id);
		
		// Get ra title của từng window
		String title = driver.getTitle();
		System.out.println(title);
		
		// Kiểm tra title với title mong muốn --> nếu == thì Stop ko kiểm tra tiếp nữa
		if(title.equals(expectTitle)) {
			break;
		}
		
	}
}


public void closeAllWindowWithoutParent(String ParentID) {
	// Set String để Get ra all các window
	Set<String> allWindow = driver.getWindowHandles();
	
	// Dùng vòng lặp để duyệt qua từng window
	for (String id : allWindow) {
		if(!id.equals(ParentID)) {
			
			// Switch vào window đó
			driver.switchTo().window(id);
			
			// Close window/ tab đó
			driver.close();  
			// Hàm Close chỉ đóng tab hiện tại
			// Hàm Quit thì đóng browser
			// Nếu chỉ có duy nhất 1 tab thì quit vs close như nhau
		}
		
	}
	// Switch về parent
	driver.switchTo().window(ParentID);
	// Hành vi trong app vs hành vi trong code không giống nhau
	// Trong code thì phải Switch thì mới qua tab/ window
	// Trong app thì click close thì quay về trang cũ
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
