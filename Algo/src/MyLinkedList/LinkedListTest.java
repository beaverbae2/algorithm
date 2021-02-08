package MyLinkedList;

public class LinkedListTest {
	public static void main(String[] args) {
//		MyLinkedList<Integer> list = new MyLinkedList<>();
//		list.addLast(10);
//		list.addLast(20);
//		list.addFirst(5);
//		list.add(1, 7);
//		System.out.println(list);
//		System.out.println(list.removeFirst());
//		list.add(1, 8);
//		System.out.println(list.remove(2));
//		System.out.println(list.remove(2));
//		System.out.println(list);
		
		MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<>();
		list.addFirst(3);
		list.addFirst(2);
		list.addFirst(1);
		list.addLast(4);
		list.addLast(5);
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
		System.out.println(list.removeLast());
		System.out.println(list.removeLast());
		System.out.println(list);
		list.addLast(4);
		list.addLast(5);
		list.addFirst(2);
		list.addFirst(1);
		System.out.println(list);
		System.out.println(list.get(1));
		System.out.println(list.get(3));
		System.out.println(list.get(list.size()-1));
		
		list.add(1, 10);
		System.out.println(list);
		System.out.println(list.remove(1));
		System.out.println(list);
		System.out.println(list.remove(list.size()-1));
		System.out.println(list.remove(list.size()-1));
		System.out.println(list);
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list);
		
	}
}
