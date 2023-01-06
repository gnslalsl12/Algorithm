package day0811;

public class CompleteBinaryTreeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Online_BFS tree = new Online_BFS(9);
		for(int i = 0; i < 9 ; i++) {
			tree.add((char)('A'+i));		//node에 A+i 추가됨 (char)
		}

		tree.bfs();
		System.out.println();
		tree.dfs();
		System.out.println();
		tree.bfs2();
		System.out.println();
		tree.dfsByPreOrder(1);	//위부터 그냥 평범한 dfs [나를 우선처리]
		System.out.println();
		tree.dfsByInOrder(1);	// [나를 중간에 처리] 왼쪽 맨 밑 노드부터 (내 밑의 왼쪽에서 오른쪽으로 넘어갈 때 나를 출력)
		System.out.println();
		tree.dfsByPostOrder(1);	//왼쪾 맨 밑 층부터 [나를 마지막에 처리]
	}

}
