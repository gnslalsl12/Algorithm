package day1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1043_trash {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int truepeople = Integer.parseInt(tokens.nextToken());
		boolean[] defaulttrueguys = new boolean[N + 1];
		for (int i = 0; i < truepeople; i++) {
			int p = Integer.parseInt(tokens.nextToken());
			defaulttrueguys[p] = true;
		}
		boolean[][] partysInfo = new boolean[M][N + 1];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int participates = Integer.parseInt(tokens.nextToken());
			for (int p = 0; p < participates; p++) {
				int partp = Integer.parseInt(tokens.nextToken());
//				if(defaulttrueguys[partp]) mustTrueParty[i] = true;
				partysInfo[i][partp] = true;
			}
		}
//		System.out.println(Arrays.toString(defaulttrueguys));
		boolean[] mustTrueParty = new boolean[M];
		for (int person = 1; person <= N; person++) {
//			System.out.println("Person : " + person);
//			System.out.println("진실을 아는가? : " + defaulttrueguys[person]);
			for (int p = 0; p < M; p++) {
				if (defaulttrueguys[person] && partysInfo[p][person]) { // 진실을 아는 사람이고 p번째 파티에 참가한다면
					if (mustTrueParty[p])
						continue; // 이미 진실을 말할 파티여
					mustTrueParty[p] = true; // 해당 파티는 트루 해야하고
					for (int madeTrue = 1; madeTrue <= N; madeTrue++) {
						if (partysInfo[p][madeTrue])
							defaulttrueguys[madeTrue] = true; // 해당 파티에 참가하는 사람들은 진실을 알고있어야함
					}
				}
			}
//			System.out.println("처리된 결과 파티 :" + Arrays.toString(mustTrueParty));
		}
		
		
		/*for (int person = 1; person <= N; person++) {
//			System.out.println("Person : " + person);
//			System.out.println("진실을 아는가? : " + defaulttrueguys[person]);
			for (int p = 0; p < M; p++) {
				if (defaulttrueguys[person] && partysInfo[p][person]) { // 진실을 아는 사람이고 p번째 파티에 참가한다면
					if (mustTrueParty[p])
						continue; // 이미 진실을 말할 파티여
					mustTrueParty[p] = true; // 해당 파티는 트루 해야하고
					for (int madeTrue = 1; madeTrue <= N; madeTrue++) {
						if (partysInfo[p][madeTrue])
							defaulttrueguys[madeTrue] = true; // 해당 파티에 참가하는 사람들은 진실을 알고있어야함
					}
				}
			}
//			System.out.println("처리된 결과 파티 :" + Arrays.toString(mustTrueParty));
		}*/
		
		
		for (int party = 0; party < M; party++) {
			if (mustTrueParty[party])
				continue;
			for (int people = 1; people <= N; people++) {
				if (partysInfo[party][people] && defaulttrueguys[people])
					mustTrueParty[party] = true;
			}
		}
		int count = 0;
		for (int i = 0; i < M; i++) {
			if (!mustTrueParty[i])
				count++;
		}
		System.out.println(count);
	}
}