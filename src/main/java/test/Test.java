package test;

import java.util.Timer;
import java.util.TimerTask;

public class Test {

	public static void  main(String args[]) throws Exception{
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(1000);
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(5000);
	}
	
	static class ThrowTask extends TimerTask{
		public void run(){
			throw new RuntimeException();
		}
	}
}
