package chefState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class Done extends ChefState {
	private String name;

	public Done(Chef chef, Customer cust, Customer waitingCust, Waiter waiter, ServiceMediator service) {
		super(chef);
		name = "done";
	}

	@Override
	public void detail() {
		if (chef.getServeWaiter() == null) {
			chef.getService().lookForWaiter(chef.getServeCustomer(), chef.getServeWaiter(), chef, "chef");
		} else if (chef.getServeWaiter() != null && chef.getWaitingCustomer() != null) {
			chef.setServeCustomer(chef.getWaitingCustomer());
			chef.setWaitingCustomer(null);
			chef.setCurrState(new Cook(chef, chef.getServeCustomer(), chef.getWaitingCustomer(), chef.getServeWaiter(),
					chef.getService()));
		} else {
			chef.setCurrState(new IdleChef(chef, chef.getServeCustomer(), chef.getWaitingCustomer(),
					chef.getServeWaiter(), chef.getService()));
		}
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
