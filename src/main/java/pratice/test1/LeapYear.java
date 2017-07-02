package pratice.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeapYear {

	//编写一个Java程序，用if-else语句判断某年份是否为闰年
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
