package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {

	WebDriver driver;
	// WebDriverWait explicitWait;
	Actions action;
	JavascriptExecutor jsExecutor;

	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver); // khởi tạo và map driver vào Actions
		jsExecutor = (JavascriptExecutor) (driver); // map driver qua kiểu của JavascriptExecutor

		// explicitWait = new WebDriverWait(driver,10); //timeout truyen den driver timeout 10 second

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// KHI DÙNG THỰC TẾ CHỈ CÓ HÀM HOVER LÀ DÙNG NHIỀU NHẤT

	// @Test
	public void TC_01_Hover_Mouse_1() {

		driver.get("https://automationfc.github.io/jquery-tooltip");

		// Hover chuột vào textbox
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(3);

		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

	}

	// @Test
	public void TC_01_Hover_Mouse_2() {

		driver.get("https://tiki.vn");

		// input
		driver.findElement(By.cssSelector(".arrowIcon")).click();
		sleepInSecond(2);

		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='tel']")).isDisplayed());

	}

	// @Test
	public void TC_01_Hover_Mouse_3() {
		driver.get("https://www.myntra.com/");

		// Hover chuột vào textbox
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text() = 'Kids']"))).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text() = 'Home & Bath']")).click();

		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");

	}

	@Test
	public void TC_01_Hover_Mouse_4() {
		driver.get("https://www.fahasa.com/");
		// sleepInSecond(3);
		driver.findElement(By.cssSelector("#NC_IMAGE1")).click();

		// Hover chuột vào textbox
		// action.moveToElement(driver.findElement(By.cssSelector(".icon_menu"))).perform();
		// driver.findElement(By.xpath("//li[@class='parent dropdown aligned-left']/a")).click();

		// Verify
		// Assert.assertTrue(driver.findElement(By.xpath("//li[@class='category4']/strong")).isDisplayed());
		// Assert.assertEquals(driver.getCurrentUrl(), "https://www.fahasa.com/sach-trong-nuoc.html");

	}

	// @Test
	public void TC_02_Click_And_Hold() {

		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector(".ui-selectable li"));
		System.out.println("Number of rectangle = " + rectangleNumber.size());

		action.clickAndHold(rectangleNumber.get(0)) // Click and Hold vào element đầu tiên
				.moveToElement(rectangleNumber.get(3)) // -> Hover (di) chuột đến element đích
				.release() // -> Nhả chuột trái ra
				.perform(); // Thực thi các action

		sleepInSecond(3);

		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectable>li.ui-selected")).size(), 4);

	}

	// @Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector(".ui-selectable>li"));

		// Nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();

		// Chọn các element đích
		action.click(rectangleNumber.get(0)).click(rectangleNumber.get(2)).click(rectangleNumber.get(5)).click(rectangleNumber.get(10)).perform();

		// Nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();

		// Verify
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectable>li.ui-selected")).size(), 4);
	}

	// @Test
	public void TC_03_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Scroll to element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));

		// Double click element
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

		// Verify
		// Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).getText(), "Hello Automation Guys!"); // Cách 1
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
