package chefState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class Cook extends ChefState {
	private String name;

	public Cook(Chef chef, Customer cust, Customer waitingCust, Waiter waiter, ServiceMediator service) {
		super(chef);
		name = "cook(" + cust.getCustName() + ")";
	}

	@Override
	public void detail() {
		chef.getServeCustomer().setServeWaiter(null);
		chef.setServeWaiter(null);
		try {
			Thread.sleep(6000 - (chef.getSpeed() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		chef.setCurrState(new Done(chef, chef.getServeCustomer(), chef.getWaitingCustomer(), chef.getServeWaiter(),
				chef.getService()));
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void setStateName(String newName) {
		name = newName;
	}
}
