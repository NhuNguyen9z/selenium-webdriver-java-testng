package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;




public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	boolean status;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;  // gán driver vào
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		// Verify button is Disable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status: " + status);
		Assert.assertFalse(status); // đang mong muốn disable nên assertFalse vì disable sẽ trả về False
		
		driver.findElement(By.xpath("//div[@class='fhs-input-group']/input[@id='login_username']")).sendKeys("0909667809");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
		//driver.findElement(By.cssSelector(".fhs-btn-login")).submit();
		
		//Verify button is Enable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertTrue(status); // đang mong muốn là Enable nên trả về Status là True
		
		// Refesh lại trang
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		// Trick
		// Remove disable attribute of Login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(5);
		
		// Verify button is Enable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Status button = " + status);
		Assert.assertTrue(status);
		
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		// Refesh lại trang
		driver.navigate().refresh();
	}

	@Test
	public void TC_02_Radio_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		
		// Click vào checkbox
		checkToCheckboxOrRadioButton(rearSideCheckbox);
		sleepInSecond(2);
		
		
		// Verify checkbox is selected
		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
		
		
		
		// Click vào checkbox để bỏ chọn
		uncheckToCheckbox(rearSideCheckbox);		
		sleepInSecond(2);
		
		// Verify checkbox is deselected
		Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
		
		// Radio button
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By oneDotFourRadio = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
				
		// Click vào radiobutton
		checkToCheckboxOrRadioButton(oneDotFourRadio);
		sleepInSecond(2);
		
		// Verify radiobutton is selected
		Assert.assertTrue(driver.findElement(oneDotFourRadio).isSelected());
		
		By twoDotZero = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
				
		// Click vào Radio button
		checkToCheckboxOrRadioButton(twoDotZero);
		sleepInSecond(2);
		
		// Verify radio button là isSelected
		Assert.assertTrue(driver.findElement(twoDotZero).isSelected());
	}

	
	public void TC_03_Checkbox_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Select all checkboxes
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']")); // nhiểu element thì dùng List<WebElement> -- còn 1 element thì dùng WebElement
		
		// Duyệt qua từng phần tử trong dãy
		for(WebElement checkbox : checkboxes) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		
		for (WebElement checkbox : checkboxes) { // cho chạy tiếp 1 vòn lặp
			Assert.assertTrue(checkbox.isEnabled()); // để verify all checkboxes đều chọn thành công
		}
	}
	
	
	public void TC_04_Radio_Checkbox_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		/*
		By winterRadio = By.xpath("//input[@value='Winter']");
		// Trường hợp 1: thẻ input bị ẩn ko click được + có thể verify được
		checkToCheckboxOrRadioButton(winterRadio);
		sleepInSecond(1);
				
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		*/
		
		/*
		// Trường hợp 2: thẻ span để click (hiển thị) + ko verify được
		By winterSpan = By.xpath("//span[contains(text(), 'Winter' )]");
		driver.findElement(winterSpan).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(winterSpan).isSelected());
		*/
		
		/*
		// Trường hợp 3: thẻ span để click + thẻ input để verify
		driver.findElement(By.xpath("//span[contains(text(), 'Winter' )]")).click();
		sleepInSecond(2);
		// Verify radio button is isSlected
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Winter']")).isSelected());
		// Nhược điểm: define 2 locator cho 1 element
		// Maintain -> bảo trì nhiều chỗ
		*/
		
		// Trường hợp 4: Thẻ input dùng JS để click + để verify luôn  -- dùng 1 locator để click và verify luôn
		By winterRadio = By.xpath("//input[@value='Winter']");
		clickToElementByJS(winterRadio);
		sleepInSecond(2);
		// Verify is isSelected
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());  // dùng 1 locator để click và verify luôn
	}
	
	
	public void TC_05_Radio_Checkbox_Custom() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		
		// Verify checkbox is deselected
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']")).isDisplayed());
		driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']/div[contains(@class,'exportInnerBox')]")).click();		
		sleepInSecond(2);
		
		// Verify checkbox is Selected
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());
		// kiểm tra trong chính xpath chứ ko kiểm tra trong isSelected, kiểm tra xpath thì dùng isDisplayed vì isSelected ko dùng cho div được
		// dùng isDisplayed để kiểm tra 1 element Selected
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollToElement(By by) {
		WebElement element = driver.findElement(by); // define element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		sleepInSecond(1);
	}
	
	public void clickToElementByJS(By by) { // tham số là By
		WebElement element = driver.findElement(by); // define web element
		jsExecutor.executeScript("arguments[0].click;", element);
		
	}
	
	public void checkToCheckboxOrRadioButton(By by) {
		WebElement checkbox = driver.findElement(by); // define web element
		if(!checkbox.isSelected()) { // nếu chưa chọn thì click vào checkbox -- còn nếu chọn rồi thì ko click
			checkbox.click();  // 
		}
	}
	
	public void uncheckToCheckbox(By by) {
		WebElement checkbox = driver.findElement(by); // define web element
		if(checkbox.isSelected()) {
			checkbox.click();
		}
		
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
