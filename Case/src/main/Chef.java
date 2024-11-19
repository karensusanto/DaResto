package main;

import java.util.Random;

import chefState.ChefState;
import chefState.IdleChef;

public class Chef extends Thread {
	private String initial;
	private ChefState currState;
	private int speed, skill;
	private ServiceMediator service;
	private Customer serveCustomer, waitingCustomer;
	private Waiter serveWaiter;
	Random rand = new Random();

	public Chef(ServiceMediator service) {
		initial = String.format("%c%c", (char) rand.nextInt(26) + 'A', (char) rand.nextInt(26) + 'A');
		speed = 1;
		skill = 1;
		this.service = service;
		serveCustomer = null;
		waitingCustomer = null;
		serveWaiter = null;
		service.register(this);
		currState = new IdleChef(this, null, null, null, service);
	}

	@Override
	public void run() {
		while (true) {
			if (Restaurant.getInstance().isPaused() == false) {
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

	public Customer getWaitingCustomer() {
		return waitingCustomer;
	}

	public void setWaitingCustomer(Customer waitingCustomer) {
		this.waitingCustomer = waitingCustomer;
	}

	public Waiter getServeWaiter() {
		return serveWaiter;
	}

	public void setServeWaiter(Waiter serveWaiter) {
		this.serveWaiter = serveWaiter;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public ChefState getCurrState() {
		return currState;
	}

	public void setCurrState(ChefState currState) {
		this.currState = currState;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

}
