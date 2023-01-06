package day1116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_2598 {
	static HashSet<Long> Dictionary = new HashSet<>();
	static int[] Dices = new int[4];
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			String templine = read.readLine();
			int tempdice = 0;
			for (int j = 0; j < 6; j++) {
				int tempnum;
				if (templine.charAt(j) == 'R') {
					tempnum = 1;
				} else if (templine.charAt(j) == 'G') {
					tempnum = 2;
				} else if (templine.charAt(j) == 'B') {
					tempnum = 3;
				} else {
					tempnum = 4;
				}
				tempdice += tempnum * Math.pow(10, j);
			}
			Dices[i] = tempdice;
		}
		setDices(123);
		System.out.println(count);
	}

	private static boolean findFromHash(int dice1, int dice2, int dice3, int dice4) {
		long MA = (dice4 / 10000) % 10;
		long GAS = dice4 % 10 + (dice3 % 10) * 10 + (dice2 % 10) * 100 + (dice1 % 10) * 1000;
		long NAS = (dice4 / 10) % 10 + ((dice3 / 10) % 10) * 10 + ((dice2 / 10) % 10) * 100
				+ ((dice1 / 10) % 10) * 1000;
		long DAS = (dice4 / 100) % 10 + ((dice3 / 100) % 10) * 10 + ((dice2 / 100) % 10) * 100
				+ ((dice1 / 100) % 10) * 1000;
		long RAS = (dice4 / 1000) % 10 + ((dice3 / 1000) % 10) * 10 + ((dice2 / 1000) % 10) * 100
				+ ((dice1 / 1000) % 10) * 1000;
		boolean[] setcheck = new boolean[5];
		for (int i = 1; i < 1001; i *= 10) {
			if (setcheck[(int) ((GAS / i) % 10)]) {
				return false;
			}
			setcheck[(int) ((GAS / i) % 10)] = true;
		}
		setcheck = new boolean[5];
		for (int i = 1; i < 1001; i *= 10) {
			if (setcheck[(int) ((NAS / i) % 10)]) {
				return false;
			}
			setcheck[(int) ((NAS / i) % 10)] = true;
		}
		setcheck = new boolean[5];
		for (int i = 1; i < 1001; i *= 10) {
			if (setcheck[(int) ((DAS / i) % 10)]) {
				return false;
			}
			setcheck[(int) ((DAS / i) % 10)] = true;
		}
		setcheck = new boolean[5];
		for (int i = 1; i < 1001; i *= 10) {
			if (setcheck[(int) ((RAS / i) % 10)]) {
				return false;
			}
			setcheck[(int) ((RAS / i) % 10)] = true;
		}
		long setting1 = MA;
		long setting2 = MA;
		long setting3 = MA;
		long setting4 = MA;
		setting1 += GAS * 10 + NAS * 100000 + DAS * 1000000000;
		setting2 += NAS * 10 + DAS * 100000 + RAS * 1000000000;
		setting3 += DAS * 10 + RAS * 100000 + GAS * 1000000000;
		setting4 += RAS * 10 + GAS * 100000 + NAS * 1000000000;
		GAS *= 10000000;
		GAS *= 1000000;
		NAS *= 10000000;
		NAS *= 1000000;
		DAS *= 10000000;
		DAS *= 1000000;
		RAS *= 10000000;
		RAS *= 1000000;
		setting1 += RAS;
		setting2 += GAS;
		setting3 += NAS;
		setting4 += DAS;
		if (Dictionary.contains(setting1) || Dictionary.contains(setting2) || Dictionary.contains(setting3)
				|| Dictionary.contains(setting4)) {
			return false;
		} else {
			Dictionary.add(setting1);
			Dictionary.add(setting2);
			Dictionary.add(setting3);
			Dictionary.add(setting4);
			return true;
		}
	}

	private static int DiceByIndex(int G, int N, int D, int R, int M, int B) { // 주사위 굴리기
		return G + N * 10 + D * 100 + R * 1000 + M * 10000 + B * 100000;
	}

	private static int Wheeling(int dice, int dir) { // 주사위 평행회전
		int wheeled = 0;
		int GA = dice % 10;
		int NA = (dice / 10) % 10;
		int DA = (dice / 100) % 10;
		int RA = (dice / 1000) % 10;
		int MA = (dice / 10000) % 10;
		int BA = (dice / 100000) % 10;
		switch (dir) {
		case (0): {
			wheeled = dice;
			break;
		}
		case (1): {
			wheeled = DiceByIndex(GA, MA, DA, BA, RA, NA);
			break;
		}
		case (2): {
			wheeled = DiceByIndex(GA, RA, DA, NA, BA, MA);
			break;
		}
		case (3): {
			wheeled = DiceByIndex(GA, BA, DA, MA, NA, RA);
			break;
		}
		}
		return wheeled;
	}

	private static int rotateDice(int Dice, int FrontFloor) { // 앞면 기준으로 주사위 세팅
		int tempdice = Dice;
		int rotate = 0;
		int GA = tempdice % 10;
		int NA = (tempdice / 10) % 10;
		int DA = (tempdice / 100) % 10;
		int RA = (tempdice / 1000) % 10;
		int MA = (tempdice / 10000) % 10;
		int BA = (tempdice / 100000) % 10;
		switch (FrontFloor) {
		case (0): {
			rotate = tempdice;
			break;
		}
		case (1): {
			rotate = DiceByIndex(NA, DA, RA, GA, MA, BA);
			break;
		}
		case (2): {
			rotate = DiceByIndex(DA, RA, GA, NA, MA, BA);
			break;
		}
		case (3): {
			rotate = DiceByIndex(RA, GA, NA, DA, MA, BA);
			break;
		}
		case (4): {
			rotate = DiceByIndex(MA, NA, BA, RA, DA, GA);
			break;
		}
		case (5): {
			rotate = DiceByIndex(BA, NA, MA, RA, GA, DA);
			break;
		}
		}
		return rotate;

	}

	private static void checkTower(int dice1, int dice2, int dice3, int dice4, int c1, int c2, int c3, int c4) {
		dice1 = rotateDice(dice1, c1);
		dice2 = rotateDice(dice2, c2);
		dice3 = rotateDice(dice3, c3);
		dice4 = rotateDice(dice4, c4); // 주사위마다 앞면 기준으로 세팅됨
		for (int d1 = 0; d1 < 4; d1++) {
			for (int d2 = 0; d2 < 4; d2++) {
				for (int d3 = 0; d3 < 4; d3++) {
					for (int d4 = 0; d4 < 4; d4++) {
						int Wheeled1 = Wheeling(dice1, d1);
						int Wheeled2 = Wheeling(dice2, d2);
						int Wheeled3 = Wheeling(dice3, d3);
						int Wheeled4 = Wheeling(dice4, d4);
						if (findFromHash(Wheeled1, Wheeled2, Wheeled3, Wheeled4)) { 
							count++;
						}
					}
				}
			}
		}
	}

	private static void setDices(int order) { // 위에서부터 아래로 0123
		int dice1 = Dices[(order / 1000) % 10];
		int dice2 = Dices[(order / 100) % 10];
		int dice3 = Dices[(order / 10) % 10];
		int dice4 = Dices[order % 10];
		for (int c1 = 0; c1 < 6; c1++) {
			int set1 = (int) (dice1 / (Math.pow(10, c1))) % 10;
			for (int c2 = 0; c2 < 6; c2++) {
				int set2 = (int) (dice2 / (Math.pow(10, c2))) % 10;
				if (set1 == set2)
					continue;
				for (int c3 = 0; c3 < 6; c3++) {
					int set3 = (int) (dice3 / (Math.pow(10, c3))) % 10;
					if (set1 == set3 || set2 == set3)
						continue;
					for (int c4 = 0; c4 < 6; c4++) {
						int set4 = (int) (dice4 / (Math.pow(10, c4))) % 10;
						if (set1 == set4 || set2 == set4 || set3 == set4)
							continue;
						checkTower(dice1, dice2, dice3, dice4, c1, c2, c3, c4);
					}
				}
			}
		}
	}

}