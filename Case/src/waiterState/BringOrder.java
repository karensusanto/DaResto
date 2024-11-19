package waiterState;

import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;

public class BringOrder extends WaiterState {
	private String name;

	public BringOrder(Waiter waiter, Customer cust, Chef chef, ServiceMediator service) {
		super(waiter);
		name = "bring order";
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void detail() {
//		if(waiter.getServeChef()!=null && waiter.getServeCustomer()!=null){
		if (waiter.getServeChef().getCurrState().getStateName().equals("idle")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			waiter.getServeChef().setServeCustomer(waiter.getServeCustomer());
			waiter.getServeChef().setServeWaiter(waiter);
			waiter.setServeChef(null);
			waiter.setServeCustomer(null);
			waiter.setCurrState(
					new Idle(waiter, waiter.getServeCustomer(), waiter.getServeChef(), waiter.getService()));
		} else if (waiter.getServeChef().getCurrState().getStateName().equals("done")) {
			waiter.getServeChef().setServeWaiter(waiter);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			waiter.setCurrState(
					new ServeFood(waiter, waiter.getServeCustomer(), waiter.getServeChef(), waiter.getService()));
		}
//		}
	}

	@Override
	public void setStateName(String newName) {
		name = newName;
	}
}
