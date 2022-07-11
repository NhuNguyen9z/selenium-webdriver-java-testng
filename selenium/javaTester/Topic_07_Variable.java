package javaTester;

public class Topic_07_Variable {
	String address;
	static String cityName = "Nguyen Van Troi";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Topic_07_Variable tp7_01 = new Topic_07_Variable();
		tp7_01.address = "Sai Gon";
		System.out.println(tp7_01.address);
		System.out.println(Topic_07_Variable.cityName);
		
		Topic_07_Variable tp7_02 = new Topic_07_Variable();
		tp7_02.address = "Ha Noi";
		System.out.println(tp7_02.address);
		System.out.println(Topic_07_Variable.cityName);

	}

}
