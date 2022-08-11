package Online;

public class SsafyStack<E>  implements IStack<E>{

	private Node<E> top;
	
	
	@Override
	public void push(E data) {		// 첫 노드 삽입
		top = new Node<E>(data, top);	//추가되는 노드의 데이터값 =>data, 참조값 : top(top이 가리키던 두번째 주소값을 지칭한다는 뜻)
	} 									//=> 헤드에 물려있던 주소값을 삽ㅇ비하는 데이터의 link에 넣고
										//헤드가 가리키는 주소값을 나로 지칭함

	@Override
	public E pop() {
		if(isEmpty()) {
			System.out.println("공백스텍임 => 불가능");
			return null;
		}
		//비어있지 않음 => 맨 위 top을 꺼냄
		Node<E> popNode = top; //꺼내려는 노드popNode 생성
		top = popNode.link;		//top 다음에 있던 노드를 top에 대입
		popNode.link = null;	//꺼내려는 노드의 link값 삭제
		return popNode.data;
	}

	@Override
	public E peek() {
		if(isEmpty()) {
			System.out.println("공백스텍임 => 불가능");
			return null;
		}
		return top.data; 		//빈 값이 아니므로 peek 수행(맨 위 데이터 꺼내보기)
	}

	@Override
	public boolean isEmpty() {

		return top == null;
	}

	@Override
	public int size() {	//사이즈 = node 개수 (처음부터 끝까지 탐색)
		int cnt = 0;
		for(Node<E> temp = top; temp!=null; temp = temp.link /*temp가 가리키던 참조값(link)을 temp data로 넣음*/) {
			++cnt;
		}
		return cnt;
	}

}
