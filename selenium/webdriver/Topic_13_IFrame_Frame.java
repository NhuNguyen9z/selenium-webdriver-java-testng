package webdriver;

import java.util.List;
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



public class Topic_13_IFrame_Frame {

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
	
// Trong Selenium iframe và frame đều handle như nhau đều dùng hàm frame()
@Test
public void TC_01_IFrame() {
	
	driver.get("https://kyna.vn/");
	scrollToButtomPage();
	//Muốn thao tác trong iframe/ frame trước tiên bắt buộc phải switchTo vào nó trước rồi mới thao tác những dữ liệu trong iframe/ frame này được	
	// Có 3 cách thao tác với iframe/ frame
	// Cách 1: Index --ko dùng -- vì độ chính xác ko cao  nếu thêm sửa xóa 1 iframe/ frame nào đó thì index thay đổi) 
	// driver.switchTo().frame(0); 
	
	// Cách 2: Name ID -- ko dùng vì rất ít trường hợp iframe/ frame có id
	
	// Cách 3: Element -- hay dùng cách này nhất vì có rất nhiều cách để findElement với nhiều locator khác nhau
	// Switch vào facebook fanpage iframe
	driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
	
	// Verify facebook page = 166K likes
	Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
	
	// Muốn thao tác iframe khác thì Switch về parent trước
	driver.switchTo().defaultContent();
	
	// Switch vào chat iframe
	driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
	sleepInSecond(2);
	
	// click vào khung chat
	driver.findElement(By.cssSelector("div.button_bar")).click();
	sleepInSecond(2);
	
	// Click vào button Gửi tin nhắn
	driver.findElement(By.cssSelector("input.submit")).click();
	
	//sleepInSecond(2);
	
	// Verify error message
	Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div.error_message")).getText(), "Tên của bạn chưa được nhập");

	Assert.assertEquals(driver.findElement(By.xpath("//select[@id='serviceSelect']/following-sibling::div[contains(text(),'Bạn chưa chọn dịch vụ hỗ trợ')]")).getText(), "Bạn chưa chọn dịch vụ hỗ trợ");
	
    Assert.assertEquals(driver.findElement(By.cssSelector("textarea.input_textarea+div.error_message")).getText(), "Tin nhắn chưa được nhập");
    
    // Back to Parent page
    driver.switchTo().defaultContent();
    
    driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
    sleepInSecond(2);
    
    driver.findElement(By.cssSelector("button.search-button")).click();
    sleepInSecond(2);
    
    List<WebElement> courseName = driver.findElements(By.cssSelector("section li h4"));
    
    // Verify courseName = 10
    Assert.assertEquals(courseName.size(), 10);
    
    //Veriry course chứa từ Excel
    for (WebElement course : courseName) {
    	System.out.println(course.getText());
    	
    	// Cách 1: Conver từ khóa qua Lowercase nên kết quả trả về sẽ ko phân biệt viết hoa hay thường
    	Assert.assertTrue(course.getText().toLowerCase().contains("excel"));
    	
    	// Cách 2: dùng matches không quan tâm keyword nằm ở đầu hay cuối đều convert qua Lowercase nên kết quả trả về sẽ ko phân biệt viết hoa hay thường
    	Assert.assertTrue(course.getText().toLowerCase().matches("(.*)excel(.*)"));
		
	}
    
    
}
 

//@Test
public void TC_02_Frame() {
	driver.get("https://netbanking.hdfcbank.com/netbanking/");
	
	// Switch vào Login frame
	driver.switchTo().frame("login_page");
	
	// input User ID
	driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
	
	// Click button Continue
	driver.findElement(By.cssSelector("div.inputfield>a")).click();
	
	// Verify password textbox is Displayed
	Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
	
	// Click vào Terms and Conditions ở footer
	driver.findElement(By.xpath("//div[@class='footer-btm']/a[text()='Terms and Conditions']")).click();
	
	
	
	
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

public void scrollToButtomPage() {
	jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
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
