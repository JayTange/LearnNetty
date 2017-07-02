package pratice.test1;

public class ForTest {

	//编写一个Java程序在屏幕上输出1！+2！+3！+……+10！的和。（循环）
	public static void main(String args[]){
		int sum = 0,mul;
		for(int i=1;i<=10;i++){
			mul=1;
			for(int j=1;j<=i;j++){
				mul = mul*j;
			}
			sum+=mul;
		}
		System.out.println(sum);
	}
}
