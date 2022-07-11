package javaTester;

public class Topic_08_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String actualText = "                            Second Option          ";
		System.out.println(actualText);
		System.out.println(actualText.trim()); // Hàm trim sẽ cắt khoảng trắng, tab, xuống dòng

		
		String href = "http://the-internet.herokuapp.com/basic.authen";
		
		String[] hrefValue = href.split("//");
		
		String user = "admin";
		String pass = "admin";
		
		 
		System.out.println(hrefValue[0]);
		System.out.println(hrefValue[1]);
		
		 href = hrefValue[0] + "//" + user + ":" +  pass + "@" + hrefValue[1];
		
		System.out.println(href);
	}

}
