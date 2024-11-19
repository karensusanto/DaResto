package main;

import java.util.Vector;

import chefState.Done;
import chefState.IdleChef;

public class ServiceMediator implements Mediator {
	private volatile Vector<Customer> custList;
	private volatile Vector<Chef> chefList;
	private volatile Vector<Waiter> waiterList;
	private Object lock = new Object();
	private Restaurant rest;

	public ServiceMediator(Restaurant rest) {
		custList = new Vector<>();
		chefList = new Vector<>();
		waiterList = new Vector<>();
		this.rest = rest;
	}

	public int custNum() {
		return custList.size();
	}

	public void pay(Chef chef) {
		int payment = chef.getSkill() * 30;
		rest.setMoney(rest.getMoney() + payment);
		rest.setScore(payment + rest.getScore());
	}

	public void lookForWaiter(Customer cust, Waiter w, Chef chef, String calledBy) {// BENERIN
		synchronized (lock) {
			for (Waiter waiter : waiterList) {
				if (waiter.getCurrState().getStateName().equals("idle") && calledBy.equals("customer")) {
					cust.setServeWaiter(waiter);
					cust.getCurrState().setStateName("order(" + cust.getServeWaiter().getInitial() + ")");
					waiter.setServeCustomer(cust);
					break;
				} else if (waiter.getCurrState().getStateName().equals("idle") && calledBy.equals("chef")) {
					waiter.setServeChef(chef);
					waiter.setServeCustomer(chef.getServeCustomer());
					cust.setServeWaiter(waiter);
					break;
				}
			}
		}
	}

	public void lookForCook(Waiter waiter, Customer cust) {
		synchronized (lock) {
			for (Chef chef : chefList) {
				if (chef.getCurrState() instanceof IdleChef) {
					waiter.getServeCustomer().setServeChef(chef);
					waiter.setServeChef(chef);
					cust.getCurrState().setStateName("wait food(" + chef.getInitial() + ")");
					break;
				} else if (chef.getCurrState() instanceof Done) {// anter pesenan dan serve food
					waiter.getServeCustomer().setServeChef(chef);
					waiter.setServeChef(chef);
					waiter.getServeCustomer().getCurrState().setStateName("wait food(" + chef.getInitial() + ")");
					chef.setWaitingCustomer(waiter.getServeCustomer());
				}
			}
		}
	}

	public int maxList() {
		if (custList.size() >= waiterList.size() && custList.size() >= chefList.size())
			return custList.size();
		else if (waiterList.size() >= custList.size() && waiterList.size() >= chefList.size())
			return waiterList.size();
		else
			return chefList.size();
	}

	public void printGameStatus() {
		synchronized (lock) {
			for (int i = 0; i < maxList(); i++) {
				String custName = i < custList.size()
						? custList.get(i).getCustName() + "(" + custList.get(i).getTolerance() + "),"
						: "";
				String custState = i < custList.size() ? custList.get(i).getCurrState().getStateName() : "";
				String wName = i < waiterList.size() ? waiterList.get(i).getInitial() + "," : "";
				String wState = i < waiterList.size() ? waiterList.get(i).getCurrState().getStateName() : "";
				String cName = i < chefList.size() ? chefList.get(i).getInitial() + "," : "";
				String cState = i < chefList.size() ? chefList.get(i).getCurrState().getStateName() : "";
				System.out.printf("%-4s %15s| %-2s %15s| %-2s %15s\n", custName, custState, wName, wState, cName,
						cState);
			}
			System.out.println("\nPress ENTER to go to pause menu");
		}
	}

	public void clearArrayLists() {
		if (chefList.size() > 0) {
			chefList.clear();
		}
		if (waiterList.size() > 0) {
			waiterList.clear();
		}
		if (custList.size() > 0) {
			custList.clear();
		}
	}

	@Override
	public void register(Customer customer) {
		custList.add(customer);
	}

	@Override
	public void register(Waiter waiter) {
		this.waiterList.add(waiter);
	}

	@Override
	public void register(Chef chef) {
		this.chefList.add(chef);
	}

	public void removeCustomer(Customer customer) {
		custList.remove(customer);
	}

	public void printWaiter() {
		System.out.println("--------------------------------------------------");
		System.out.println("| No.  | Initial | Speed |");
		System.out.println("--------------------------------------------------");
		for (int i = 0; i < waiterList.size(); i++) {
			System.out.printf("|%4d| %10s|%5d|\n", i, waiterList.get(i).getInitial(), waiterList.get(i).getSpeed());
		}
		System.out.println("--------------------------------------------------");
	}

	public void printChef() {
		System.out.println("--------------------------------------------------");
		System.out.println("| No.  | Initial | Speed |");
		System.out.println("--------------------------------------------------");
		for (int i = 0; i < chefList.size(); i++) {
			System.out.printf("|%4d| %10s|%5d|\n", i, chefList.get(i).getInitial(), chefList.get(i).getSpeed());
		}
		System.out.println("--------------------------------------------------");
	}

	public int getWaiterSize() {
		return waiterList.size();
	}

	public int getChefSize() {
		return chefList.size();
	}

	public void upgradeChef(int index) {
		waiterList.get(index).setSpeed(waiterList.get(index).getSpeed() + 1);
	}

	public void upgradeWaiter(int index) {
		chefList.get(index).setSpeed(chefList.get(index).getSpeed() + 1);
	}
}
