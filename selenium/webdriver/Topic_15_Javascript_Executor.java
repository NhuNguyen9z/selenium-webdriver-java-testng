package webdriver;

import java.util.Random;
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



public class Topic_15_Javascript_Executor {

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
//Get ValidationMessage phải dùng JE chứ ko dùng getText() trong Selenium được
//Các hàm hay dùng trong Javascript Executor: Click an element, Scroll to element, Highlight element
//Các hàm có thể dùng: Remove an attribute, Get html5 validation message
	
//@Test
public void TC_01_() {
	
	
}
 

//@Test
public void TC_02_Sieu_Thi_May_Moc_Thiet_Bi() {
	driver.get("https://sieuthimaymocthietbi.com/account/register");
	String validationMessage;
	
	// click button Đăng ký
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();	
	// Get html5 validation message của Last name
	validationMessage = getElementValidationMessage(("//input[@id='lastName']"));
	// Verify html5 validation message
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
	
	// First name
	// Click vào button Đăng ký
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
	// Get html5 Validation Message
	validationMessage = getElementValidationMessage("//input[@id='firstName']");
	// Verify html5 Validation message
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	// Nhập First name
	driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("FC");
	
	
	// Click button Đăng ký
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
	// Get html5 validation message
	validationMessage = getElementValidationMessage("//input[@id='email']");
	// Verify html5 validation message
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	
	// Clear data cũ
	driver.findElement(By.xpath("//input[@id='email']")).clear();
	// Nhập email sai định dạng
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("@d");
	// Get html5 validation message
	validationMessage = getElementValidationMessage("//input[@id='email']");
	// Verify html5 validation message
	Assert.assertEquals(validationMessage, "Please enter an email address.");
	
	// Clear data cũ
	driver.findElement(By.xpath("//input[@id='email']")).clear();
	// Nhập địa chỉ email hợp lệ
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@gmail.com");
	
	
	// Click button Đăng ký
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
	// Get html5 validation message
	validationMessage = getElementValidationMessage("//input[@id='password']");
	// Verify html5 validation message
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	// Nhập passwoed
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123");
	// Click button Đăng ký
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
	
	}

//@Test
public void TC_03_Register(){	
	
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
	
	driver.get("http://demo.guru99.com/v4/");
	
	// Register
	driver.findElement(By.xpath("//a[text()='here']")).click();
	driver.findElement(By.name("emailid")).sendKeys(emailAddress);
	driver.findElement(By.name("btnLogin")).click(); // Button thì dùng click
    userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText(); // Khởi tạo gán giá trị cho userID
	// vì hàm getText() trả về kiểu String nên sẽ tạo 1 biến kiểu String để hứng dữ liệu
	// trước tiên sẽ tìm element với cú pháp xpath này, nếu nó tìm thấy xong sẽ lấy text ra, khi lấy text xong sẽ trả về lại biến userID lúc đó userID sẽ có data
	
    password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText(); // Khởi tạo gán giá trị cho password
    
    // Login
    driver.get("http://demo.guru99.com/v4/");
    driver.findElement(By.name("uid")).clear();
	driver.findElement(By.name("uid")).sendKeys(userID);
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("btnLogin")).click();	
	Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	
	
	// New Customer
	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	driver.findElement(nameTextboxBy).sendKeys(name);
	removeAttributeInDOM("//input[@id='dob']", "type");
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

//@Test
public void TC_04_Create_an_Account() {
	driver.get("http://live.techpanda.org/");
	
	// Click an element use Javascript Executor
	clickToElementByJS("//header[@class='page-header']//a[text()='My Account']");
	sleepInSecond(2);
	clickToElementByJS("//a[@title='Create an Account']");
	
	// Senkey to element dùng JE
	sendkeyToElementByJS("//input[@id='firstname']", "Automation");
	sendkeyToElementByJS("//input[@id='middlename']", "test");
	sendkeyToElementByJS("//input[@id='lastname']", "FC");
	sendkeyToElementByJS("//input[@id='email_address']", generateEmail());
	sendkeyToElementByJS("//input[@id='password']", "123456!@#");
	sendkeyToElementByJS("//input[@id='confirmation']", "123456!@#");
	
	// Click button Register bằng Javascript Executor
	clickToElementByJS("//button[@title='Register']");
	
	// Verify message xuất hiện khi đăng ký thành công
	getElementValidationMessage("//span[text()='Thank you for registering with Main Website Store.']");
	
	// Click Logout bằng JE
	clickToElementByJS("//a[text()='Log Out']");
	
	// Kiểm tra hệ thống navigate về homepage sau khi logout thành công
	Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	
	
}

@Test
public void TC_05_Register_Rode() {
	driver.get("https://warranty.rode.com/");
	String validationMessage;
	
	// Click vào button Register
	driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
	// Get validation message
	validationMessage = getElementValidationMessage("//input[@id='firstname']");
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("automation");
	
	
	
	// Click vào button Register
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		// Get validation message
		validationMessage = getElementValidationMessage("//input[@id='surname']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='surname']")).sendKeys("FC");
		
	// Click vào button Register
	driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
	// Get validation message
	validationMessage = getElementValidationMessage("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']");
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	driver.findElement(By.xpath("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']")).sendKeys("FC");
	// Get validation message
	validationMessage = getElementValidationMessage("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']");
	Assert.assertEquals(validationMessage, "Please enter an email address.");
	
	// Clear data cũ
	driver.findElement(By.xpath("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']")).clear();	
		
	// Click vào button Register
	driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
	// Get validation message
	validationMessage = getElementValidationMessage("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']");
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	driver.findElement(By.xpath("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']")).sendKeys("FC@gmail.@");
	// Get validation message
	validationMessage = getElementValidationMessage("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']");
	Assert.assertEquals(validationMessage, "Please enter an email address.");
	driver.findElement(By.xpath("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='email']")).sendKeys("automation@gmail.com");
	
	// Click button Register
	driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
	// Get validation message
	validationMessage = getElementValidationMessage("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='password']");
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	driver.findElement(By.xpath("//div[@class='card-header' and contains(text(),'Register')]/following-sibling::div//input[@id='password']")).sendKeys("123");
	
	// Click button Register
	driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
	// Get validation message
	validationMessage = getElementValidationMessage("//input[@id='password-confirm']");
	Assert.assertEquals(validationMessage, "Please fill out this field.");
	driver.findElement(By.xpath("//input[@id='password-confirm']")).sendKeys("123");
	
	driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
	hightlightElement("//button[contains(text(),'Register')]");
	
	
	
}



@AfterClass
public void afterClass() {
	driver.quit();
}

public String generateEmail() {
	Random rand = new Random();
	return rand.nextInt(9999) + "@mail.net";
	
}


public void sleepInSecond(long time) {
	try {
		Thread.sleep(time*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

// CÁC HÀM DÙNG JAVASCRIPT EXECUTOR

public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean areExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void hightlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
	sleepInSecond(1);
	
	// Highlight xong phải trả lại style element cũ 
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public void scrollToElementOnTop(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

//Get element ValidationMessage phải dùng Javascript Executor chứ ko dùng getText() trong Selenium được
public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
	if (status) {
		return true;
	}
	return false;
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
}

	
}
