package javaBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_02_Data_Type {
	// Gobal variable
	static int number;
	String address =  "Ho Chi Minh";
	

	// kiểu Reference Type: Tham chiếu
	
	// String
	String address2 = "Sai Gon";
	
	// Array
	String[] studentAddress = {address2, "Ha noi", "Da nang"};
	Integer[] studentNumber1 = {15, 20, 50};
	
	// Class
	Topic_02_Data_Type topic;
	
	// Interface
	WebDriver driver;
	
	// kiểu Object có thể convert qua String, Class, Integer, Float
	
	// Object
	Object aObject;
	
	// Collection
	// List/ Set/ Queue/ Map
	List<WebElement> homePageLinks = driver.findElements(By.tagName("a"));
	Set<String> allWindows = driver.getWindowHandles();
	List<String> productName = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Local variable
		// Kiểu Static là gọi từ tên lớp đến trực tiếp tên hàm được
		
		int studentNumber = 10;
		
		Topic_02_Data_Type topic = new Topic_02_Data_Type();
		
		System.out.println(number);
		System.out.println(studentNumber);
		System.out.println(topic.address);
	}

}
