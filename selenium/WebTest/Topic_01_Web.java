package WebTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Web {
	WebDriver driver;
	String projectPath =  System.getProperty("user.dir");
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		 System.out.println("Run Before Class");
		 System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			Assert.assertTrue(false);
			
	}
	
  @Test(groups = {"web", "regession"})
  public void TC_01_View_All_Student() {
	  	  
  }
  
  @Test(groups = {"web", "regession"})
  public void TC_02_Edit_Student() {
	  
  }
  
  @Test(groups = {"web", "regession"})
  public void TC_03_Delete_Student() {
	  
  }
  
  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  System.out.println("Run After Class");
	  driver.quit();
  }
  

}
