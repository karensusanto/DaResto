package main;

import java.util.Random;

public class CustomerFactory implements Observer{
	private static Random rand = new Random();
	private ServiceMediator service;
	
	public CustomerFactory(ServiceMediator service) {
		this.service = service;
	}
	
	public void createCustomer() {
		String name = String.format("%c%c", (char)rand.nextInt(26)+'A',(char)rand.nextInt(26)+'A');
		Customer c = new Customer(name, service);
		service.register(c);
		c.start();
	}
	
	@Override
	public void receiveNotif(String notif) {
		if(notif.equals("make customer")&&rand.nextInt(4)==0) {
			createCustomer();
		}
	}

}
