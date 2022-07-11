package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdownlist {
	WebDriver driver;
	Select select; // khai bao
	JavascriptExecutor jsExecutor; // Cho phép thực thi đoạn javascript trong trình duyệt
	List<String> expectedItemtext;
	
	String firstName, lastName, emailAddress, companyName, day, month, year;
	
// WebDriver ko thể new được mà phải new những thằng con đang kế thừa WebDriver
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;  // Ép kiểu driver qua interface của javascript -- ép kiểu tường minh
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Tommy";
		lastName = "Deep";
		emailAddress = "Tommy" + generateEmail();
		companyName = "Automation";
		day = "10";
		month = "May";
		year = "2000";
		
		expectedItemtext =  new ArrayList<String>(Arrays.asList("Month","January","February","March","April","May","June",
				"July","August","September","October","November","December"));
		
	}

	//@Test
	public void TC_01_Nopcommerce() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day); // Chọn items trong dropdown  -- hàm này hay dùng nhất vì dễ nhìn thấy bằng mắt
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day); // Kiểm tra đã chọn đúng item này hay chưa
		
		select =  new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Newsletter")).click();
		driver.findElement(By.id("Password")).sendKeys("12345678");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("12345678");
		
		clickByJS(By.id("register-button"));
		//driver.findElement(By.id("register-button")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector(".ico-account")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		// Mỗi dropdown là 1 item riêng nên phải new lại nó, nên ko được dùng lại
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		/*
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "10"); // Kiểm tra được đã chọn đúng item này hay chưa
		Assert.assertEquals(select.getOptions().size(), 32); // Dùng verify xem trong dropdown này có tổng cộng bao nhiêu item
		Assert.assertFalse(select.isMultiple()); // Verify dropdown này ko chọn nhiều item cùng 1 lúc
		*/
		
	}
	
	@Test
	public void TC_02_() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		List<WebElement> allItems =  select.getOptions();  // select 1 item trong list
		List<String> actualItemsText = new ArrayList<>();  // ArrayList là class đang kế thừa  List interface
		// Duyệt qua tất cả các items đang có trong list
		for(WebElement item : allItems) {
			//System.out.println(item.getText());
			actualItemsText.add(item.getText()); // mỗi lần getText() xong sẽ gán cái text vào cái list actualItemsText bằng cách add(item.getText()) vào
			//để kiểm tra cái list mong muốn
		}
		// Verify  2 cái list bằng nhau
		Assert.assertEquals(expectedItemtext, actualItemsText);
	}

	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickByJS (By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt() + "@email.com";
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
