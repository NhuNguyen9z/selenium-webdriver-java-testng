package javaBasic;

import org.testng.annotations.Test;

public class Topic_02_Exercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	}


	@Test
		public void TC_01 () {
			int a = 6;
			int b = 2;
			
			System.out.println("Tổng = " +  (a + b));
			System.out.println("Hiệu = " +  (a - b));
			System.out.println("Tích = " +  a * b);
			System.out.println("Thương = " +  a / b);
			
			
		}
	
	@Test
	public void TC_02 () {
		float width = 7.5f;
		float height = 3.8f;
		
		System.out.println("dien tich hinh chu nhat: " + width * height);
		
		
	}
	
	@Test
	public void TC_03 () {
		String name = "Hello Automation Testing";
		
		System.out.println("Name = " + name);
	}
}

