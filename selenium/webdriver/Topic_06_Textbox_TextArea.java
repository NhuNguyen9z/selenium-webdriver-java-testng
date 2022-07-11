package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By.ByCssSelector;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String emailAddress, loginPageUrl, userID, password, customerID; // khai báo
	
	// Data test (New Customer)
	String name, dob, address, city, state, pin, phone;
	
	// Data test (Edit Customer)
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	
	//UI (New Customer/ Edit Customer)
	By nameTextboxBy = By.name("name");
	By dobTextboxBy = By.name("dob");
	By genderTextbox = By.name("gender");
	By addressTextareaBy = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox =  By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox =  By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		
		// Init data (New Customer)
		emailAddress = "jonlips" + generateEmail();		
		name = "Johny Deeps";
		dob = "2000-12-22";
		city = "California";
		address = "123 Broadway";
		state = "United States";
		pin = "790123";
		phone = "6505551234";
		
		// Init data (Edit Customer)
		editAddress = "345 New Jersey";
		editCity = "London";
		editState = "England";
		editPin = "123567";
		editPhone = "6595559000";
		editEmail = "Tomy" + generateEmail();
		
		
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl(); // khi thay đổi server thì ko bị ảnh hưởng url, mà sẽ lấy url hiện tại
		// dữ liệu này ko ảnh hưởng test case nếu có sự thay đổi về url
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click(); // Button thì dùng click
	    userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText(); // Khởi tạo gán giá trị cho userID
		// vì hàm getText() trả về kiểu String nên sẽ tạo 1 biến kiểu String để hứng dữ liệu
		// trước tiên sẽ tìm element với cú pháp xpath này, nếu nó tìm thấy xong sẽ lấy text ra, khi lấy text xong sẽ trả về lại biến userID lúc đó userID sẽ có data
		
	    password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText(); // Khởi tạo gán giá trị cho password
				
		
		
	}
		
	@Test
	public void TC_02_Login () {
		driver.get(loginPageUrl);
	//driver.getCurrentUrl();
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		driver.findElement(addressTextareaBy).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys("123456");
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailAddress);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}
	
	@Test
	public void TC_04_Edit() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).clear();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify Disable Customer
		Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextboxBy).isEnabled());
		
		// Verify Customer
		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobTextboxBy).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(addressTextareaBy).getText(), address);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), emailAddress);
		
		// Edit Customer -- truoc khi edit sendkey thi phải clear xóa data cũ để nhập data mới vào
		driver.findElement(addressTextareaBy).clear();
		driver.findElement(addressTextareaBy).sendKeys(editAddress);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(editState);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(editPhone);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(), "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.net";
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
