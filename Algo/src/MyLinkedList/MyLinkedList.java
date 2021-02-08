package MyLinkedList;

public class MyLinkedList<T> {
	private Node head;
	private Node tail;
	private int size = 0;
	
	class Node{
		T data;
		Node next;
		
		Node(T data){
			this.data = data;
			this.next = null;
		}

		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	public void addFirst(T data) {
		Node node = new Node(data);// 새로운 노드 생성
		
		node.next = head;// 새로운 노드의 다음 노드로 head 지정
		head = node;// 새로운 노드를 head로 지정
		size++;
		
		if (head.next == null) {
			tail = head;
		}
	}
	
	public void addLast(T data) {
		if (size == 0) {
			addFirst(data);
		}else {
			Node node = new Node(data);// 새로운 노드 생성
			tail.next = node;// tail의 다음을 node로 연결
			tail = node;// tail을 node로 설정
			size++;
		}
	}
	
	public void add(int k, T data) {
		if (size == 0) {
			addFirst(data);
		}else {
			Node x = node(k-1);// k-1번쨰 노드
			Node next_x = x.next;// k번쨰 노드
			
			Node node = new Node(data);// 새로운 노드 생성
			
			x.next = node;// x의 다음 노드로 새로운 노드 지정
			node.next = next_x;// 세로운 노드의 다음으로 next_x 지정
			size++;
			
			// 새로운 노드가 tail이 되는 경우
			if (node.next == null) {
				tail = node;
			}
		}
	}
	
	public T removeFirst() {
		if (!isEmpty()) {
			Node node = head;
			head = node.next;
			
			T returnData = node.data;
			node = null;
			size--;
			return returnData;
		}else {
			return null;
		}
	}
	
	public T remove(int k) {
		if (!isEmpty()) {
			Node node = node(k-1);
			Node deleted_node = node.next;// 삭제 할 노드
			
			// 삭제하는 노드 앞 노드의 다음 노드를 삭제하는 노드 앞 노드로 설정
			node.next = node.next.next;
			
			T returnData = deleted_node.data;
			if(deleted_node == tail) {
				tail = node;
			}
			
			deleted_node = null;
			size--;
			return returnData;
		}else {
			return null;
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	// k번쨰에 위치한 노드를 찾는다
	public Node node(int k) {
		Node x = head;
		
		for (int i = 0; i < k; i++) {
			x = head.next;
		}
		
		return x;
	}

	@Override
	public String toString() {
		if (head == null) {
			return "[]";
		}
		
		Node x = head;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		while(x.next != null) {
			sb.append(x.data).append(",").append(" ");
			x = x.next;
		}
		
		sb.append(x.data).append("]");
		return sb.toString();
	}
	
}
