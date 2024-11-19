package customerState;
import main.Chef;
import main.Customer;
import main.ServiceMediator;
import main.Waiter;
public class Order extends CustomerState{
	private String name;
	public Order(Customer cust, Chef chef, Waiter waiter, ServiceMediator service) {
		super(cust);
		name = "order";
	}
	
	private long startTime = System.currentTimeMillis();
	private long currTime;
	@Override
	public void detail() {
			if (cust.getServeWaiter() == null) {//lg cari waiter
				currTime = System.currentTimeMillis();
				if (currTime - startTime == 2000) {
					cust.setTolerance(cust.getTolerance() - 1);
					startTime = currTime;
				}
				cust.getService().lookForWaiter(cust, cust.getServeWaiter(), cust.getServeChef(),"customer");
			} else {//lg pesen
				try {
					Thread.sleep(6000-(cust.getServeWaiter().getSpeed()*1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}

	@Override
	public String getStateName() {
		return name;
	}

	@Override
	public void setStateName(String stateName) {
		this.name = stateName;
	}
}
