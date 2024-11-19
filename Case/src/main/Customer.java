package main;

import java.util.Random;

import customerState.CustomerState;
import customerState.Order;

public class Customer extends Thread{
	private String name;
	private int tolerance;
	private CustomerState currState;
	private ServiceMediator service;
	boolean isFinished;
	private Waiter serveWaiter;
	private Chef serveChef;

	public Customer(String name, ServiceMediator service) {
		this.name = name;
		tolerance = 12;
		isFinished = false;
		this.service = service;
		serveWaiter = null;
		serveChef = null;
		currState = new Order(this, null, null, service);
	}
	
	@Override
	public void run() {
		while(isFinished==false) {
			if(Restaurant.getInstance().isPaused()==false) {
				currState.detail();
			}
		}
	}

	public ServiceMediator getService() {
		return service;
	}
	
	public Waiter getServeWaiter() {
		return serveWaiter;
	}

	public void setServeWaiter(Waiter serveWaiter) {
		this.serveWaiter = serveWaiter;
	}

	public Chef getServeChef() {
		return serveChef;
	}

	public void setServeChef(Chef serveChef) {
		this.serveChef = serveChef;
	}

	public String getCustName() {
		return name;
	}

	public void setCustName(String name) {
		this.name = name;
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	public CustomerState getCurrState() {
		return currState;
	}

	public void setCurrState(CustomerState currState) {
		this.currState = currState;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	
}
