package day1009;

public class UnionFindtest {
	static int[] parent;

	/*parent[i]의 값이 음수면 i가 부모이고
	 * 해당 값의 절대값이 밑에 가진 자식의 수
	 * 양수면 그 값이 부모를 가리킴
	*/
	public static void main(String[] args) {

		parent = new int[10];
		for (int i = 0; i < 10; i++) {
			parent[i] = -1;
		}

	}
	
	private static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return;
		parent[x] = y;
	}
	
	

	private static int Weightedfind(int x) {
		if (parent[x] < 0) {
			return x;
		}
		else{
			int y = find(parent[x]);
			parent[x] = y;
			return y;
		}
	}
	
	private static void Weightedunion(int x, int y) {
		x = Weightedfind(x);
		y = Weightedfind(y);
		
		if(x == y) {	//이미 같은 조직
			return;
		}
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}

}
