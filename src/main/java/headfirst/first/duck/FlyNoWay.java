package headfirst.first.duck;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I cant fly");
	}

}
