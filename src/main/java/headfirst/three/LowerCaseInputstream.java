package headfirst.three;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerCaseInputstream extends FilterInputStream{

	protected LowerCaseInputstream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException{
		int c = super.read();
		return (c == -1?c :Character.toLowerCase(c));
	}
	@Override
	public int read(byte[] b,int offset, int len)throws IOException{
		int result = super.read(b, offset, len);
		for(int i=offset;i<offset+result;i++){
			b[i] = (byte) Character.toLowerCase(b[i]);
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException{
		int c;
		try {
			File f = new File("./src/main/test/a.txt");
			InputStream in = new LowerCaseInputstream(new BufferedInputStream(new FileInputStream(f)));
			while((c=in.read())>0){
				System.out.println((char)c);
			}
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
