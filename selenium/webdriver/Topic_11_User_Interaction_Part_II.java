package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_11_User_Interaction_Part_II {

	WebDriver driver;	
	WebDriverWait  explicitWait;
	
	Actions action;
	Alert alert;
	JavascriptExecutor jsExecutor;
	
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions (driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,10); //time out truyền đến driver timeout 10 second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	



@Test
public void TC_01_Double_Click() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	
	// Scroll to element
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
	sleepInSecond(2);
	
	// Double click element
	action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	
	// Verify
	// Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).getText(), "Hello Automation Guys!"); // Cách 1
	Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
	
}
 

//@Test
public void TC_02_Right_Click() {
	
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	
	// Click chuột phải
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	
	// Hover chuột vào Edit menu
	action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-edit"))).perform();
	sleepInSecond(3);
	
	// Edit menu update
	Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-edit.context-menu-hover.context-menu-visible")).isDisplayed());
	
	// Click chuột vào Edit menu
	action.click(driver.findElement(By.cssSelector(".context-menu-icon-edit"))).perform();
	
	// Wait chờ alert xuất hiện và Switch qua alert
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	//sleepInSecond(3);
	
	// Verify
	Assert.assertEquals(alert.getText(), "clicked: edit");
	
	// Accept alert
	alert.accept();
	
	}

//@Test
public void TC_03_Drag_Drop_HTML4(){	
	
	driver.get("https://automationfc.github.io/kendo-drag-drop/");
	
	WebElement smallCicle = driver.findElement(By.xpath("//div[@id='draggable']"));
	WebElement bigCicle = driver.findElement(By.xpath("//div[@id='droptarget']"));
	
	// Drag Drop chuột
	action.dragAndDrop(smallCicle, bigCicle).perform();
	sleepInSecond(3);
	
	//Verify text
	Assert.assertEquals(bigCicle.getText(), "You did great!");
	
	// Verify Color
	// Obtain color in rgb
	String s = bigCicle.getCssValue("background-color");
	System.out.println("background-color: " + s );
	
	// Convert rgb to hex
	System.out.println("Color asHex: " + Color.fromString(s).asHex());
	Assert.assertEquals(Color.fromString(s).asHex(), "#03a9f4");
	
	
	
}

//@Test
public void TC_04_t() {
	
}

//@Test
public void TC_05_t() {
	
	
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
