import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int exp;
	static int N, M;
	static dirXY startpoint;
	static char[][] maps;
	static Queue<Integer> Moves = new LinkedList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Map<Integer, Monster> MonsterDict = new HashMap<>();
	static Map<Integer, Item> BoxDict = new HashMap<>();
	static HoonyClass Hoony;
	static int turncount;
	static String GameResult;

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new char[N + 1][M + 1];
		int boxes = 0;
		int mons = 0;
		for (int i = 1; i <= N; i++) {
			String templine = read.readLine();
			for (int j = 1; j <= M; j++) {
				char tempchar = templine.charAt(j - 1);
				maps[i][j] = tempchar;
				if (tempchar == 'B')
					boxes++;
				else if (tempchar == '&' || tempchar == 'M') {
					mons++;
				} else if (tempchar == '@') {
					startpoint = new dirXY(i, j);
					Hoony = new HoonyClass(new dirXY(i, j), 20, 20, 2, 0, 2, 0, 1, 0, 5, 0, new boolean[7]);
				}
			}
		}
		String moveline = read.readLine();
		for (int i = 0; i < moveline.length(); i++) {
			char tempchar = moveline.charAt(i);
			if (tempchar == 'U')
				Moves.add(0);
			else if (tempchar == 'R')
				Moves.add(1);
			else if (tempchar == 'D')
				Moves.add(2);
			else
				Moves.add(3);
		}
		for (int i = 0; i < mons; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			String name = tokens.nextToken();
			int pow = Integer.parseInt(tokens.nextToken());
			int def = Integer.parseInt(tokens.nextToken());
			int hp = Integer.parseInt(tokens.nextToken());
			int exp = Integer.parseInt(tokens.nextToken());
			MonsterDict.put(getNumXY(x, y), new Monster(name, pow, def, hp, exp));
		}
		// System.out.println();
		for (int i = 0; i < boxes; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			char type = tokens.nextToken().charAt(0);
			String temp = tokens.nextToken();
			int value = 0;
			if (type == 'O') {
				if (temp.equals("HR")) {
					value = 0;
				} else if (temp.equals("RE")) {
					value = 1;
				} else if (temp.equals("CO")) {
					value = 2;
				} else if (temp.equals("EX")) {
					value = 3;
				} else if (temp.equals("DX")) {
					value = 4;
				} else if (temp.equals("HU")) {
					value = 5;
				} else {
					value = 6;
				}
			} else {
				value = Integer.parseInt(temp);
			}
			BoxDict.put(getNumXY(x, y), new Item(type, value));
		}
		GameResult = "";
		turncount = 0;
		maps[Hoony.loc.x][Hoony.loc.y] = '.';
		breakgame: while (!Moves.isEmpty()) {
			turncount++;
			Hoony.Hmove(Moves.poll());
			char step = maps[Hoony.loc.x][Hoony.loc.y];
			switch (step) {
			case ('&'): {
				boolean win = battleWith(Hoony.loc, false);
				String monstername = MonsterDict.get(getNumXY(Hoony.loc.x, Hoony.loc.y)).name;
				if (win) {
					Monster monster = MonsterDict.remove(getNumXY(Hoony.loc.x, Hoony.loc.y));
					if (Hoony.Inventory[0])
						Hoony.Hp = Math.min(Hoony.Hp + 3, Hoony.MaxHp);
					if (Hoony.Inventory[3]) { // 경험치 1.2배
						Hoony.Exp += (int) monster.exp * (1.2);
					} else {
						Hoony.Exp += monster.exp;
					}
					levelUp();
					maps[Hoony.loc.x][Hoony.loc.y] = '.';
				} else {
					if (youDied()) {
						GameResult = "YOU HAVE BEEN KILLED BY " + monstername + "..";
						break breakgame;
					}
				}
				break;
			}
			case ('M'): {
				boolean win = battleWith(Hoony.loc, true);
				String monstername = MonsterDict.get(getNumXY(Hoony.loc.x, Hoony.loc.y)).name;
				if (win) {
					Monster monster = MonsterDict.remove(getNumXY(Hoony.loc.x, Hoony.loc.y));
					if (Hoony.Inventory[0])
						Hoony.Hp = Math.min(Hoony.Hp + 3, Hoony.MaxHp);
					if (Hoony.Inventory[3]) { // 경험치 1.2배
						Hoony.Exp += Math.floor(monster.exp * (1.2));
					} else {
						Hoony.Exp += monster.exp;
					}
					levelUp();
					maps[Hoony.loc.x][Hoony.loc.y] = '.';
					GameResult = "YOU WIN!";
					break breakgame;
				} else {
					if (youDied()) { // 살아나지 못하고 죽음
						GameResult = "YOU HAVE BEEN KILLED BY " + monstername + "..";
						break breakgame;
					}
				}
				break;
			}
			case ('^'): { // 아야
				if (Hoony.Inventory[4]) {
					Hoony.Hp--;
				} else {
					Hoony.Hp -= 5;
				}
				if (youDied()) {
					GameResult = "YOU HAVE BEEN KILLED BY SPIKE TRAP..";
					break breakgame;
				}
				break;
			}
			case ('B'): { // 헉
				Item item = BoxDict.remove(getNumXY(Hoony.loc.x, Hoony.loc.y));
				if (item.itemtype == 'W') {
					Hoony.WeaponPow = item.value;
				} else if (item.itemtype == 'A') {
					Hoony.ArmorDef = item.value;
				} else {
					getO(item.value);
				}
				maps[Hoony.loc.x][Hoony.loc.y] = '.';
				break;
			}
			}// switch
			if (Moves.isEmpty())
				GameResult = "Press any key to continue.";
		}
		printResult();
	}

	private static void levelUp() {
		if (Hoony.Exp >= Hoony.MaxExp) {
			Hoony.Level++;
			Hoony.Exp = 0;
			Hoony.MaxExp = Hoony.Level * 5;
			Hoony.MaxHp += 5;
			Hoony.Pow += 2;
			Hoony.Def += 2;
			Hoony.Hp = Hoony.MaxHp;
		}
	}

	private static boolean battleWith(dirXY Mloc, boolean boss) throws CloneNotSupportedException {
		Monster monster = MonsterDict.get(getNumXY(Mloc.x, Mloc.y)).clone();
		int realpow = Hoony.Pow + Hoony.WeaponPow;
		if (Hoony.Inventory[2]) {
			if (Hoony.Inventory[4])
				realpow *= 3;
			else
				realpow *= 2;
		}
		realpow -= monster.def;
		realpow = Math.max(1, realpow);
		int realdamage = monster.pow - Hoony.Def - Hoony.ArmorDef;
		realdamage = Math.max(1, realdamage);
		// 첫턴만 분별
		if (boss) {
			if (Hoony.Inventory[5]) {
				Hoony.Hp = Hoony.MaxHp;
			} // 풀피 충전
			monster.hp -= realpow;
			if (monster.hp <= 0) {
				return true;
			}
			if (!Hoony.Inventory[5]) {
				Hoony.Hp -= realdamage;
			}
			if (Hoony.Hp <= 0)
				return false;
		} else {
			monster.hp -= realpow;
			if (monster.hp <= 0) {
				return true;
			}
			Hoony.Hp -= realdamage;
			if (Hoony.Hp <= 0)
				return false;
		}
		realpow = Math.max(Hoony.Pow + Hoony.WeaponPow - monster.def, 1);
		boolean gameresult = false;
		while (true) {
			monster.hp -= realpow;
			if (monster.hp <= 0) {
				gameresult = true;
				break;
			}
			Hoony.Hp -= realdamage;
			if (Hoony.Hp <= 0) {
				gameresult = false;
				break;
			}
		}
		return gameresult;
	}

	private static void getO(int item) {
		if (Hoony.InvenSize == 4 || Hoony.Inventory[item])
			return;
		Hoony.Inventory[item] = true;
		Hoony.InvenSize++;
	}

	private static boolean youDied() {
		if (Hoony.Hp <= 0) {
			if (Hoony.Inventory[1]) {
				Hoony.Inventory[1] = false;
				Hoony.InvenSize--;
				Hoony.loc = startpoint;
				Hoony.Hp = Hoony.MaxHp;
				return false;
			}
			return true;
		} else
			return false;
	}

	private static boolean isIn(int x, int y) {
		return x > 0 && y > 0 && x <= N && y <= M;
	}

	private static int getNumXY(int x, int y) {
		return x * M + y;
	}

	private static class HoonyClass {
		dirXY loc;
		int Hp;
		int MaxHp;
		int Pow;
		int WeaponPow;
		int Def;
		int ArmorDef;
		int Level;
		int Exp;
		int MaxExp;
		int InvenSize;
		boolean[] Inventory = new boolean[7];

		public HoonyClass(dirXY loc, int hp, int maxHp, int pow, int weaponPow, int def, int armorDef, int level,
				int exp, int maxExp, int invenSize, boolean[] inventory) {
			this.loc = loc;
			Hp = hp;
			MaxHp = maxHp;
			Pow = pow;
			WeaponPow = weaponPow;
			Def = def;
			ArmorDef = armorDef;
			Level = level;
			Exp = exp;
			MaxExp = maxExp;
			InvenSize = invenSize;
			Inventory = inventory;
		}

		public void Hmove(int dir) {
			int tempx = this.loc.x + deltas[dir][0];
			int tempy = this.loc.y + deltas[dir][1];
			if (isIn(tempx, tempy) && maps[tempx][tempy] != '#') {
				this.loc = new dirXY(tempx, tempy);
			}
		}
	}

	private static class Item {
		char itemtype;
		int value;

		public Item(char itemtype, int value) {
			super();
			this.itemtype = itemtype;
			this.value = value;
		}
	}

	private static class Monster implements Cloneable {
		String name;
		int pow;
		int def;
		int hp;
		int exp;

		public Monster(String name, int pow, int def, int hp, int exp) {
			super();
			this.name = name;
			this.pow = pow;
			this.def = def;
			this.hp = hp;
			this.exp = exp;
		}

		@Override
		protected Monster clone() throws CloneNotSupportedException {
			return (Monster) super.clone();
		}

	}

	private static class dirXY implements Cloneable {
		int x;
		int y;

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static void printamap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (i == Hoony.loc.x && j == Hoony.loc.y && Hoony.Hp > 0) {
					sb.append("@");
				} else
					sb.append(maps[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void printResult() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		printamap();
		write.write(String.format("Passed Turns : %d\n", turncount));
		write.write(String.format("LV : %d\n", Hoony.Level));
		write.write(String.format("HP : %d/%d\n", Math.max(Hoony.Hp, 0), Hoony.MaxHp));
		write.write(String.format("ATT : %d+%d\n", Hoony.Pow, Hoony.WeaponPow));
		write.write(String.format("DEF : %d+%d\n", Hoony.Def, Hoony.ArmorDef));
		write.write(String.format("EXP : %d/%d\n", Hoony.Exp, Hoony.MaxExp));
		write.write(GameResult);
		write.close();
	}
}