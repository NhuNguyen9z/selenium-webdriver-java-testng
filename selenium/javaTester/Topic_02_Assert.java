package javaTester;

import org.testng.Assert;

//import org.testng.Assert;

public class Topic_02_Assert {

	public static void main(String[] arg) {
		String errorMessage = "This is a required field.";
		boolean status = true;
				
		
		// 2 data co bang nhau ko
		Assert.assertEquals("This is a required field.", errorMessage);
				
		// kiem tra 1 dieu kien mong muon tra ve la dung
		Assert.assertTrue(status);
		
		// kiem tra 1 dieu kien mong muon tra ve la sai
		status = false;
		Assert.assertFalse(status);
		
				
				
	}
}
