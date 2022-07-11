package webdriver;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.openqa.jetty.html.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Topic_04_Web_Browser_Method_Parth_I {
	WebDriver driver; // Khai báo
	WebElement element;


	@BeforeClass
	public void beforeClass() {
		//Open browser
		driver =  new FirefoxDriver();  // Khởi tạo
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				
		}
	
	@Test
	public void TC_01_FindElement() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		// WebElement - lấy ra 1 element
		// Dùng 1 lần - ko tạo biến
		driver.findElement(By.id("email")).sendKeys("autotest@gmail.com");
		sleepInSecond(3);
		
		// Dùng lại nhiều lần - nên tạo biến đẻ code gọn hơn
		WebElement emailTextbox = driver.findElement(By.id("email"));
		
		emailTextbox.clear();
		emailTextbox.sendKeys("autotestelenium@gmail.com");
       
		// List<WebElement> - lấy ra nhieu WebElement
		List<WebElement> textboxes = driver.findElements(By.xpath("//input[not(@type='hidden')]"));
		
		// Tổng các phần tử đang có trong List
		System.out.println(textboxes.size());
		
		// Thao tác vs cả 4 textbox này
		for (WebElement textbox : textboxes) {
			textbox.clear();
			textbox.sendKeys("SeLenium");
			sleepInSecond(2);
		}
		
	}
	
	@Test
	public void TC_02_Element_Method() {
		element = driver.findElement(By.xpath(""));
		
		// Dùng cho textbox/ textarea/ dropdown (editable)
		element.clear();
		
		// Dùng cho textbox/ textarea/ dropdown (editable)
		element.sendKeys("");
		
		// Button/ Checkbox/ Radio Button/ Dropdown (Custom)/ Link
		element.click();
		
		// Lấy ra giá trị của 1 attribute
		element.getAttribute("placeholder");
		
		// Test GUI: font/ size/ color/ position/ location/ responsive/...
		element.getCssValue("background");
		// #3399cc
		element.getAttribute("font-size");
		// 13px
		
		// Vị trí của element vs viewport (browser);
		element.getLocation();
		
		// Chiều rộng, cao của element
		element.getSize();
		
		// Lưu hình trong quá trình chạy bị lỗi và attach vào report html
		//element.getScreenshotAs(arg0); .png/ .jpg / base64
		
		element = driver.findElement(By.cssSelector("#email"));
		String emailTextboxTagname = element.getTagName();
		// input
		// Đầu ra của hàm này sẽ là đầu vào của 1 locator khác
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@name='search']"));
		
		// Lấy ra được text của label/ header/ li/ span/ div/ (có text là get dc)
		element.getText(); // Dùng rất nhiều
		
		// Cần verify xem 1 element có hiển thị hay ko
		// Tất cả các loại element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		// Cần verify xem 1 element có thể thao tác dc hay ko (ko bị disable)
		// Tất cả các loại element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// Cần verify xem 1 element có dc chọn hay chưa (radio/ checkbox/ dropdown)
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		// Dùng cho cái form (thẻ/ tagname form)
		// Tương ứng vs hành động: nhập xong + Enter
		element.submit();
		
		
		
		
	}
	
	@Test
	public void TC_03_Submit() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.cssSelector("#email")).sendKeys("nhu@gmail.com");
		driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("#send2")).submit();
		sleepInSecond(10);
	}
	@Test(enabled = false)
	public void TC_04_Page_Source() {
		
	}
	
	@Test
	public void TC_05_Create_An_Account() {
		
		
		
	}
	
	@AfterClass
	public void affterClass() {
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
