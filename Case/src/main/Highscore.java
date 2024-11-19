package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscore {
	private static volatile Highscore instance = null;
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<Integer> score = new ArrayList<>();

	private Highscore() {
		readFile();
	}

	private void readFile() {
		File file = new File("highscore.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			Scanner rf = new Scanner(file);
			while (rf.hasNextLine()) {
				String data = rf.nextLine();
				String[] temp = data.split("#");
				if (temp.length == 2) {
					name.add(temp[0]);
					score.add(Integer.parseInt(temp[1]));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateFile() {
		File file = new File("highscore.txt");
		try {
			FileWriter fw = new FileWriter(file);
			for (int i = 0; i < name.size(); i++) {
				fw.write(name.get(i) + "#" + score.get(i) + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Highscore getInstance() {
		if (instance == null) {
			synchronized (Highscore.class) {
				if (instance == null) {
					instance = new Highscore();
				}
			}
		}

		return instance;
	}

	public void sort() {
		for (int i = 0; i < score.size() - 1; i++) {
			for (int j = i; j < score.size(); j++) {
				if (score.get(i) < score.get(j)) {
					swap(i, j);
				}
			}
		}
	}

	public void swap(int idx1, int idx2) {
		int temp = score.get(idx1);
		score.set(idx1, score.get(idx2));
		score.set(idx2, temp);

		String tempname = name.get(idx1);
		name.set(idx1, name.get(idx2));
		name.set(idx2, tempname);
	}

	public ArrayList getScore() {
		sort();
		return score;
	}

	public ArrayList getNames() {
		return name;
	}

	public void addNewData(String newName, Integer newScore) {
		name.add(newName);
		score.add(newScore);
	}

}
