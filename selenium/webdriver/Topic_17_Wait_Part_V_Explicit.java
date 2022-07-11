package webdriver;

import java.io.File;
import java.util.List;
import java.util.Set;
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



public class Topic_17_Wait_Part_V_Explicit {

	WebDriver driver;	
	WebDriverWait  explicitWait;  // explicitWait: phải dùng findElement vì phải tìm được elemnent trước thì mới apply dc điều kiện
	// Wait implicit là wait ngầm định (ko có trạng thái nào cụ thể, chỉ wait cho việc tìm element) 
	// Wait explicit là wait tường minh (có trạng thái cụ thể: visible/ presence/ invisible/ staleness/ clickable/ numberLess/ greater/ ..)
	// Wait explicit: hàm invisibility chờ cho 1 element biến mất,
	//hàm visibility chờ cho 1 element hiển thị, hàm alertisPresent chờ cho 1 alert hiển thị, hàm elementToBeClickable chờ cho 1 element có thể click
	// explicit wait dùng để chờ cho trạng thái của element nào đó nó phải dc sẵn sàng thì mới dc tương tác lên element đó
	// dùng explicit wait để đảm bảo cho các action sẵn sàng thao tác và các verify sẵn sàng để đưa đi assert	
	// Wait explicit -- phục vụ cho những trạng thái của element - 0.5 giây tìm lại 1 lần nếu ko tìm thấy
	// Wait implicit -- phục vụ cho việc tìm element - 0.5 giây tìm lại 1 lần nếu ko tìm thấy
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir"); // lấy thư mục gốc
	
	By buttonStart = By.cssSelector("div#start>button");
	By loadingIcon = By.cssSelector("div#loading");
	By helloWorld = By.cssSelector("div#finish h4");
	
	String thinkPadName = "ThinkPad.jpg";
	String dellName = "Dell.jpg";
	String thinkPadFilePath = projectPath + File.separator + "uploadFiles" + File.separator + thinkPadName;
	String dellFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dellName;
	
	
	
@BeforeClass	
public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	//	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		
	}
	
//@Test
public void TC_01_Less_Than() {
	
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	
	explicitWait = new WebDriverWait(driver,15); // time out truyền đến driver timeout 15 second
	// Trước khi click thì Wait cho Start button có thể click chưa
	explicitWait.until(ExpectedConditions.elementToBeClickable(buttonStart));
	driver.findElement(buttonStart).click();
	
	// Time bị thiếu 3s
	explicitWait = new WebDriverWait(driver,2);
	
	// Bussiness: Nếu như loading icon biến mất thì Hello world text hiển thị
	
	// Wait cho loading icon biến mất
	// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	
	// Wait cho text Hello world xuất hiện
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorld));
	
	
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
}
 

//@Test
public void TC_02_Enough() {
	
   driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	
	explicitWait = new WebDriverWait(driver,15); // time out truyền đến driver timeout 15 second
	// Trước khi click thì Wait cho Start button có thể click chưa
	explicitWait.until(ExpectedConditions.elementToBeClickable(buttonStart));
	driver.findElement(buttonStart).click();
	
	explicitWait = new WebDriverWait(driver,5);
	
	// Bussiness: Nếu như loading icon biến mất thì Hello world text hiển thị
	
	// Wait cho loading icon biến mất
	//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	
	// Wait cho text Hello world xuất hiện
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorld));
	
	
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
	
	}

//@Test
public void TC_03_Greater(){	
	
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	
	explicitWait = new WebDriverWait(driver,15); // time out truyền đến driver timeout 15 second
	// Trước khi click thì Wait cho Start button có thể click chưa
	explicitWait.until(ExpectedConditions.elementToBeClickable(buttonStart));
	driver.findElement(buttonStart).click();
	
	explicitWait = new WebDriverWait(driver,15);
	
	// Bussiness: Nếu như loading icon biến mất thì Hello world text hiển thị
	
	// Wait cho loading icon biến mất
	//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	
	// Wait cho text Hello world xuất hiện
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorld));
	
	
	// Verify
	Assert.assertEquals(driver.findElement(helloWorld).getText(), "Hello World!");
	
}

