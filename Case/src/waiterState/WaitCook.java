package waiterState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class WaitCook extends WaiterState{
	private String name;
	public WaitCook(Waiter waiter, Customer cust, Chef chef, ServiceMediator service) {
		super(waiter);
		name = "wait cook";
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void detail() {
		if(waiter.getServeChef()==null) {
			waiter.getService().lookForCook(waiter, waiter.getServeCustomer());
		}else if(waiter.getServeChef()!=null){
			waiter.setCurrState(new BringOrder(waiter, waiter.getServeCustomer(), waiter.getServeChef(), waiter.getService()));//wait cook->bring order
		}
	}
	@Override
	public void setStateName(String newName) {
		name = newName;
	}

}
