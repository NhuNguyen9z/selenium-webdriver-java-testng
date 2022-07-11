package javaTester;

import java.util.Calendar;
import java.util.Random;

public class Topic_06_Random {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("auto" + getRandomNumberByDateTime() + "@hotmail.com");
		sleepInSecond(2);
		System.out.println("auto" + getRandomNumberByDateTime() + "@hotmail.com");
		sleepInSecond(2);
		System.out.println("auto" + getRandomNumberByDateTime() + "@hotmail.com");
		sleepInSecond(2);
		System.out.println("auto" + getRandomNumberByDateTime() + "@hotmail.com");
		sleepInSecond(2);
		System.out.println("auto" + getRandomNumberByDateTime() + "@hotmail.com");
		sleepInSecond(2);
		System.out.println("auto" + getRandomNumberByDateTime() + "@hotmail.com");

	}
	
	public static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.vn";
	}
	
	public static void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
