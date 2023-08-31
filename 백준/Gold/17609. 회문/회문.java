import java.util.*;
import java.io.*;

public class Main {
	static int T;
	static Queue<String> Inputs;
	static Queue<Integer> Outputs;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(read.readLine());
		Inputs = new LinkedList<>();
		for (int t = 0; t < T; t++) {
			Inputs.add(read.readLine());
		}
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		Outputs = new LinkedList<>();
		getPalinOrNot();
		while (!Outputs.isEmpty()) {
			write.write(Outputs.poll() + "\n");
		}
		write.close();
	}

	private static void getPalinOrNot() {
		while (!Inputs.isEmpty()) {
			Result = 2;
			String input = Inputs.poll();
			isPalin(input, 0, input.length() - 1, false);
			Outputs.add(Result);
		}
	}

	private static void isPalin(String input, int left, int right, boolean assemble) {
		if (left >= right) {
			if (assemble) {
				Result = Math.min(Result, 1);
			} else {
				Result = 0;
			}
			return;
		}
		char leftC = input.charAt(left);
		char rightC = input.charAt(right);
		if (leftC == rightC) {
			isPalin(input, left + 1, right - 1, assemble);
		} else {
			if (assemble) {
				Result = Math.min(Result, 2);
				return;
			}
			if (input.charAt(left + 1) == rightC || leftC == input.charAt(right - 1)) {
				if (input.charAt(left + 1) == rightC) {
					isPalin(input, left + 1, right, true);
				}
				if (leftC == input.charAt(right - 1)) {
					isPalin(input, left, right - 1, true);
				}
			} else {
				if (Math.abs(left - right) == 1) {
					Result = 0;
					return;
				} else {
					Result = Math.min(Result, 2);
					return;
				}
			}
		}
	}

}