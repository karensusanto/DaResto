package customerState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class WaitFood extends CustomerState {
	private String name;

	public WaitFood(Customer cust, Chef chef, Waiter waiter, ServiceMediator service) {
		super(cust);
		name = "wait food";
	}

	public String getName() {
		return name;
	}

	@Override
	public void detail() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cust.setTolerance(cust.getTolerance() - 1);
		if (cust.getServeWaiter() != null) {
			cust.setCurrState(new Eat(cust, cust.getServeChef(), cust.getServeWaiter(), cust.getService()));
		}
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void setStateName(String stateName) {
		name = stateName;
	}
}
