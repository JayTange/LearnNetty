package test.filecopy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class WriteFile {

	public void writeToFile(String filePath, byte[] data) throws Exception{
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
		try {
			fos.write(data);
		} finally {
			fos.close();
		}
	}
	
	public static void main(String[] args) throws Exception {

		String filePath = "./src/main/test/c.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.err.println("«Î ‰»Îƒ⁄»›");
		String preData = br.readLine();
		WriteFile writeFile = new WriteFile();
		byte[] data = preData.getBytes("GBK");
		writeFile.writeToFile(filePath, data);
	}

}
