package day0811;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Online_BFS {	//완전이진트리 구현할 거임

	private char [] nodes;
	private int lastIndex; //마지막 노드의 인덱스
	private final int SIZE;
	
	public Online_BFS(int sIZE) {
		super();
		SIZE = sIZE;
		nodes = new char[sIZE + 1]; //1인덱스부터 쓸 거임
	}
	
	public boolean add(char e) {//완전 이진트리에 맞게 추가
		if(lastIndex == SIZE) {
			return false;
		}
		
		nodes[++lastIndex] = e;
			return true;
	}
	
	public void bfs() {//bfs 구현
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1); //루트노드 인덱스부터 넣기
		while(!queue.isEmpty()) {	//큐에 뭐가 있는동안 계쏙
			int current = queue.poll(); // 다음 방문 대상 정보 꺼내기
			System.out.print(nodes[current] + " ");  //방문해서 해야할 일 처리
			
			//현재 방문노드의 자식노드들을 대기열에 넣기
			if(current*2<=lastIndex) queue.offer(current*2);	//(왼쪽 자식이 있음)	
										//맨 마지막 인덱스가 다음 층 맨 끝 숫자이니까
										//그보다 작으면 내 밑에 노드가 남아있따는 뜻
			if(current*2+1 <= lastIndex) queue.offer(current*2+1);	//(오른쪽 자식이 있음
			
			
		}
		System.out.println();
	}
	
	public void dfs() {//bfs 구현
		
		Stack<Integer> satck = new Stack<>();
		satck.push(1); //루트노드 인덱스부터 넣기
		while(!satck.isEmpty()) {	//큐에 뭐가 있는동안 계쏙
			int current = satck.pop(); // 다음 방문 대상 정보 꺼내기
			System.out.print(nodes[current] + " ");  //방문해서 해야할 일 처리
			
			//현재 방문노드의 자식노드들을 대기열에 넣기
			if(current*2<=lastIndex) satck.push(current*2);	//(왼쪽 자식이 있음)	
										//맨 마지막 인덱스가 다음 층 맨 끝 숫자이니까
										//그보다 작으면 내 밑에 노드가 남아있따는 뜻
			if(current*2+1 <= lastIndex) satck.push(current*2+1);	//(오른쪽 자식이 있음
			
			
		}
		System.out.println();
	}
	

	public void bfs2() {//bfs 트리모양 구현
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1); 
		
		//맨 처음에 1만 들어가서 사이즈 1만 돌면서 2,3 넣어주고,
		//sysout 줄 바꿔주고
		//다음엔 size 2 들어가서 사이즈 2만 돌면서 4,5,6,7 넣어주고
		//...미리 정해둔 
		while(!queue.isEmpty()) {	
			int size = queue.size();	//큐 크기 확인 (== "동일너비의 대상의 개수")
			
			while(--size>=0) {	//반복 작업 전에 구해놓은 큐 크기만큼만 반복
				
				int current = queue.poll(); 
				System.out.print(nodes[current] + " ");
				
				//현재 방문노드의 자식노드들을 대기열에 넣기
				if(current*2<=lastIndex) queue.offer(current*2);	
				if(current*2+1 <= lastIndex) queue.offer(current*2+1);
				
				
			}
			System.out.println(); // 라인 바꾸기용
			
			
			
		}
		System.out.println();
	}
	
	
	
	public void dfsByPreOrder(int current) { //위 메서드를 재귀로 만들어보기
		
		System.out.print(nodes[current] + " ");
		
		if(current*2<=lastIndex) dfsByPreOrder(current*2);
		if(current*2+1 <= lastIndex) dfsByPreOrder(current*2+1);;
		
		//뭐고 개간단하노;;
		
	}
	
	public void dfsByInOrder(int current) {  //기저조건을 다는 메서드로 변경(더 보기 편함)
		if(current>lastIndex) return;
		
		/*if(current*2<=lastIndex) */
		dfsByInOrder(current*2);
		
		System.out.print(nodes[current] + " ");				//얘는 왼쪽 맨 밑에꺼부터 시작함
		
		/*if(current*2+1 <= lastIndex) */
		dfsByInOrder(current*2+1);;
		

		
	}
	public void dfsByPostOrder(int current) {  //기저조건을 다는 메서드로 변경(더 보기 편함)
		if(current>lastIndex) return;
		
		dfsByPostOrder(current*2);
				
		dfsByPostOrder(current*2+1);;
		
		System.out.print(nodes[current] + " ");	//처리를 맨 마지막에 (맨 오른쪽 맨 밑에부터)
		
		
	}

}
