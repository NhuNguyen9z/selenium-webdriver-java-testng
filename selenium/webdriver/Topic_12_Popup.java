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



public class Topic_12_Popup {

	WebDriver driver;	
	//WebDriverWait  explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;		
		//explicitWait = new WebDriverWait(driver,10); // time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
//@Test
public void TC_01_Fixed_Popup() {
	
	driver.get("https://ngoaingu24h.vn");
	driver.findElement(By.cssSelector("button.login_")).click();
	
	// Verify kiểm tra hiển thị popup
	Assert.assertTrue(driver.findElement(By.cssSelector("div.in>div.modal-dialog")).isDisplayed()); 
	
	// input user
	driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='account-input']")).sendKeys("automationfc");
	//sleepInSecond(1);
	
	// input password
	driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='password-input']")).sendKeys("automationfc");
	//sleepInSecond(1);
	
	// Click đăng nhập
	driver.findElement(By.xpath("//div[@class='modal fade in']//button[text()='Đăng nhập']")).click();
	//sleepInSecond(1);
	
	// Trong cssSelector nếu dùng dấu ">" thì đi đến 1 node còn nếu dùng khoảng cách " " thì đi đến nhiều node 
	// Verify kết quả
	Assert.assertEquals(driver.findElement(By.cssSelector("div.modal.fade.in div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	
	// Click close
	driver.findElement(By.cssSelector("div.in button.close")).click();
	
	// Verify check xem còn hiển thị popup không
	Assert.assertFalse(driver.findElement(By.cssSelector("div.modal.fade>div.modal-dialog")).isDisplayed());
	
	
	// Viết với xpath
	//Assert.assertFalse(driver.findElement(By.xpath("//div[@class='modal fade']/div")).isDisplayed());
	
}
 

//@Test
public void TC_02_Random_In_DOM() {
	
	driver.get("https://blog.testproject.io/");
		
	WebElement randomPopup = driver.findElement(By.cssSelector("div.mailch-wrap")); 
	
	// Kiếm tra Popup is displayed thì bấm Close popup để thực hiện các Step tiếp theo
	if(randomPopup.isDisplayed()) {
		// Close popup
		System.out.println("Popup is displayed");
		driver.findElement(By.cssSelector("div#close-mailch")).click();
		sleepInSecond(1);
	} else {
		System.out.println("Popup is not displayed");
		
	}
	
	// Nếu nó không thực hiện thì thực hiện các Step sau luôn
	driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
	driver.findElement(By.cssSelector("#search-2 span.glass")).click();
	sleepInSecond(3);
	
	// Verify tile chứa text = Selenium
	List<WebElement> aticleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
	
	// chạy qua từng element trong list
	for (WebElement aticle : aticleTitle) {	
		Assert.assertTrue(aticle.getText().contains("Selenium"));		
		}
	
	
	}

//@Test
public void TC_03_POPUP_NOT_IN_DOM(){	
	driver.get("https://shopee.vn/");
	
	// Nếu element không có trong DOM thì hàm findElement ko tìm thấy
	// Chờ hết timeout của implicit
	// Đánh fail testcase ngay tại step đó luôn
	// Throw ra 1 exception: NoSuchElement
	// WebElement popup = driver.findElement(By.className("home-popup__content"));
	
	// Nếu element ko có trong DOM thì hàm findElements ko tìm thấy
	// Nó sẽ trả về 1 list empty (Size = 0)
	// Ko đánh fail testcase
	// Ko Throw exception
	
	List<WebElement> pop_up = driver.findElements(By.className("home-popup__content"));
	
	if(pop_up.size()>0 && pop_up.get(0).isDisplayed()) {
		// Close popup
		System.out.println("Popup is displayed");
		driver.findElement(By.className("shopee-popup__close-btn")).click();
		sleepInSecond(2);
		
		
	} else {
		System.out.println("Popup is not displayed");
	}
	
	driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Iphone 13");	
	driver.findElement(By.cssSelector("div.shopee-searchbar>button")).click();
	
}


//@Test
public void TC_04_Popup_not_in_DOM() {
	driver.get("https://dehieu.vn/");
	
	List<WebElement> pop_up = driver.findElements(By.cssSelector("div.popup-content"));
	
	
	if(pop_up.size() > 0 && pop_up.get(0).isDisplayed()) {
		System.out.println("Popup is displayed");
		driver.findElement(By.cssSelector("div.popup-content>button")).click();
		
	} else {
		System.out.println("Pop up is not displayed");
	}
	
	driver.findElement(By.xpath("//a[text()='Đăng nhập']"));
	driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
	
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("automationfc@gmail.com");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("automationfc");
	driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
	
	// Verify alert
	Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Bạn chưa đăng ký tại trường!')]")).getText(), "Bạn chưa đăng ký tại trường!");
	
}

@Test
public void TC_05_Popup_Not_In_DOM() {
	driver.get("https://vnk.edu.vn/");
	
	List<WebElement> popup = driver.findElements(By.xpath("//div[@data-style='cb_style_7']"));
	
	// Nếu popup is displayed thì Close popup
	if(popup.size()>0 && popup.get(0).isDisplayed()) {
		System.out.println("Popup is displayed");
		// Close popup
		driver.findElement(By.xpath("//div[@data-css='tve-u-155b475efe5a993']")).click();
	}
	
	else {
		System.out.println("Popup is not displayed");
	}
	
	driver.findElement(By.xpath("//a[@title='Khóa học ONLINE']"));
	driver.findElement(By.xpath("//a[@title='Khóa học ONLINE']")).click();
	sleepInSecond(2);
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
	
}
