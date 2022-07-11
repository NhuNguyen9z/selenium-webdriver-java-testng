package javaBasic;

import java.util.Scanner;



public class Available {
	
	static int studentNumber;
	static boolean statusValue;
	static final String bowserName = "Chrome";

	String studentName = "Automation FC";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(studentNumber);
		System.out.println(statusValue);
		
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println(name);

	}

	
	
	// Getter
	public String getStudentName() {
		return this.studentName;
	}
	
	//Setter
	public void setStudentName(String stdName) {
		
		this.studentName = stdName;
	}

	
}

