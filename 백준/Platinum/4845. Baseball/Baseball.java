import java.util.*;
import java.io.*;

public class Main {
	static String[] Teams; // 팀 이름
	static Player[][] HitterInfos; // 팀별 선수 정보
	static int[] HitterIndex; // 팀별 쳐야하는 선수 번호
	static int[] Score; // 팀별 득점 수
	static int[][] TeamHistory; // 팀별 run, hit 기록

	static int GameCount; // 게임 순서
	static int Ground; // 그라운드 상황(비트마스킹)
	static int OutCount; // 아웃 수

	static int RandNum; // 난수값
	static Queue<Integer> Hits, Runs; // Hit, Run 선수 목록

	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	private static class Player {
		String name; // 이름
		float hits; // 타율
		float sacs; // 희생률
		boolean onGround; // 그라운드 여부

		public Player(String name, float hits, float sacs) {
			this.name = name;
			this.hits = hits;
			this.sacs = sacs;
			onGround = false;
		}

	}

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException { // 초기화 및 입력
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int games = Integer.parseInt(read.readLine());
		GameCount = 0;
		while (games-- > 0) {
			Teams = new String[2];
			HitterInfos = new Player[2][9];
			HitterIndex = new int[2];
			Score = new int[2];
			TeamHistory = new int[2][2];

			RandNum = 1;
			Hits = new LinkedList<>();
			Runs = new LinkedList<>();

			Teams[0] = read.readLine();
			for (int h = 0; h < 9; h++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				HitterInfos[0][h] = new Player(tokens.nextToken(), Float.parseFloat(tokens.nextToken()),
						Float.parseFloat(tokens.nextToken()));
			}
			Teams[1] = read.readLine();
			for (int v = 0; v < 9; v++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				HitterInfos[1][v] = new Player(tokens.nextToken(), Float.parseFloat(tokens.nextToken()),
						Float.parseFloat(tokens.nextToken()));
			}
			setGame();
			if (games > 0) {
				write.write("============================================================\n");
			}
		}
		write.close();
		read.close();
	}

	private static void setGame() throws IOException {
		GameCount++; // 게임 번호
		int inning = 1; // 이닝
		write.write(String.format("Game %d: %s vs. %s\n\n", GameCount, Teams[0], Teams[1]));
		while (inning <= 200) {
			setTeamHit(0); // 원정팀 공격
			if (inning == 9 && Score[0] < Score[1]) { // 9회 말인데 홈팀이 이기는 점수
				writeGameInfo(inning); // Hit, Run 기록
				break; // 그대로 게임 끝
			}
			setTeamHit(1); // 홈팀 공격
			writeGameInfo(inning); // Hit, Run 기록
			if (inning >= 9 && Score[0] != Score[1]) { // 9회 이상에서 우승 판별 났을 때
				break;
			}
			inning++;
		}
		write.write("End of Game:\n");
		write.write(String.format("  %15s %d runs, %d hits\n", Teams[0], TeamHistory[0][0], TeamHistory[0][1]));
		write.write(String.format("  %15s %d runs, %d hits\n", Teams[1], TeamHistory[1][0], TeamHistory[1][1]));
	}

	private static void writeGameInfo(int inning) throws IOException {
		write.write(String.format("Inning %d:\n", inning));
		write.write("Hits:\n");
		if (Hits.isEmpty()) {
			write.write("  none\n");
		} else {
			while (!Hits.isEmpty()) { // 해당 이닝에서 Hit 선수들
				int hitPlayer = Hits.poll();
				write.write(String.format("  %15s %15s\n", HitterInfos[hitPlayer / 10][hitPlayer % 10].name,
						Teams[hitPlayer / 10]));
			}
		}
		write.write("\n");
		write.write("Runs:\n");
		if (Runs.isEmpty()) {
			write.write("  none\n");
		} else {
			while (!Runs.isEmpty()) { // 해당 이닝에서 Run 선수들
				int runPlayer = Runs.poll();
				write.write(String.format("  %15s %15s\n", HitterInfos[runPlayer / 10][runPlayer % 10].name,
						Teams[runPlayer / 10]));
			}
		}
		write.write("\n");
	}

	private static void setTeamHit(int team) {
		OutCount = 0; // 아웃 수
		Ground = 0; // 그라운드 상황(비트마스킹)
		ArrayList<Integer> tempHits = new ArrayList<>(); // 현재 이닝에서 현재 팀 선수 중 Hit
		Queue<Integer> tempRuns = new LinkedList<>(); // '' 중 Run
		int homeInIndex = 0; // 쳐야하는 선수 번호 (0~8)
		while (OutCount < 3) {
			int hitterIndex = HitterIndex[team]; // 등판 선수 번호
			HitterIndex[team] = (HitterIndex[team] + 1) % 9;
			Player thisHitter = HitterInfos[team][hitterIndex]; // 해당 선수 정보
			if (thisHitter.onGround) // 그라운드에 나가있는 선수면 패스
				continue;
			if (isAbleSac()) { // 희생 가능 상황
				if (isSuccessed(thisHitter.sacs)) { // 희생 성공
					Ground = Ground << 1; // 1루씩 진출
				}
				OutCount++; // 희상은 아웃
			} else { // 안타 시도
				if (isSuccessed(thisHitter.hits)) { // 안타 성공
					Ground = (Ground << 1) + 1; // 1루씩 진출 및 1루 진출
					thisHitter.onGround = true; // 현재 선수 그라운드 처리
					tempHits.add(hitterIndex); // 현재 이닝의 현재 팀 Hit 기록
				} else { // 실패
					OutCount++; // 아웃
				}
			}

			if ((Ground & 8) != 0) { // 4루 진출 선수 홈으로 들어감
				Ground &= 7; // 4루 값 없애기
				Score[team]++; // 해당 팀 점수 +1
				thisHitter.onGround = false; // 홈으로 들어간 선수 그라운드 비처리
				tempRuns.add(tempHits.get(homeInIndex++)); // 득점 선수 기록
			}

		}
		TeamHistory[team][0] += tempRuns.size(); // 해당 팀 run 수 기록
		TeamHistory[team][1] += tempHits.size(); // 해당 팀 hit 수 기록
		for (Integer h : tempHits) { // 현재 이닝의 hit에 추가
			Hits.add(team * 10 + h);
		}
		while (!tempRuns.isEmpty()) { // 현재 이닝의 run에 추가
			Runs.add(team * 10 + tempRuns.poll());
		}
		for (int i = 0; i < 9; i++) { // 모든 선수 ground 비처리
			HitterInfos[team][i].onGround = false;
		}
	}

	private static boolean isAbleSac() { // 희생 가능 상황인가
		return (((Ground & 2) != 0) && OutCount == 0) || ((Ground & 4) != 0 && (OutCount <= 1));
	}

	private static int getNextRandNum() { // 난수값 갱신
		return RandNum = (RandNum * 25173 + 13849) % 65536;
	}

	private static boolean isSuccessed(float percentage) { // 희생/안타 결과
		return ((float) getNextRandNum()) / 65536f <= percentage;
	}

}