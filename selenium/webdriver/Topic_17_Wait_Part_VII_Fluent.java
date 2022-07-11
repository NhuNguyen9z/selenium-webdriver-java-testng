package webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;



public class Topic_17_Wait_Part_VII_Fluent {

	WebDriver driver;	
	WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//explicitWait = new WebDriverWait(driver,10); // map driver vào, time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}
	
// Trên dự án thực tế thì FluentWait ít khi dùng vì đã có ImplicitWait vs ExplicitWait xử lý rồi
// Dùng FluentWait: khi mình muốn custom cái polling time cho cơ chế tìm lại element là mỗi nửa giây

public void TC_01_Fluent_Wait() {
	
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	clickToElement(By.cssSelector("div#start>button"));
	Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
}
 

public void TC_02_Implicit_Wait() {
	
	driver.get("https://automationfc.github.io/fluent-wait/");	
	driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
	WebElement element = driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']"));
	Assert.assertTrue(element.isDisplayed());
	
	}


public void TC_03_Explicit_Wait(){	
	driver.get("https://automationfc.github.io/fluent-wait/");	
	explicitWait = new WebDriverWait(driver, 13);
	By element = By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']");
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(element));
	
	}

@Test
public void TC_04_Fluent_Wait() {
	driver.get("https://automationfc.github.io/fluent-wait/");
	WebElement countDownTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(countDownTime); // tham số của hàm apply là gì thì new fluentWait vs type đó
	fluentWait.withTimeout(Duration.ofSeconds(13))
	.pollingEvery(Duration.ofMillis(100))
	.ignoring(NoSuchElementException.class)
	.until(new Function<WebElement, Boolean>(){
		public Boolean apply(WebElement countdown) {
			System.out.println(countdown.getText());
			return countdown.getText().endsWith("00");
		}
	});
	
}

//@Test
public void TC_05_() {
	
	
}



@AfterClass
public void afterClass() {
	driver.quit();
}

public WebElement getWebElement(By locator) {
	// Khai báo vào khởi tạo Fluent Wait
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			// Tổng thời gian chờ là bao nhiêu giây
			.withTimeout(Duration.ofSeconds(15))
			// Thời gian để lặp lại là bao nhiêu s
			.pollingEvery(Duration.ofSeconds(1))
			// Nếu sau mỗi lần lặp lại mà gặp exception thì sẽ ignore
			.ignoring(NoSuchElementException.class);
	WebElement element = wait.until(new Function<WebDriver, WebElement>(){
		public WebElement apply(WebDriver driver) {
			return driver.findElement(locator);
		}
	});
	return element;
}

public void clickToElement(By locator) {
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(15))
			.pollingEvery(Duration.ofSeconds(1))
			.ignoring(NoSuchElementException.class);
	
	WebElement element = wait.until(new Function<WebDriver, WebElement>(){
		public WebElement apply(WebDriver driver) {
			return driver.findElement(locator);
		}
	});
	element.click();
}

public boolean waitForElementAndDisplayed(By locator) {
	WebElement element = getWebElement(locator);
	FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
			.withTimeout(Duration.ofSeconds(15))
			.pollingEvery(Duration.ofSeconds(1))
			.ignoring(NoSuchElementException.class);
	
	boolean isDisplayed = wait.until(new Function<WebElement, Boolean>(){
		public Boolean apply(WebElement element) {
			boolean flag = element.isDisplayed();
			return flag;
		}
	});
	return isDisplayed;
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
