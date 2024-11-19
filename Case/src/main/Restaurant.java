package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant extends Thread implements Facade, Observable {
	private String name;
	private ServiceMediator service;
	private CustomerFactory custf;
	private WaiterFactory waitf;
	private ChefFactory cheff;
	private int chairNum, money, score;
	private Scanner sc;
	private static volatile Restaurant instance = null;

	private volatile boolean isPaused = false;

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	private Restaurant() {
		service = new ServiceMediator(this);
		custf = new CustomerFactory(service);
		waitf = new WaiterFactory();
		cheff = new ChefFactory();
		sc = new Scanner(System.in);
	}

	public static Restaurant getInstance() {
		if (instance == null) {
			synchronized (Restaurant.class) {
				if (instance == null) {
					instance = new Restaurant();
				}
			}
		}

		return instance;
	}

	public void reset(String newName) {
		service.clearArrayLists();
		name = newName;
		chairNum = 4;
		money = 1300;
		score = 0;
		waitf.createWaiter(service);
		waitf.createWaiter(service);
		cheff.createChef(service);
		cheff.createChef(service);
	}

	@Override
	public void run() {
		while (true) {
			if (isPaused == false) {
				printGameStatus();
				if (service.custNum() < chairNum) {
					sendNotif("make customer");
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void printGameStatus() {
		System.out.println("\nRestaurant '" + name + "' is on Business!");
		System.out.println("Status");
		System.out.println("Money : Rp. " + money);
		System.out.println("Score : " + score + " Points");
		System.out.println("Size : " + chairNum + " seats");
		System.out.println(
				"====================================================================\n          Customer             Waiter               Cook\n--------------------------------------------------------------------");
		service.printGameStatus();
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void continueBusiness() {
		isPaused = false;
	}

	@Override
	public void upgradeRestaurant() {
		boolean r = true;
		while (r) {
			System.out.println("Status");
			System.out.println("Money : Rp. " + money);
			System.out.println("Score : " + score + " Points");
			System.out.println("Size : " + chairNum + " seats");
			System.out.println(
					"1. Increase Restaurant's Seat <Rp. 400>\n2. Hire New Employee\n3. Upgrade Waiter <Rp. 150>\n4. Upgrade Cook <Rp. 150>\n5. Back to Pause Menu\n");
			int input;
			do {
				System.out.print("Input [1..5] : ");
				input = sc.nextInt();
				sc.nextLine();
			} while (input < 1 || input > 5);

			switch (input) {
			case 1: {
				chairNum += 1;
				money -= 400;
				break;
			}
			case 2: {
				hireNewEmployee();
				break;
			}
			case 3: {
				upgradeWaiter();
				break;
			}
			case 4: {
				upgradeCook();
				break;
			}
			case 5: {
				r = false;
				break;
			}
			}
		}
	}

	public void hireNewEmployee() {
		boolean h = true;
		while (h) {
			System.out.println("Status");
			System.out.println("Money : Rp. " + money);
			System.out.println("Score : " + score + " Points");
			System.out.println("Size : " + chairNum + " seats");
			System.out.println("HIRE NEW EMPLOYEE");
			System.out.println("1. Hire New Waiter <Rp. 300>\n2. Hire New Cook <Rp. 400>\n3. Back to Upgrade Menu");
			int input;
			do {
				System.out.print("Input [1..3] : ");
				input = sc.nextInt();
				sc.nextLine();
			} while (input < 1 || input > 3);

			switch (input) {
			case 1: {
				waitf.createWaiter(service);
				money -= 300;
			}
			case 2: {
				cheff.createChef(service);
				money -= 400;
			}
			case 3: {
				h = false;
			}
			}
		}

	}

	public void upgradeWaiter() {
		System.out.println("UPGRADE WAITER <Rp. 150>");
		service.printWaiter();
		int input;
		do {
			System.out.println("Input employee's number to upgrade [0 to exit] : ");
			input = sc.nextInt();
			sc.nextLine();
		} while (input < 0 || input > service.getWaiterSize());
		if (input == 0)
			return;
		else {
			service.upgradeWaiter(input);
			money -= 150;
		}
	}

	public void upgradeCook() {
		System.out.println("UPGRADE WAITER <Rp. 150>");
		service.printChef();
		int input;
		do {
			System.out.println("Input employee's number to upgrade [0 to exit] : ");
			input = sc.nextInt();
			sc.nextLine();
		} while (input < 0 || input > service.getChefSize());
		if (input == 0)
			return;
		else {
			service.upgradeChef(input);
			money -= 150;
		}
	}

	@Override
	public void closeBusiness() {
		Highscore hs = Highscore.getInstance();
		hs.addNewData(name, score);
		hs.updateFile();
		ArrayList<Integer> scores = hs.getScore();
		ArrayList<String> names = hs.getNames();
		System.out.println("         HIGHSCORE\n=========================================");
		System.out.println("        Rank       Restaurant's name      Score");
		int i = 1;
		while (i <= 10) {
			if (i > names.size())
				break;
			System.out.printf("%10d%20s%16d\n", i, names.get(i - 1), scores.get(i - 1));
			i++;
		}
		System.out.println("Press Enter to Continue...");
		sc.nextLine();
		System.out.println("Prove Our Will Emerge Our Spirit");
		System.out.println("Press Enter to Continue...");
		sc.nextLine();
		System.exit(0);
	}

	@Override
	public void pause() {
		isPaused = true;
	}

	@Override
	public void sendNotif(String message) {
		custf.receiveNotif(message);
	}

	@Override
	public void register(Observer observer) {
		// TODO Auto-generated method stub

	}
}
