package headfirst.first.duck;

public class MallardDuck extends Duck{

	public MallardDuck() {
		quackBehavior = new Quack();
		flyBehavior = new FlyNoWay();
	}
	@Override
	public void display() {
		System.out.println("fly");
	}

}
