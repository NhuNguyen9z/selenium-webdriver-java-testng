package webdriver;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class Topic_08_Custom_Dropdown_Part_I {
	WebDriver driver;
	WebDriverWait explicitWait;  // Thư viện Wait  -- muốn dùng thì phải khởi tạo lên
	
	// Inject 1 javascript code
	JavascriptExecutor jsExecutor; // Thư viện Javascript -- muốn dùng thì phải khởi tạo lên
	String projectPath = System.getProperty("user.dir");
	
	String[] firstMonth = {"February", "May", "October"};
	String[] secondMonth = {"February", "May", "August", "October"};

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		
		// Lưu ý: Interface và Abstract Class là ko cho khởi tạo
		// Ép kiểu ngầm định: Từ kiểu dữ liệu cho range lớn hơn về range nhỏ hơn
		// double -> float -> long -> int -> short -> byte
		
		// Ép kiểu ngầm định từ nhỏ -> lớn
		//int price = 156000;
		//float size = price;
		
		// lớn -> nhỏ
		//short sPrice = (short) price;
				
		
		// Ép kiểu tường minh
		jsExecutor =  (JavascriptExecutor) driver; // driver cũng là 1 interface muốn ép driver qua kiểu jsExecutor thì chỉ cần gán JavascriptExecutor vào
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class, 'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","5");
		sleepInSecond(3);
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		
		selectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","15");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		
		selectItemInCustomDropdown("//span[@id='number-button']//span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","3");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='3']")).isDisplayed());
	}
	
	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Elliot Fu");
		sleepInSecond(3);
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Elliot Fu']")).isDisplayed());
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Justen Kitsune");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Justen Kitsune']")).isDisplayed());
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Christian");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
	}
	
	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(3);
		// Kiểm tra locator chứa cái text đã chọn, chọn thằng nào thì verify thằng đó
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(3);
		// Kiểm tra locator chứa cái text đã chọn, chọn thằng nào thì verify thằng đó
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(3);
		//Kiểm tra locator chứa cái text đã chọn, chọn thằng nào thì verify thằng đó
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	}
	
	
	public void TC_04_Angular() {
		driver.get("https://valor-software.com/ng2-select/");
		selectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Berlin");
		sleepInSecond(3);
		
		//selectItemInCustomDropdown("//span[@aria-owns='games_options']", "//li[@class='e-list-item']", "Tennis");
		//sleepInSecond(3);
		
		// get text ra kiểm tra nó bằng cái text mình mong muốn hay ko
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]")).getText(), "Berlin");
	}
	
	
	public void TC_05_Editable_01() {
		
		driver.get("https://valor-software.com/ng2-select/");
		
		enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Brussels");
		sleepInSecond(3);
		//get text ra  để kiểm tra nó bằng với cái mình mong muốn hay ko
		//get xpath cách 1
		//Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]")), "Brussels");
		
		//get text có để kiểm tra xem có trả về kết quả mong muốn hay ko
		// get xpath cách 2
		Assert.assertEquals(driver.findElement(By.xpath("//tab[@heading='Single']//span[contains(@class,'ui-select-allow-clear')]")).getText(), "Brussels");
		sleepInSecond(3);
	}
	
	
	public void TC_06_Ediable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndTabToCustomDropdown("//input[@class='search']", "Belgium");
		sleepInSecond(1);
		// get text để kiểm tra xem kết quả trả về có bằng với cái mình muốn hay ko
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='combobox']//div[@class='divider text']")).getText(), "Belgium");
		sleepInSecond(1);
		
		enterAndTabToCustomDropdown("//input[@class='search']", "Algeria");
		sleepInSecond(1);
		//get text để kiểm tra xem có trả về bằng vs cái mình muốn hay ko
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='combobox']//div[@class='divider text']")).getText(), "Algeria");
		sleepInSecond(1);
	}
	
	//@Test
	public void TC_07_Multiple() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", firstMonth);
		sleepInSecond(3);
		
		// Verify
		Assert.assertTrue(areItemSelectecd(firstMonth));
		
		// cho tải lại trang
		driver.navigate().refresh();;
		
		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//span", secondMonth);
		sleepInSecond(3);
		// Verify để kiểm tra kết tra kết quả trả về có đúng vs cái mình muốn hay ko
		Assert.assertTrue(areItemSelectecd(secondMonth));
	}
	
	
	// Nên lấy đúng thẻ chứa text vì getText() ra sẽ chính xác , div chứa text khi getText sẽ lấy ra duoc text bên trong
 	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem) {
		
		 // Click vào 1 element để cho nó xổ hết tất cả các item trong dropdown ra -> Parent element
		 driver.findElement(By.xpath(parentXpath)).click();
		 sleepInSecond(1);
		 
		// - Chờ cho tất cả các item được load ra thành công -> Child element 
		 // presenceOfAllElementsLocatedBy  ko quan tâm thằng nào ẩn hay hiện hết nó chỉ quan tâm nó dc load ra hết trong html DOM
		 // visibilityOfAllElementsLocatedBy chỉ hiện những element nào được thấy, còn các element scroll thì ko hiện
		 // - Lấy hết tất cả các item này lưu vào list element 
		 List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath))); // 19 items
		System.out.println(allItems.size());
		 // Tìm các item cần chọn		 
		 // Duyệt qua từng item
		 for (WebElement item : allItems) { // Định nghĩa ra cái biến item sẽ là 1 biến tạm mỗi lần chạy nó sẽ duyệt qua từng cái item trong 19 items này
			 // Kiểm tra luôn nó có bằng vs cái item text mà mình mong muốn hay ko
			  
				 // cái item này có phải là cái WebElement ko, WebElement có hàm getText()
			/*
			if(item.getText().trim().equals(expectedItem)) { // hàm trim dùng để lạo bỏ ký tự khoảng trắng, ký tự tab, ký tự xuống dòng
				 // Kiểm tra tiếp nếu như nó hiển thị thì nó click vào, nếu nó ko display thì nó scroll đến và click vào
				 // Item cần chọn nó hiển thị -> Click vào item đó luôn
				/* Đoạn code này ko tối ưu vì nó dài
				 if(item.isDisplayed()) {
					 item.click();
				 } else { // Item cần chọn ko hiển thị (ẩn bên dươi)
					 // Scroll đến item đó -> click vào item
					 jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					 sleepInSecond(1);
					 item.click();
				 }
				 */
				 // Đoạn code này tối ưu hơn đoạn trên vì dù hiển thị hay ko cũng đều click
			 //// hàm trim dùng để lạo bỏ ký tự khoảng trắng, ký tự tab, ký tự xuống dòng
				 if(item.getText().trim().equals(expectedItem)) { // trường hợp element có hiện thị hay ko đều cho scroll
					
					 jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					 sleepInSecond(1);				 
				     item.click(); // Dù hiện thị hay ko hiển thị thì cũng đều click
				     break; // Thỏa mãn điều kiện rồi thì Thoát khỏi vòng lặp bằng câu lệnh break
				 // Tại sao cần thoát khỏi vòng lặp vì: ở đây nếu mình có nhiều element, nhiều item cả trăm cả ngàn item cái mình cần là click vào đúng cái mình muốn
				 // để cho nó ko kiểm tra các thằng còn lại, vì nếu ko có break sẽ duyệt tiếp những element còn lại gây tốn time mà ko có ý nghĩa
			 }
			// }	 
		 }
		 
		 
		 // Get text của item đó ra và kiểm tra xem nó có bằng vs item text mà mình mong muốn hay ko
		 // + Item cần chọn nó hiển thị -> Click vào item đó luôn
		 // + Item cần chọn ko hiển thị (ẩn bên dưới) -> Scroll đến item đó -> Click vào item
		
	}
	
	public void enterAndSelectItemInCustomDropdown(String parentXpath, String textboxXpath, String childXpath, String expectItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectItem);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		for(WebElement item : allItems) {
			if(item.getText().trim().equals(expectItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	public void enterAndTabToCustomDropdown(String textboxXpath, String expectedItem) {
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedItem);
		sleepInSecond(2);
		driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);
		sleepInSecond(1);
	}
	
	public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1: click vào cái dropdown cho nó xổ hết all các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();
		
		// 2: chờ cho all các giá trị trong dropdown được load ra thành công
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// Duyệt qua hết all các phần tử cho đến khi thỏa mãn điều kiện
		for(WebElement childElement : allItems) {
			// "January", "April", "July"
			for(String item : expectedValueItem) {
				if(childElement.getText().equals(item)) { //kiểm tra xem có = vs item đã truyền vào hay ko, nếu thõa mãn điều kiện thì vào if, ngược lại ko vào if
					// 3: Scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy mà ko cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", childElement);
					sleepInSecond(1);
					
					// 4:  click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected);
					if(expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
				
			}
			
		}
	}
	
	public boolean areItemSelectecd(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		// 3
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text đã chọn: " + allItemSelectedText);
		// February, May, October
		
		// months = "February", "May", "October"
		if(numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			System.out.println(" <= 3 && > 0  la: " + numberItemSelected);
			for(String item : months) { // duyệt từng phần tử trong mảng
				// Fabruary  lần thứ 1  - đúng trả về true
				// May lần thứ 2   đúng trả về true
				// October lần thứ 3 - đúng trả về true
				if(!allItemSelectedText.contains(item)) {  // nếu trường hợp ko chứa --- nếu thỏa mãn điều kiện thì mới chạy vào hàm này
					status = false;
					return status;
				}
				
				
			}
			return status;
		} else if(numberItemSelected == 12) {
			System.out.println(" == 12" + numberItemSelected);
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if(numberItemSelected > 3 && numberItemSelected < 12) {
			System.out.println(">3 && <12 la: " + numberItemSelected);
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
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
