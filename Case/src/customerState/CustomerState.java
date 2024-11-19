package customerState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public abstract class CustomerState {
	
//	public abstract void changeState(Waiter w, Chef cheff, ServiceMediator servicee);
	
	protected Customer cust;
	public CustomerState(Customer c) {
		cust = c;
	}
	
	public abstract String getStateName();
	
	public abstract void setStateName(String stateName);
	
	public abstract void detail();
}
