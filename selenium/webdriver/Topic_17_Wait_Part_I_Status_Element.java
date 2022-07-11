package webdriver;

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



public class Topic_17_Wait_Part_I_Status_Element {

	WebDriver driver;	
	WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	By confirmEmail =  By.xpath("//input[@name='reg_email_confirmation__']");
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,5); // map driver vào, time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		driver.get("https://vi-vn.facebook.com/");
	}
/* CÁC ELEMENT STATUS/ CONDITION
 * Case 1: Element: hiển thị ở trên UI và có xuất hiện trong DOM (HTML)
 * Case 2: Element ko hiển thị trên UI nhưng vẫn xuất hiện trong DOM
 * Case 3: Element ko hiển thị trên UI và ko có trong DOM
 * 
 * Điều kiện của 1 element: hiển thị trên UI hay ko? - có trong HTML hay ko?
 * 
 * Trạng thái của element: có 4 trạng thái
 * 1. Visible/trong code là visibility (hàm dùng để wait element mong muốn nó hiển thị) == Display/ trong code là isDisplay (hàm trong selenium dùng để verify elemnent hiển thị đúng/ sai)
 *  ---> visible thỏa mãn điều kiện 1: phải hiển thị trên UI, phải có trong DOM
 * 2. Invisible/ trong code là invisibility: element có trong DOM với ko có trong DOM
 * 3. Presence/ trong code là presence: điều kiện bắt buộc là phải có element trong DOM ko quan tâm có trên UI hay ko
 * 4. Staleness/ trong code là staleness: Element ko hiển thị trên UI và ko có trong DOM
 * 
 * Thực tế khi làm dự án chỉ quan tâm hai cái: là hiện thị vs ko hiển thị ---> đó là 2 cái dùng nhiều nhất
 * 
 * */
	

//@Test
public void TC_01_Visible() {
	
	driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
	driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("automationfc@gmail.com");
	
	// Wait cho element dc hiển thị (Visible) phải thỏa mãn điều kiện là: hiển thị trên UI và có trong DOM ---> case này làm nhiều nhất
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmail));
	
	// Chờ cho element hiển thị rồi mới senkeys thì chạy rất ổn định
	driver.findElement(confirmEmail).sendKeys("automationfc@gmail.com");
	
	
}
 

//@Test
public void TC_02_Invisible_01_In_DOM() {
	// Case 1: Element Ko CÓ trên UI và CÓ trong DOM (HTML)
	
	driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmail));
	driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
	
	}

//@Test
public void TC_02_Invisible_02_Not_In_DOM(){	
	// Case 2: Element Ko có trên UI và KO có trong DOM
	driver.navigate().refresh();
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmail));
	
}


// Dùng presence khi muốn wait cho element ko nhìn thấy bằng mắt thường, presence wait có thể chờ dc cho all element dc load ra
//@Test
public void TC_03_Presence() {
	driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
	
	// Wait presence (in DOM - ko có trên UI)
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmail));
	driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("automationfc@gmail.com");
	
	// Wai presence (in DOM - có trên UI)
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmail));
	
	
}

@Test
public void TC_04_Staleness() {
	driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	
	// 1.
	driver.findElement(By.cssSelector("button#SubmitCreate")).click();
	
	// 2. element tại bước 1 bị update lại - no longer attach to the DOM
	WebElement errorMessage = driver.findElement(By.cssSelector("div#create_account_error"));
	
	// 3.  Wait element Staleness: wait cho 1 element ko còn trạng thái cũ nữa
	explicitWait.until(ExpectedConditions.stalenessOf(errorMessage));
	
	// Element đã thay đổi trạng thái nếu lấy ra sử dụng nữa thì sẽ bị lỗi --- nếu muốn ko bị lỗi thì phải findElement lại
	// StaleElementException: element đã bị thay đổi trạng thái rồi mà vẫn lôi ra để thao tác
	
	
	
}



@AfterClass
public void afterClass() {
	//driver.quit();
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
