package waiterState;

import customerState.WaitFood;
import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class TakeOrder extends WaiterState{
	private String name;
	public TakeOrder(Waiter waiter, Customer cust, Chef chef, ServiceMediator service) {
		super(waiter);
		name = "take order("+cust.getCustName()+")";
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void detail() {
		try {
			Thread.sleep(6000-(waiter.getSpeed()*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waiter.getServeCustomer().setCurrState(new WaitFood(waiter.getServeCustomer(), waiter.getServeChef(), waiter, waiter.getService()));//wait food
		waiter.setCurrState(new WaitCook(waiter, waiter.getServeCustomer(), waiter.getServeChef(), waiter.getService()));//wait cook
	}
	
	@Override
	public void setStateName(String newName) {
		name = newName;
	}
	
}
