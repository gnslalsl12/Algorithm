import java.util.*;
import java.io.*;

public class Main {
	static BufferedWriter write;
	static int N;
	static int L;
	static int F;
	static Queue<Info> Inputs;
	static HashMap<String, Integer> TargetDict;
	static int TargetIndex;
	static HashMap<String, Integer> NameDict;
	static int NameIndex;
	static HashMap<Integer, Integer> Status;
	static long[] FList;
	static PriorityQueue<String> NameAscending;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		String l = tokens.nextToken();
		L = Integer.parseInt(l.substring(0, 3)) * 1440 + Integer.parseInt(l.substring(4, 6)) * 60
				+ Integer.parseInt(l.substring(7, 9));
		F = Integer.parseInt(tokens.nextToken());
		TargetDict = new HashMap<>();
		TargetIndex = 0;
		NameDict = new HashMap<>();
		NameIndex = 0;
		Inputs = new LinkedList<>();
		NameAscending = new PriorityQueue<>();
		for (int n = 0; n < N; n++) {
			tokens = new StringTokenizer(read.readLine());
			Inputs.add(new Info(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken()));
		}
		Status = new HashMap<>(); // ~가 ~를 빌려간 시간간
		FList = new long[NameIndex];
		read.close();
	}

	private static void solv() throws IOException {
		write = new BufferedWriter(new OutputStreamWriter(System.out));
		getWholeF();
		getResult();
		write.close();
	}

	private static void getWholeF() {
		while (!Inputs.isEmpty()) {
			Info current = Inputs.poll();
			int dateInfo = current.dateInfo;
			int target = current.target;
			int name = current.name;
			int statusKey = name * 40000 + target; // 이름과 품목을 합친 키
			if (!Status.containsKey(statusKey)) { // 아직 안 빌려감
				Status.put(statusKey, dateInfo); // 키, 빌려간 날짜 입력
			} else { // 빌려간 이력이 있음
				int diff = dateInfo - Status.remove(statusKey);
				if (diff > L) {
					FList[name] += (diff - L) * F;
				}
			}
		}
	}

	private static void getResult() throws IOException {
		boolean hasAnyone = false;
		while (!NameAscending.isEmpty()) {
			String name = NameAscending.poll();
			long f = FList[NameDict.get(name)];
			if (f != 0L) {
				hasAnyone = true;
				write.write(name + " " + f + "\n");
			}
		}
		if (!hasAnyone)
			write.write("-1\n");
	}

	private static class Info {
		int dateInfo = 0;
		int target;
		int name;

		public Info(String infos0, String infos1, String infos2, String infos3) {
			int m = Integer.parseInt(infos0.substring(5, 7));
			if (m == 1) {
				m = 0;
			} else if (m == 2) {
				m = 31;
			} else if (m == 3) {
				m = 59;
			} else if (m == 4) {
				m = 90;
			} else if (m == 5) {
				m = 120;
			} else if (m == 6) {
				m = 151;
			} else if (m == 7) {
				m = 181;
			} else if (m == 8) {
				m = 212;
			} else if (m == 9) {
				m = 243;
			} else if (m == 10) {
				m = 273;
			} else if (m == 11) {
				m = 304;
			} else if (m == 12) {
				m = 334;
			}
			m += Integer.parseInt(infos0.substring(8, 10));
			m *= 1440;
			this.dateInfo = m + Integer.parseInt(infos1.substring(0, 2)) * 60
					+ Integer.parseInt(infos1.substring(3, 5));
			if (!TargetDict.containsKey(infos2)) { // 처음 보는 품목
				this.target = TargetIndex;
				TargetDict.put(infos2, TargetIndex++);
			} else {
				this.target = TargetDict.get(infos2);
			}
			if (!NameDict.containsKey(infos3)) { // 처음 보는 이름
				this.name = NameIndex;
				NameDict.put(infos3, NameIndex++);
				NameAscending.add(infos3);
			} else {
				this.name = NameDict.get(infos3);
			}
		}
	}
}