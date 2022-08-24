package day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BDFSNodes {
	String readinput;
	int nextL;
	int nextR;
	
	
	
	public BDFSNodes() {
		super();
	}



	public BDFSNodes(String in, int nextL, int nextR) {
		super();
		this.readinput = in;
		this.nextL = nextL;
		this.nextR = nextR;
	}
	public BDFSNodes(String in) {
		this.nextL = 0;
		this.nextR = 0;
		this.readinput = in;
	}
	public BDFSNodes(String in, int nextL) {
		this.nextL = nextL;
		this.nextR = 0;
		this.readinput = in;
	}
	
	
	
	
}
public class SWEA_사칙연산유효성검사 {

	static int totalresult;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		for(int test = 1; test <= 10; test++) {
			
			int N = Integer.parseInt(read.readLine());
			
			Queue<Integer> Qs = new LinkedList<>();
			ArrayList<BDFSNodes> nodelist = new ArrayList<>();
			nodelist.add(new BDFSNodes());
			for(int i = 1; i <= N; i ++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				tokens.nextToken();
				if(tokens.countTokens() == 3) {					
					nodelist.add(new BDFSNodes(tokens.nextToken(), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
				}else if(tokens.countTokens() == 2){
					nodelist.add(new BDFSNodes(tokens.nextToken(), Integer.parseInt(tokens.nextToken())));
				}else {
					nodelist.add(new BDFSNodes(tokens.nextToken()));
				}
				
			}
			
			Qs.offer(1);

			while(!Qs.isEmpty()) {
				int index = Qs.poll();
				if (index == N) {
					totalresult = 1;
					break;
				}
				BDFSNodes tempnode = nodelist.get(index);
//				System.out.println("지금 들어온 readinput : " + tempnode.readinput.toString());
				if(tempnode.readinput.equals("*") || tempnode.readinput.equals("/") || tempnode.readinput.equals("-") || tempnode.readinput.equals("+") ) {
					if(tempnode.nextL > 0 || tempnode.nextR>0) {//연산 다음 값이 둘 다있을 때
						Qs.add(tempnode.nextL);
						Qs.add(tempnode.nextR);
						continue;
					}
					else {
//						System.out.println("계산값인데 다음에 숫자가 두 개 안옴 : " + index);
						totalresult =  0;
						break;
					}
				}else { //숫자일 때 (다음에 값이 나오면 안댐
					if(tempnode.nextL > 0 || tempnode.nextR > 0) {
//						System.out.println("숫자인데 다음에 또 뭔가가 옴 : " + index);
						totalresult = 0;
						break;
					}
				}
				
			}
			System.out.println("#" + test + " " + totalresult);
		}
		
		
		
	}

}
