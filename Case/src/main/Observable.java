package main;

public interface Observable {
	public void sendNotif(String message);
	public void register(Observer observer);
}
