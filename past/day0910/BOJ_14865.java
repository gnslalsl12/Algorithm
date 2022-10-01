package day0910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14865 {
	static int N;
	static ArrayList<dirXY> Square = new ArrayList<>();
	static ArrayList<int[]> BottomPointMatch = new ArrayList<>(); // 만들어진 사각형의 뿌리 짝
	static int DontInclude, IsntIncluded;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		ArrayList<dirXY> Points = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			Points.add(new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}

		dirXY Firstone = Points.remove(0);
		DFStoMakeSquare(Firstone, Points, new ArrayList<dirXY>());
		// 사각형 완료
		for (int i = 0; i < Square.size(); i++) {
			if (Square.get(i).y < 0) { // y가 마이너스네? 얘랑 연결된 애 중에 x는 같고 y가 양수인 애가 있나?
				if (i > 0) {
					if (Square.get(i).x == Square.get(i - 1).x && Square.get(i - 1).y > 0) {
						Square.get(i).sety(0);
						i = 0;
						continue;
					}
				} else if (i == 0) {
					if (Square.get(i).x == Square.get(Square.size() - 1).x && Square.get(Square.size() - 1).y > 0) {
						Square.get(i).sety(0);
						;
						i = 0;
						continue;
					}
				}
				if (i < Square.size() - 1) {
					if (Square.get(i).x == Square.get(i + 1).x && Square.get(i + 1).y > 0) {
						Square.get(i).sety(0);
						i = 0;
						continue;
					}
				} else if (i == Square.size() - 1) {
					if (Square.get(i).x == Square.get(0).x && Square.get(0).y > 0) {
						Square.get(i).sety(0);
						i = 0;
						continue;
					}
				}

				Square.remove(i);// 연결된 애 중에 구제해 줄 애가 없네?
				i = 0;
				continue;
			}
		}
		// y < 0이 남지 않았음
		while (Square.size() != 0) {
			dirXY temp = Square.remove(0);
			int RootA = 0;
			int RootB = 0;
			if (temp.y != 0) { // 공중에 떠있는 중간값 =>왼쪽 오른쪾 둘 다 찾아봐야함
				while (true) {
					if (Square.get(0).y != 0) {
						Square.remove(0);
						continue;
					} else { // 뿌리 하나 발견
						RootA = Square.remove(0).x;
						break;
					}
				}
				while (true) {
					if (Square.get(Square.size() - 1).y != 0) {
						Square.remove(Square.size() - 1);
						continue;
					} else { // 뿌리 두뻔쨰 발견
						RootB = Square.remove(Square.size() - 1).x;
						break;
					}
				}

			} else if (temp.y == 0) { // 뿌리값 => 다음값도 0이면 왼쪽으로 탐색, 아니면 오른쪽으로 탐색
				RootA = temp.x;
				if (Square.get(0).y == 0) { // 다음값도 0 => 왼쪽
					while (true) {
						if (Square.get(Square.size() - 1).y != 0) {
							Square.remove(Square.size() - 1);
							continue;
						} else {
							RootB = Square.remove(Square.size() - 1).x;
							break;
						}
					}
				} else { // 다음값이 0 아님 => 오른쪽으로 탐색
					while (true) {
						if (Square.get(0).y != 0) {
							Square.remove(0);
							continue;
						} else {
							RootB = Square.remove(0).x;
							break;
						}
					}
				}
			}
			BottomPointMatch.add(new int[] { Integer.min(RootA, RootB), Integer.max(RootA, RootB) });
			if (Square.size() == 0)
				break;
		}
		CountInclude();

		System.out.println(IsntIncluded + " " + DontInclude);

	}

	private static void CountInclude() { // 포함되지 않는 큰 애들 세기
		for (int i = 0; i < BottomPointMatch.size(); i++) {
			boolean IsNotIncluded = true; // 나는 아무에게도 포함되지 않아요
			boolean DoNotInclude = true; // 나는 누구도 포함하지 않아요
			int Minx = BottomPointMatch.get(i)[0];
			int Maxx = BottomPointMatch.get(i)[1];
			for (int j = 0; j < BottomPointMatch.size(); j++) { // 내가 포함되지 않으면 count
				if (i == j)
					continue;
				int tempMinx = BottomPointMatch.get(j)[0];
				int tempMaxx = BottomPointMatch.get(j)[1];

				if (tempMinx < Minx && Maxx < tempMaxx) { // 나를 포함하는 애가 있어
					IsNotIncluded = false;
				}
				if (Minx < tempMinx && tempMaxx < Maxx) { // 내가 누군가를 포함해
					DoNotInclude = false;
				}

			}
			if (IsNotIncluded)
				IsntIncluded++;
			if (DoNotInclude)
				DontInclude++;
		}

	}

	private static void DFStoMakeSquare(dirXY startpoint, ArrayList<dirXY> input, ArrayList<dirXY> MakingSquare) {
		if (Square.size() != 0)
			return; // 다른 경우에서 이미 Square를 완성했으면 끝내기
		if (input.size() == 1) {
			if (Square.size() != 0)
				return;
			MakingSquare.add(startpoint);
			if (startpoint.x != input.get(0).x && startpoint.y != input.get(0).y)
				return;
			MakingSquare.add(input.get(0));
			Square = MakingSquare;
			return;
		}
		MakingSquare.add(startpoint);
		for (int i = 0; i < input.size(); i++) {
			if (startpoint.equals(input.get(i)))
				continue;
			if (startpoint.x == input.get(i).x || startpoint.y == input.get(i).y) {
				dirXY nextpoint = input.remove(i);
				DFStoMakeSquare(nextpoint, input, MakingSquare);
				input.add(nextpoint);
			}
		}
		return; // 찾아진 게 없으면 빼기
	}

	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public void sety(int y) {
			this.y = y;
		}

	}

}
