import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int Q;
	static int Current;
	static Deque<Integer> Back;
	static Stack<Integer> Front;

	public static void main(String[] args) throws IOException {
		initAndSolv();
	}

	private static void initAndSolv() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		Back = new LinkedList<>();
		Front = new Stack<>();
		Current = -1;
		while (Q-- > 0) {
			tokens = new StringTokenizer(read.readLine());
			char order = tokens.nextToken().charAt(0);
			int page = tokens.hasMoreTokens() ? Integer.parseInt(tokens.nextToken()) : 0;
			setWebBrowserWork(order, page);
		}
		write.write(Current + "\n");
		if (Back.isEmpty()) {
			write.write("-1\n");
		} else {
			while (!Back.isEmpty()) {
				write.write(Back.pollLast() + " ");
			}
			write.write("\n");
		}
		if (Front.isEmpty()) {
			write.write("-1\n");
		} else {
			while (!Front.isEmpty()) {
				write.write(Front.pop() + " ");
			}
			write.write("\n");
		}
		read.close();
		write.close();
	}

	private static void setWebBrowserWork(char order, int page) {
		if (order == 'A') {
			doAccess(page);
		} else if (order == 'C') {
			doCompress();
		} else {
			doMove(order == 'B');
		}
	}

	private static void doAccess(int page) {
		Front = new Stack<>();
		if (Current != -1)
			Back.addLast(Current);
		Current = page;
	}

	private static void doCompress() {
		int size = Back.size();
		int tempBefore = -1;
		while (size-- > 0) {
			int tempCurrent = Back.pollFirst();
			if (tempBefore == tempCurrent) {
				continue;
			} else {
				Back.addLast(tempCurrent);
				tempBefore = tempCurrent;
			}
		}
	}

	private static void doMove(boolean backWard) {
		if (backWard && !Back.isEmpty()) {
			Front.add(Current);
			Current = Back.pollLast();
		} else if (!backWard && !Front.isEmpty()) {
			Back.add(Current);
			Current = Front.pop();
		}
	}

}