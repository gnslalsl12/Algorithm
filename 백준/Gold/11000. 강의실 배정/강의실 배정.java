import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<TimeTable> OrderedTimeTables = new PriorityQueue<>();
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			int start = Integer.parseInt(tokens.nextToken());
			int end = Integer.parseInt(tokens.nextToken());
			OrderedTimeTables.add(new TimeTable(start, end));
		}
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	private static void solv() {
		searchAscending();
	}

	private static void searchAscending() {
		PriorityQueue<Integer> Rooms = new PriorityQueue<>();
		Rooms.add(OrderedTimeTables.poll().end);	//가장 빠르게 시작하는 수업은 일단 추가 (36라인 Rooms Null Point 방지)
		while (!OrderedTimeTables.isEmpty()) {
			TimeTable temp = OrderedTimeTables.poll();
			if (Rooms.peek() <= temp.start) {	//가장 빠르게 끝나는 강의실 다음으로 배정 가능한 수업인가
				Rooms.poll();	//그럼 그 강의실을 대신해서 쓸 것임
			}	//그게 아니라면 그냥 강의실 하나 추가 (가장 빠르게 끝나는 강의실에 배치가 안된다면, 그 이후 강의실도 배치 불가능이므로)
			Rooms.add(temp.end);
		}
		Result = Rooms.size();
	}

	private static class TimeTable implements Comparable<TimeTable> {
		int start;
		int end;

		public TimeTable(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(TimeTable obj) {	//시작 시간부터 오름차순, 같으면 종료 시간 오름차순
			return obj.start == this.start ? Integer.compare(this.end, obj.end)
					: Integer.compare(this.start, obj.start);
		}

	}

}