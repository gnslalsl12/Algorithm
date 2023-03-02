package LGUPLUS;

public class prob4 {
	static int P;
	static int Totalcount;
	static int[] BoatMemory;

	public static void main(String[] args) {
		int n = 2;
		int m = 2;
		int p = 2;
		System.out.println(solution(n, m, p));
	}

	public static int solution(int n, int m, int p) {
		int answer = 0;
		P = p;
		BoatMemory = new int[] { 0, 0 };
		Totalcount = Integer.MAX_VALUE;
		setBoat(true, 1, n, m, 0, 0);
		answer = Totalcount == Integer.MAX_VALUE ? -1 : Totalcount;
		return answer;
	}

	private static void setBoat(boolean fromA, int count, int OriginAN, int OriginAM, int OriginBN, int OriginBM) {
		int Ncount = fromA ? OriginAN : OriginBN;
		int Mcount = fromA ? OriginAM : OriginBM;
		if (Totalcount < count)
			return;
		int startN = 0;
		int endN = Ncount;
		int plusN = 1;
		if (fromA) {
			startN = Ncount;
			endN = 0;
			plusN = -1;
		}
		for (int normal = startN; fromA ? normal >= endN : normal <= endN; normal += plusN) {
			for (int murd = Mcount; murd >= 0; murd--) {
				if (normal + murd > P || normal + murd == 0)
					continue;
				if (normal >= 1) {
					if (murd > normal)
						continue;
				}
				if (BoatMemory[0] == normal && BoatMemory[1] == murd)
					continue;
				BoatMemory[0] = normal;
				BoatMemory[1] = murd;
				if (fromA) {
					if (OriginAN - normal != 0 && OriginAN - normal < OriginAM - murd)
						continue;
					if (OriginBN + normal != 0 && OriginBN + normal < OriginBM + murd)
						continue;
					if (OriginAN - normal == 0 && OriginAM - murd == 0) {
						Totalcount = Math.min(Totalcount, count);
						return;
					}
					setBoat(false, count + 1, OriginAN - normal, OriginAM - murd, OriginBN + normal, OriginBM + murd);
				} else {
					if (OriginBN - normal != 0 && OriginBN - normal < OriginBM - murd)
						continue;
					if (OriginAN + normal != 0 && OriginAN + normal < OriginAM + murd)
						continue;
					setBoat(true, count + 1, OriginAN + normal, OriginAM + murd, OriginBN - normal, OriginBM - murd);
				}
			}
		}
	}

}
