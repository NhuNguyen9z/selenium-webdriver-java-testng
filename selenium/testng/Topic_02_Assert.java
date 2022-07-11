package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_02_Assert {
	
	Object studentAddress = null;
	
  @Test()
  public void TC_01_Assert() {
	  String studentName = "Le Van Nam";
	  
	  // isDisplayed/ isEnabled/ isSelected/ isMultiple
	  
	  // Verify 1 điều kiện trả về là đúng
	  Assert.assertTrue(studentName.contains("Nam"));
	  
	  // Verify 1 điều kiện trả về là sai
	  Assert.assertFalse(studentName.contains("Trung"));
	  
	  // Verify 2 cái điều kiện bằng nhau
	  Assert.assertEquals(studentName, "Le Van Nam");
	  
	  // Kiểm tra 2 điều kiện ko bằng nhau
	  Assert.assertNotEquals(studentName, "Le Van Trung");
	  
	  Assert.assertNull(studentAddress);
	  
	  studentAddress = "Nguyen Van Troi";
	 
	  Assert.assertNotNull(studentAddress);
	  
	  
  }
  
  @Test()
  public void TC_02() {
  }
  @Test()
  public void TC_03() {
  }
  
  

}
