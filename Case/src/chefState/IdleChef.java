package chefState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class IdleChef extends ChefState {
	private String name;

	public IdleChef(Chef chef, Customer cust, Customer waitingcust, Waiter waiter, ServiceMediator service) {
		super(chef);
		name = "idle";
	}

	@Override
	public void detail() {
		if (chef.getServeCustomer() != null && chef.getServeWaiter() != null) {
			chef.setCurrState(new Cook(chef, chef.getServeCustomer(), chef.getWaitingCustomer(), chef.getServeWaiter(),
					chef.getService()));
		} else {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getStateName() {
		return name;
	}

//	@Override
//	public void changeState(Waiter waiter, Customer cust, ServiceMediator service) {
//		chef.setCurrState(new Cook(chef, cust, waiter, service));
//	}
	@Override
	public void setStateName(String newName) {
		name = newName;
	}

}
