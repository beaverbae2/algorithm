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

		MyDoublyLinkedList<Integer> list = new MyDoublyLinkedList<>();
		list.add(list.size(), 9);
		list.add(0, 7);
		list.addFirst(5);
		System.out.println(list);
		System.out.println(list.removeLast());
		System.out.println(list.removeLast());
		System.out.println(list.removeLast());
		System.out.println(list);
		list.add(list.size(), 9);
		list.add(0, 7);
		list.addFirst(5);
		System.out.println(list);
		System.out.println(list.remove(list.size() - 1));
		list.addFirst(3);
		System.out.println(list);
		System.out.println(list.removeFirst());
		list.addLast(13);
		System.out.println(list);
		System.out.println(list.remove(1));
		System.out.println(list);

	}
}
