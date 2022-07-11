package javaTester;

public class Topic_04_And_Or {

	public static void main(String[] args) {
		//male
		boolean firstStatus = true;
		//female
		boolean secondStatus = false;
		
		System.out.println("-----------------AND----------------");
		
		//AND: true & false = false	
		
		System.out.println(firstStatus & secondStatus);
		
		 // AND: false & true = false
		firstStatus = false;
		secondStatus= true;
		System.out.println(firstStatus & secondStatus);
		
		 // AND: true & true = true
		firstStatus = true;
		secondStatus= true;
		System.out.println(firstStatus & secondStatus);
		
		 // AND: false & false = false
		firstStatus = false;		
		secondStatus= false;
		System.out.println(firstStatus & secondStatus);
		
		System.out.println("==============OR===================");
		 // AND: false & true = true
		firstStatus = false;
		secondStatus= true;
		System.out.println(firstStatus | secondStatus);
		
		 // AND: true & true = true
		firstStatus = true;
		secondStatus= true;
		System.out.println(firstStatus | secondStatus);
		
		 // AND: false & false = false
		firstStatus = false;		
		secondStatus= false;
		System.out.println(firstStatus | secondStatus);
		
		 // AND: true & false = true
		firstStatus = true;		
		secondStatus= false;
		System.out.println(firstStatus | secondStatus);
	}
}
