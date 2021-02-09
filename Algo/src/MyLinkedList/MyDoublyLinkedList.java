package MyLinkedList;

/**
 * 
 * @author beave
 * @see https://opentutorials.org/module/1335/8941
 * 
 */

public class MyDoublyLinkedList<T> {
   private Node head;
   private Node tail;
   private int size;
   
   class Node {
      T data;
      Node prev;
      Node next;
      
      public Node(T data) {
         this.data = data;
      }

      @Override
      public String toString() {
         return String.valueOf(data);
      }
   }
   
   public void addFirst(T data) {
      Node newNode = new Node(data);
      newNode.next = head;
      
      if (head != null) {// 첫 생성시
         head.prev = newNode;
      }
      
      head = newNode;
      
      if (head.next == null) {// 첫 생성시
         tail = head;
      }
      
      size++;
   }
   
   public void addLast(T data) {
      if (isEmpty()) {// 첫 생성시
         addFirst(data);
      } else {
         Node newNode = new Node(data);
         tail.next = newNode;
         newNode.prev = tail;
         
         tail = newNode;
         size++;
      }
   }
   
   public void add(int idx, T data) {
      if (idx > size) {// 예외 조건
         return; 
      } else if (isEmpty() || idx == 0) {
         addFirst(data);
      } else if (idx == size){
         addLast(data);
      } else {
         Node x = get(idx-1);
         Node next_x = x.next;
         
         Node newNode = new Node(data);
         x.next = newNode;
         newNode.prev = x;
         
         newNode.next = next_x;
         next_x.prev = newNode;
         size++;
      }
   }
   
   public T removeFirst() {
      if (isEmpty()) {
         return null;
      }else {
         Node x = head;
         Node next_x = head.next;
         T returnData = x.data;
         
         x = null;
         head = next_x;
         
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
      } else if (size == 1) { 
         return removeFirst();
      } else {
         Node x = tail;
         Node prev_x = tail.prev;
         T returnData = x.data;
         
         x = null;
         tail = prev_x;
         tail.next = null;
         
         size--;
         
         return returnData;
      }
   }
   
   public T remove(int idx) {
      if (isEmpty() || idx >= size) {
         return null;
      } else if (idx == 0) { 
         return removeFirst();
      } else if (idx == size -1) {
         return removeLast();
      } else {
         Node x = get(idx);
         Node prev_x = x.prev;
         Node next_x = x.next;
         T returnData = x.data;
         
         prev_x.next = next_x;
         next_x.prev = prev_x;
         
         x = null;
         
         size--;
         
         return returnData;
      }
   }
   
   public Node get(int idx) {
      if (idx < size/2) {
         Node node = head;
         for (int i = 0; i < idx; i++) {
            node = node.next;
         }
         return node;
      } else {
         Node node = tail;
         for (int i = 0; i < size-1-idx; i++) {
            node = node.prev;
         }
         return node;
      }
   }
   
   public int size() {
      return size;
   }
   
   public boolean isEmpty() {
      return size == 0;
   }

   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      } else {
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