//@Test
public void TC_04_Ajax_Loading() {
	explicitWait = new WebDriverWait(driver, 15);
	driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
	Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
	
	// Wait cho có thể click vào 2
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='2']")));
	driver.findElement(By.xpath("//a[text()='2']")).click();
	
	// Wait cho ajax icon loading ko còn visible
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']"))));
	
	// Verify text Selected dates khi bấm vào 2
	Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Thursday, June 2, 2022");
	
	
	// Wait cho 2 hiển thị
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='2']")));
			
	// Verify is displayed
	Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='2']")).isDisplayed());
	
	
	
	
}

//@Test
public void TC_05_Upload_File() {
	explicitWait = new WebDriverWait(driver, 15);
	driver.get("https://gofile.io/uploadFiles");
	String parentID = driver.getWindowHandle();
	By upLoadFile = By.xpath("//input[@type='file']");
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(upLoadFile));
	driver.findElement(upLoadFile).sendKeys(thinkPadFilePath + "\n" + dellFilePath);
	
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowUploadProgress div.card div.card-body")));
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success h5")));
	Assert.assertEquals(driver.findElement(By.cssSelector("div.callout-success h5")).getText(), "Your files have been successfully uploaded");
	driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).click();
	sleepInSecond(1);

	// Switch vào window/ tab con
	swichToWindowByID(parentID);
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowFolder-info-public>i")));
	
	Assert.assertTrue(driver.findElement(By.cssSelector("div#rowFolder-info-public>i")).isDisplayed());

	driver.close();
	
	// Switch về parent page
	driver.switchTo().window(parentID);
	Assert.assertTrue(driver.findElement(By.cssSelector("div.callout-success h5")).isDisplayed());
		
	
}

@Test
public void TC_06() {
	explicitWait = new WebDriverWait(driver, 30);
	driver.get("https://blog.testproject.io/");
	
	 // popup random in DOM
	 WebElement randomPopup = driver.findElement(By.cssSelector("div.mailch-wrap"));
	if(randomPopup.isDisplayed()) { // Close popup nếu displayed
		System.out.println("Popup is displayed");
		driver.findElement(By.cssSelector("div#close-mailch")).click();
	} else {
		System.out.println("Popup is not displayed");
	}
	
	driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
	driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.search-field")));
	
	List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
	//explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("h3.post-title>a")));
	for (WebElement article : articleTitle) {
		
		
		// Verify text chứa Selenium
		
		Assert.assertTrue(article.getText().contains("Selenium"));
		
		System.out.println( "Article is: " + article.getText());
		
		
	}
	
}



@AfterClass
public void afterClass() {
	driver.quit();
}

public void swichToWindowByID(String parentID) {
	
	// Get ra tất cả các tab/ window đang có
	Set<String> allWindows = driver.getWindowHandles();
	
	// Dùng vòng lặp để duyệt qua từng window
	for (String id : allWindows) {
		
		// Nếu như id nào mà khác với parentID
		if(!id.equals(parentID)) {
			// Switch vào id đó
			driver.switchTo().window(id);
		}
	}
}

public void switchToWindowByTitle(String expectTitle) {
	
	Set<String> allWindow = driver.getWindowHandles();
	for (String id : allWindow) {
		driver.switchTo().window(id);
		
		String title = driver.getTitle();
		if(title.equals(expectTitle)) {
			break;
		}
			
		
	}
}

public void closeAllWindowWithoutTitle(String parentTitle) {
	Set<String> allWindow = driver.getWindowHandles();
	for (String id : allWindow) {
		driver.switchTo().window(id);
		String title = driver.getTitle();
		if(!title.equals(parentTitle)) {
			driver.close();
		}
		
	}
	driver.switchTo().window(parentTitle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
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
