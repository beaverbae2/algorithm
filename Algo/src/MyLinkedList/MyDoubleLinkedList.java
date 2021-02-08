package MyLinkedList;

/**
 * 
 * @author beave
 * @see https://opentutorials.org/module/1335/8941
 * 
 */

public class MyDoubleLinkedList<T> {
	private Node head;
	private Node tail;
	private int size;
	
	class Node {
		T data;
		Node next;
		Node prev;
		
		Node(T data){
			this.data = data;
		}

		@Override
		public String toString() {
			return String.valueOf(this.data);
		}
	}
	
	public void addFirst(T data) {
		Node node = new Node(data);
		
		node.next = head;
		if (head != null) {
			head.prev = node;
		}
		head = node;
		size++;
	
		if (head.next == null) {
			tail = head;
		}
	}
	
	public void addLast(T data) {
		if (isEmpty()) {
			addFirst(data);
		}else {
			Node node = new Node(data);
			tail.next = node;
			node.prev = tail;
			tail = node;
			size++;
		}
	}
	
	public void add(int k, T data) {
		if (k == 0) {
			addFirst(data);
		}else if (k == size -1) {
			addLast(data);
		}else {
			Node node = new Node(data);
			
			Node x = get(k-1);
			Node next_x = x.next;
			
			x.next = node;
			node.prev = x;
			
			node.next = next_x;
			next_x.prev = node;
			size++;
		}
	}
	
	public T removeFirst() {
		if (isEmpty()) {
			return null;
		}else {
			Node node = head;
			Node next_node = node.next;
			T returnData = node.data;
			
			node.next = null;
			head = next_node;
			if (head !=null) {
				head.prev = null;
			}
			size--;
			
			return returnData;
		}
	}
	
	public T removeLast() {
		if (isEmpty()) {
			return null;
		}else {
			Node node = tail;
			Node prev_node = tail.prev;
			T returnData = node.data;
			
			node.prev = null;
			prev_node.next = null;
			tail = prev_node;
			size--;
			
			return returnData;
		}
	}
	
	public T remove(int k) {
		if (k == 0) {
			return removeFirst();
		}else if (k == size-1) {
			return removeLast();
		}else {
			Node prev = get(k-1);
			Node delete_x = prev.next;
			Node next = delete_x.next;
			T returnData = delete_x.data;
			prev.next = next;
			next.prev = prev;
			
			return returnData;
		}
	}
	
	public Node get(int k) {
		if (k < size/2) {
			Node node = head;
			
			for (int i = 0; i < k; i++) {
				node = node.next;
			}
			
			return node;
		}else {
			Node node = tail;
			
			for (int i = 0; i < size-k-1; i++) {
				node = node.prev;
			}
			
			return node;
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}else {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			Node node = head;
			
			while(node.next != null) {
				sb.append(node.data).append(",").append(" ");
				node = node.next;
			}
			sb.append(node.data).append("]");
			return sb.toString();
		}
	}
	
	
}
