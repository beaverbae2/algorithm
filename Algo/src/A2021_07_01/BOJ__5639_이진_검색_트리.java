package A2021_07_01;

import java.util.*;
import java.io.*;

/**
 * Binary Search Tree
 * 25MIN
 * 
 * 어려웠던 부분
 * - 이진 탐색 트리를 만들어서 전위 순회한 대로 삽입하면 해결 -> 이후 후위 순회 해주면 끝
 * - 참조츼 참조 변경 문제 : 참조변수1이 있고, 참조변수1을 참조하는 참조변수2가 있을 떄, 참조변수2의 참조를 바꾸는것은 참조변수1에 영향X
 * 
 * 
 * @author beaverbae
 *
 */

public class BOJ__5639_이진_검색_트리 {
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		Tree tree = new Tree();
		sb = new StringBuilder();
		
		while ((str = br.readLine()) != null) {
			tree.insert(Integer.parseInt(str));
		}
		
		postOrder(tree.root);
		System.out.println(sb.toString());
	}
	
	public static void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			sb.append(node.v).append("\n");
		}
	}
	
	static class Tree {
		Node root;
		
		public void insert(int v) {
			Node newNode = new Node(v);
			
			Node node = root;
			
			if (root == null) {
				root = newNode;
			} else {
				while (true) {
					if (node.v > newNode.v) {
						if (node.left == null) {
							node.left = newNode;
							break;
						} else {
							node = node.left;
						}
					} else {
						if (node.right == null) {
							node.right = newNode;
							break;
						} else {
							node = node.right;
						}
					}
				}
			}
		}
	}
	
	static class Node {
		int v;
		Node left, right;
		
		public Node(int v) {
			this.v = v;
		}
	}
}
