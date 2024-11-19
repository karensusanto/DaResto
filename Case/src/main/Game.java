package main;

import java.util.Scanner;

public class Game {
	Restaurant rest;
	Scanner sc = new Scanner(System.in);

	public Game(String name) {
		rest = rest.getInstance();
		rest.reset(name);
		rest.start();
		game();
	}

	public void game() {
		boolean p = true;
		while (p) {
			sc.nextLine();
			rest.pause();
			while (rest.isPaused() == true) {
				System.out.println("1. Continue Business\n2. Upgrade Restaurant\n3. Close Business");
				int n;
				do {
					System.out.print("Input [1..3] :");
					n = sc.nextInt();
					sc.nextLine();
				} while (n < 1 || n > 3);

				switch (n) {
				case 1: {
					rest.continueBusiness();
					break;
				}
				case 2: {
					rest.upgradeRestaurant();
					break;
				}
				case 3: {
					rest.closeBusiness();
					rest.setPaused(false);
					p = false;
					break;
				}
				}
			}
		}

	}
}