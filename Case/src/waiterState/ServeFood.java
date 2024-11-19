package waiterState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class ServeFood extends WaiterState{
	private String name;
	public ServeFood(Waiter waiter, Customer cust, Chef chef, ServiceMediator service) {
		super(waiter);
		name = "serve food";
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void detail() {
		try {
			Thread.sleep(1000);
			waiter.getServeCustomer().setServeWaiter(waiter);
			waiter.setServeCustomer(null);
			waiter.setServeChef(null);
			waiter.setCurrState(new Idle(waiter, waiter.getServeCustomer(), waiter.getServeChef(), waiter.getService()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setStateName(String newName) {
		name = newName;
	}

}
