package main;

public class ChefFactory {

	public Chef createChef(ServiceMediator service) {
		Chef c = new Chef(service);
		c.start();
		return c;
	}
}
