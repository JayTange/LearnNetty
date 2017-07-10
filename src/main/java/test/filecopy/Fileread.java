package test.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Fileread {

	public String readFile(String path) throws IOException{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		try {
			fis.read(data);
		} finally {
			fis.close();
		}
		return new String(data);
	}
	
	public static void main(String args[]) throws IOException{
		String path = "./src/main/test/svcconfig.ini";
		Fileread rFileread = new Fileread();
		String result = rFileread.readFile(path);
		String[] tokens = result.split("");
		for(int i = 0;i<tokens.length;i++){
			System.out.print(tokens[i]);
		}
	}
}
