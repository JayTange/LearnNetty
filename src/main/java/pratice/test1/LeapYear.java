package pratice.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeapYear {

	//��дһ��Java������if-else����ж�ĳ����Ƿ�Ϊ����
	public static void main(String args[]) throws IOException{
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
		String br = reader.readLine();
		int year = Integer.parseInt(br);
		if((year%4==0&&year%100!=0)||(year%400==0)){
			System.err.println("YES");
		}else {
			System.err.println("no");
		}
		
	}
}
