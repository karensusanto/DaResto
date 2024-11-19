package main;

public interface Mediator {
	public void register(Waiter waiter);
	public void register(Chef chef);
	public void register(Customer customer);
}
