package pratice.test1;

import java.util.Scanner;

public class Register {

	//ʵ�ֻ�Աע�ᣬҪ���û������Ȳ�С��3�����볤�Ȳ�С��6��ע��ʱ�����������������ͬ
	String name;
	String password;
	String newPassword;

	public void nameExe() {
		System.out.println("�������û���");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		name = input.next();
		System.err.println("����������");
		password = input.next();
		System.err.println("���ٴ���������");
		newPassword = input.next();
		while((name.length()<3)||password.length()<6||(password.equals(newPassword)==false)){
			if(name.length()<3){
				System.err.println("�û�����������6");
			}
			if(password.length()<6){
				System.err.println("���볤�Ȳ���С��6");
			}
			if(!password.equals(newPassword)){
				System.err.println("�����������벻һ��");
			}
			System.out.println("���������룺 ");
			System.out.println("�������û���");
			name = input.next();
			System.err.println("����������");
			password = input.next();
			System.err.println("���ٴ���������");
			newPassword = input.next();
		}
	}

	public static void main(String args[]) {
		Register register = new Register();
		register.nameExe();
		System.out.println("name:" + register.name);
		System.out.println("password:" + register.password);
	}
}
