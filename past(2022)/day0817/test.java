package day0817;

public class test {

	public static void main(String[] args) {
		int [][] mm = new int [5][5];
		int count = 0;
		for(int i = 0; i < 5 ; i++) {
			for(int j = 0; j < 5; j++) {
				mm[i][j] = count++;
			}
		}
		for(int[] t : mm) {
			for(int tt : t) {
				System.out.printf("%d ",tt);
			}
			System.out.println();
		}
		int [][] temp = new int [5][5];
		System.arraycopy(mm, 0, temp, 0, mm.length);
		for(int[] t : temp) {
			for(int tt : t) {
				System.out.printf("%d ",tt);
			}
			System.out.println();
		}
		
		
	}
}
