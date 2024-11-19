package waiterState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class Idle extends WaiterState{
	private String name;
	
	public Idle(Waiter waiter, Customer cust, Chef chef, ServiceMediator service) {
		super(waiter);
		name = "idle";
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void detail() {
		if(waiter.getServeCustomer()!=null && waiter.getServeChef()==null) {//take order
			waiter.setCurrState(new TakeOrder(waiter, waiter.getServeCustomer(), waiter.getServeChef(), waiter.getService()));
		}else if(waiter.getServeCustomer()!=null && waiter.getServeChef()!=null) {
			waiter.setCurrState(new BringOrder(waiter, waiter.getServeCustomer(), waiter.getServeChef(), waiter.getService()));
		}else {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setStateName(String newName) {
		name = newName;
	}

}
