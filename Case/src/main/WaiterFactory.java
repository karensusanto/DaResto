package main;

public class WaiterFactory {

	public Waiter createWaiter(ServiceMediator service) {
		Waiter w = new Waiter(service);
		w.start();
		return w;
	}
}
