package customerState;
import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;
public class Eat extends CustomerState{
	private String name;
	public Eat(Customer cust, Chef chef, Waiter waiter, ServiceMediator service) {
		super(cust);
		name = "eat";
	}

	@Override
	public void detail() {
			try {
				Thread.sleep(6000);
				cust.getService().pay(cust.getServeChef());
				cust.getService().removeCustomer(cust);
				cust.setFinished(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
