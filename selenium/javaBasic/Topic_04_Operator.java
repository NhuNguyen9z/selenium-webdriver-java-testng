package javaBasic;

public class Topic_04_Operator {

	public static void main(String[] args) {
		
		int number = 10;
		//number += 10;
		
		System.out.println(number);
		
		System.out.println(number / 2);
		System.out.println(number % 2);
		
		System.out.println(number ++);
		//System.out.println(number --);
		
		System.out.println(++ number);
		
		int firstVariable = 5;
		int secondVariable = 7;
		int result = firstVariable++ + ++secondVariable - 8;
		
		System.out.println("First Available: " + firstVariable);
		System.out.println("Second Available: " + secondVariable);
		System.out.println("Result: " + result);
		
		

	}

}
