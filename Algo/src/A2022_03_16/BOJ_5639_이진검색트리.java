package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree(BST)
 * @author beaverbae
 * - 전위순회한 대로 BST를 구성하면 된다
 */

public class BOJ_5639_이진검색트리 {
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		BST tree = new BST();
		while ((s = br.readLine()) != null) {
			if (s.length() == 0) break;
			int v = Integer.parseInt(s);
			tree.add(v);
		}
		
		sb = new StringBuilder();
		post(tree.root);
		System.out.println(sb.toString());
	}
	
	private static void post(Node node) {
		if (node.left != null) post(node.left);
		if (node.right != null) post(node.right);
		sb.append(node.v).append("\n");
	}
	
	static class Node {
		int v;
		Node left, right;
		
		Node (int v) {
			this.v = v;
		}
	}
	
	static class BST {
		Node root;
		
		void add (int v) {
			Node node = root;
			
			if (node == null) root = new Node(v);
			else {
				while (true) {
					if (v < node.v) {
						if (node.left == null) {
							node.left = new Node(v);
							break;
						} else node = node.left;
					} else {
						if (node.right == null) {
							node.right = new Node(v);
							break;
						} else node = node.right;
					}
				}
			}
		}
	}
}
