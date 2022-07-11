package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.jasper.tagplugins.jstl.core.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.opera.OperaDriver;
//import org.openqa.selenium.internetexplorer.InternetExplorer.Driver;
import org.testng.annotations.Test;

public class Topic_03_Web_Browser_Method {
	WebDriver driver;


	@Test
	public void beforeClass() {
		// Mở 1 browser cụ thể nào đó
		driver = new FirefoxDriver();
		
		driver =  new ChromeDriver();
		
	//	driver =  new InternetExplorerDriver();
		
		driver = new SafariDriver();
		
		driver = new OperaDriver();
		
		driver = new EdgeDriver();
				
		}

	@Test
	public void TC_01() {
        // Mở 1 url (1 app)		
		driver.get("https://demo.nopcommerce.com/"); //*
		
		// Đóng 1 tab/ window active
		driver.close(); //*
		
		// Đóng cả browser
		driver.quit(); //*
		
		// Lấy ra url tại thời điểm page đang đứng
		String hompePageUrl = driver.getCurrentUrl(); //*
		
		// Lấy title tại page đang đứng
		String homePageTitle = driver.getTitle(); //*
		
		// Lấy ra source code tại page đang đứng
		String homePageSource = driver.getPageSource();
		
		//Lấy Window/ Tab ID hiện tại
		String homePageTabID = driver.getWindowHandle(); //*
		
		//Get ra all các Tab/ Window ID đang có
		Set<String> allWindowIDs = driver.getWindowHandles(); 
		
		// Chờ cho element có thể tìm thấy trong 1 khoảng thời gian 15s
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //*
		
		//Chờ cho 1 page duoc load thành công trong khoảng time 15s
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Chờ  cho 1 đoạn Javascript duoc inject thành công
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// Phóng to browser
		driver.manage().window().maximize();//*
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
		// History: Lưu trạng thái tốt hơn (back/ forward/..)
		driver.navigate().to("https://demo.nopcommerce.com/");
		
		driver.switchTo().alert();//*
		
		driver.switchTo().frame(1);//*
		
		driver.switchTo().window("");//*
		
		//* hay dùng
		// Size/ Position/ Cookie/ Log/ Navigation/...
	}
	
	@Test
	public void TC_03_Run_On_Chrome_MAC() {


		
		
	}
	


}
