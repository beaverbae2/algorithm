package A2021_03_17;

import java.util.*;
import java.io.*;

/**
 * Binary Tree
 * 22MIN
 * @author beaverbae
 *
 */

public class BOJ_5639_이진_검색_트리 {
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		BinaryTree tree = new BinaryTree();
		while ((input = br.readLine()) != null) {
			int key = Integer.parseInt(input);
			tree.insert(key);
		}
		
		sb = new StringBuilder();
		postOrder(tree.root);
		System.out.println(sb.toString());
	}
	
	private static void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			sb.append(node.key).append("\n");
		}
	}
	
	
	static class Node {
		int key;
		Node left;
		Node right;
		
		Node(int key){
			this.key = key;
		}
	}
	
	static class BinaryTree {
		Node root;
		
		BinaryTree() {}
		
		public void insert(int key) {
			if (root == null) { 
				root = new Node(key);
			} else {
				Node node = root;
				
				while (true) {
					if (key < node.key) {
						if (node.left == null) {
							node.left = new Node(key);
							break;
						} else {
							node = node.left;
						}
					} else {
						if (node.right == null) {
							node.right = new Node(key);
							break;
						} else {
							node = node.right;
						}
					}
				}
			}
		}
	}
}
