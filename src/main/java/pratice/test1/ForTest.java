package pratice.test1;

public class ForTest {

	//��дһ��Java��������Ļ�����1��+2��+3��+����+10���ĺ͡���ѭ����
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
