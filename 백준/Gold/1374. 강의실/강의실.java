import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<Integer> Rooms;
	static PriorityQueue<int[]> Classes;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		init();
		for (int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			tokens.nextToken();
			Classes.add(new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()) });
		}
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		Rooms = new PriorityQueue<>();
		Comparator<int[]> ClassesComparator = (o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1])
				: Integer.compare(o1[0], o2[0]); // 강의들을 시작시간 오름차순, 끝시간 오름차순으로 정렬하기 위한 Comp
		Classes = new PriorityQueue<>(ClassesComparator);
	}

	private static void solv() {
		addClassAtRooms();
	}

	private static void addClassAtRooms() {
		while (!Classes.isEmpty()) {
			int[] tempClass = Classes.poll(); // 가장 빠르게 시작하는 강의 하나의 시작시간
			int minTime = Rooms.isEmpty() ? 0 : Rooms.peek(); // 모든 강의실 중 가장 빠르게 끝나는 강의실의 시간
			if (minTime <= tempClass[0]) { // 새 강의가 빠르게 끝나는 강의실 이후에 시작하는가
				Rooms.poll(); // 그럼 그 강의실에 해당 강의를 넣으면 되니까 기존 강의는 뺴고
			}
			Rooms.add(tempClass[1]); // 강의 추가 (가장 빠른 강의실보다 빠르게 시작해야하면 다른 강의실에도 못 넣으니까 아무 강의실도 빼지 않고 강의 추가(강의실 추가))
		}
		Result = Rooms.size();
	}

}