package bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
	
	private static void send(){

		int port = 8080;
		Socket socket = null;
		BufferedReader in =null;
		PrintWriter out = null;
		try {
			socket = new Socket("192.168.1.107",port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("QUERY TIME ORDER");
			System.out.println("send order 2 server succeed");
			String resp = in.readLine();
			System.out.println("服务器的回复是："+resp);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(out !=null){
				out.close();
				out = null;
			}
			if(in !=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			in = null;
			if(socket !=null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			socket = null;
		}
	
	}
	
	public static void main(String args[]){
		for(int i = 0;i<5;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					send();
				}
			}).start();;
		}
	}
}
