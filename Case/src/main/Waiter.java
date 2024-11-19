package main;

import java.util.Random;

import waiterState.Idle;
import waiterState.WaiterState;

public class Waiter extends Thread{
	private String initial;
	private int speed;
	private WaiterState currState;
	private ServiceMediator service;
	private Customer serveCustomer;
	private Chef serveChef;
	Random rand = new Random();
	
	public Waiter(ServiceMediator service) {
		initial = String.format("%c%c", (char)rand.nextInt(26)+'A',(char)rand.nextInt(26)+'A');
		speed = 1;
		this.service = service;
		serveCustomer = null;
		serveChef = null;
		service.register(this);
		currState = new Idle(this, null, null, service);
	}
	
	@Override
	public void run() {
		while(true) {
			if(Restaurant.getInstance().isPaused()==false) {
				currState.detail();
			}
		}
	}
	
	public ServiceMediator getService() {
		return service;
	}

	public void setService(ServiceMediator service) {
		this.service = service;
	}

	public Customer getServeCustomer() {
		return serveCustomer;
	}

	public void setServeCustomer(Customer serveCustomer) {
		this.serveCustomer = serveCustomer;
	}

	public Chef getServeChef() {
		return serveChef;
	}

	public void setServeChef(Chef serveChef) {
		this.serveChef = serveChef;
	}

	public void change(WaiterState state) {
		this.currState = state;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public WaiterState getCurrState() {
		return currState;
	}

	public void setCurrState(WaiterState currState) {
		this.currState = currState;
	}
	
}
