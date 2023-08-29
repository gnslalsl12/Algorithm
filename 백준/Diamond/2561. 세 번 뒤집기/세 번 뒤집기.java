import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] Origin;
	static boolean Result;
	static int[] Answer;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		for (int i = 0; i < 3; i++) {
			write.write((Answer[i * 2] + 1) + " " + (Answer[i * 2 + 1] + 1) + "\n");
		}
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		Origin = new int[N];
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			Origin[i] = Integer.parseInt(tokens.nextToken());
		}
		Result = false;
		Answer = new int[6];
		read.close();
	}

	private static void solv() {
		setReverseCase(0, Origin); // 재귀
	}

	private static void setReverseCase(int caseCount, int[] array) {
		if (Result)
			return;
		int[] sectionsIndexes = getSectionsIndexes(array);
		if (sectionsIndexes[0] == -1 || (caseCount == 1 && sectionsIndexes[14] / 2 > 5)
				|| (caseCount == 2 && sectionsIndexes[14] / 2 > 3))
			return; // 남은 뒤집기 횟수로 바로 돌릴 수 없는 케이스
		getSectionComb(sectionsIndexes[14], 0, 0, new int[2], array, sectionsIndexes, caseCount);
	}

	private static int[] getSectionsIndexes(int[] array) { // array의 섹션 인덱스 구하기
		int[] sectionsIndexes = new int[15];
		int count = 0;
		sectionsIndexes[count++] = 0;
		int before = array[0];
		for (int i = 1; i < N; i++) {
			int current = array[i];
			if (Math.abs(current - before) != 1) { // 1차이가 나지 않는 두 인덱스는 앞 뒤 섹션
				sectionsIndexes[count++] = i - 1;
				if (count >= 15)
					return new int[] { -1 };
				sectionsIndexes[count++] = i;
				if (count >= 15)
					return new int[] { -1 };
			}
			before = current;
		}
		sectionsIndexes[count++] = N - 1;
		sectionsIndexes[14] = count;
		return sectionsIndexes;
	}

	private static void getSectionComb(int maxCount, int count, int start, int[] sel, int[] array,
			int[] sectionsIndexes, int caseCount) { // 구간중 두 구간 조합 뽑아서 앞~뒤 구간까지 전부 뒤집기
		if (Result)
			return;
		if (count == 2) {
			setReverseCase(sel, array.clone(), sectionsIndexes, caseCount); // 뒤집기
			return;
		}
		for (int i = 0; i < maxCount; i++) {
			sel[count] = i;
			getSectionComb(maxCount, count + 1, i + 1, sel, array, sectionsIndexes, caseCount);
		}
	}

	private static void setReverseCase(int[] sel, int[] array, int[] sectionsIndexes, int caseCount) {
		int[] copyArray = array.clone();
		int startIndex = sectionsIndexes[sel[0]];
		int endIndex = sectionsIndexes[sel[1]];
		Answer[caseCount * 2] = startIndex;
		Answer[caseCount * 2 + 1] = endIndex;
		for (int i = startIndex; i <= endIndex; i++) {
			array[i] = copyArray[endIndex - (i - startIndex)];
		}
		if (caseCount == 2) { // 3번 다 뒤집었음
			if (isOrigin(array)) { // 1~N까지 골고루 있는가
				Result = true;
				return;
			}
		} else {
			setReverseCase(caseCount + 1, array); // 한 번 더 뒤집기
		}
	}

	private static boolean isOrigin(int[] array) {
		for (int i = 0; i < N; i++) {
			if (array[i] != i + 1)
				return false;
		}
		return true;
	}

}