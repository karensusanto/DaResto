package waiterState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public abstract class WaiterState {
	
	protected Waiter waiter;

	public WaiterState(Waiter w) {
		waiter = w;
	}
	
	public abstract String getStateName();
	
	public abstract void setStateName(String newName);
	
	public abstract void detail();

}
