package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	Scanner sc = new Scanner(System.in);
	
	public void play() {
		System.out.println("Enter Restauran Name (3-15 characters):");
		String name;
		do {
			name = sc.nextLine();
		}while(name.length()<3||name.length()>15);
		
		new Game(name);		
	}
	
	public void highscore() {
		Highscore hs = Highscore.getInstance();
		ArrayList<Integer> scores = hs.getScore();
		ArrayList<String> names = hs.getNames();
		System.out.println("         HIGHSCORE\n=========================================");
		System.out.println("        Rank       Restaurant's name      Score");
		for(int i=0;i<10;i++) {
			System.out.println(         i+"     "+names.get(i)+"     "+scores.get(i));
		}
	}
	
	public Main() {
		while(true) {
			System.out.println("1. Play New Restaurant\n2. High Score\n3. Exit");
			int m;
			do {
				m = sc.nextInt();sc.nextLine();
			}while(m<1||m>3);
			
			switch(m) {
			case 1:{
				play();
				break;
			}
			case 2:{
				highscore();
				break;
			}
			case 3:{
				System.out.println("Prove Our Will Emerge Our Spirit\n\n\n\n\n");
				System.out.println("Press Enter to Continue...");
				
				sc.nextLine();
				System.exit(0);;
				break;
			}
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
