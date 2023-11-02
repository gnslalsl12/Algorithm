import java.util.*;
import java.io.*;

public class Main {
	static Queue<Integer> Infos;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		Infos = new LinkedList<>();
		while (true) {
			int a = readInt();
			int b = readInt();
			if (a == 0 && b == 0)
				break;
			Infos.add(a);
			Infos.add(b);
		}
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			if (n == 45)
				neg = true;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) > 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return neg ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		while (!Infos.isEmpty()) {
			write.write(getResult(Infos.poll(), Infos.poll()) + "\n");
		}
		write.close();
	}

	private static int getResult(int a, int b) {
		int wholeAmount = getElecAmount(a); // 둘이서 쓴 전기 양
		return getSgFee(wholeAmount, b);
	}

	// 200, 29700, 4950000
	private static int getElecAmount(int a) {
		int output = 0;
		if (a >= 4979900) {
			output = 1000000 + (a - 4979900) / 7;
		} else if (a >= 29900) {
			output = 10000 + (a - 29900) / 5;
		} else if (a >= 200) {
			output = 100 + (a - 200) / 3;
		} else {
			output = a / 2;
		}
		return output;
	}

	private static int getFee(int amount) {
		int output = 0;
		if (amount > 1000000) {
			output = 4979900 + (amount - 1000000) * 7;
		} else if (amount > 10000) {
			output = 29900 + (amount - 10000) * 5;
		} else if (amount > 100) {
			output = 200 + (amount - 100) * 3;
		} else {
			output = amount * 2;
		}
		return output;
	}

	private static int getSgFee(int wholeAmount, int b) {
		int min = 0;
		int max = wholeAmount;
		while (true) {
			int sgFee = getFee((min + max) / 2);
			int neiFee = getFee(wholeAmount - (min + max) / 2);
			if (neiFee - sgFee == b) {
				min = sgFee;
				break;
			} else if (neiFee - sgFee < b) {
				max = (min + max) / 2;
			} else {
				min = (min + max) / 2;
			}
		}
		return min;
	}

}