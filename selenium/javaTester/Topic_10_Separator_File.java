package javaTester;

import java.io.File;

public class Topic_10_Separator_File {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String projectPath = System.getProperty("user.dir");
		String dellName = "Dell.png";
		
		// Hàm separator sẽ tự động chỉnh dấu \ trên window hoặc là dấu / trên Mac nên chạy ko bị lỗi
		String dellFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dellName;
		System.out.println(dellFilePath);
	}

}
