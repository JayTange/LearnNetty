package pratice.test1;

import java.util.Scanner;

public class Register {

	//实现会员注册，要求用户名长度不小于3，密码长度不小于6，注册时两次输入密码必须相同
	String name;
	String password;
	String newPassword;

	public void nameExe() {
		System.out.println("请输入用户名");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		name = input.next();
		System.err.println("请输入密码");
		password = input.next();
		System.err.println("请再次输入密码");
		newPassword = input.next();
		while((name.length()<3)||password.length()<6||(password.equals(newPassword)==false)){
			if(name.length()<3){
				System.err.println("用户名长度少于6");
			}
			if(password.length()<6){
				System.err.println("密码长度不能小于6");
			}
			if(!password.equals(newPassword)){
				System.err.println("两次密码输入不一样");
			}
			System.out.println("请重新输入： ");
			System.out.println("请输入用户名");
			name = input.next();
			System.err.println("请输入密码");
			password = input.next();
			System.err.println("请再次输入密码");
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
