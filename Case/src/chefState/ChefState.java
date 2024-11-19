package chefState;

import main.Chef;

public abstract class ChefState {

	public abstract void detail();

	public abstract String getStateName();

	public abstract void setStateName(String newName);

	protected Chef chef;

	public ChefState(Chef c) {
		chef = c;
	}
}
