package A2021_03_30;

import java.util.*;

/**
 * BST
 * 43MIN
 * 오래 걸린 이유 : 트리 구성시 NULL 처리, 문제 해석 오류, answer배열 idx 처리 
 * @author beaverbae
 * 
 */

public class PGS_길_찾기_게임 {
	int[][] answer;
	ArrayList<int[]> list = new ArrayList<>();
	int idx;
	
	public int[][] solution(int[][] nodeinfo) {
		answer = new int[2][nodeinfo.length];
		for (int i = 0; i < nodeinfo.length; i++) {
			list.add(new int[] {i+1, nodeinfo[i][0], nodeinfo[i][1]});
		}
		
		Collections.sort(list, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] != o2[2]) {
					return Integer.compare(o2[2], o1[2]);
				}
				
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		BinaryTree tree = new BinaryTree();
		for (int i = 0; i < list.size(); i++) {
			int[] arr = list.get(i);
			tree.insert(arr[0], arr[1]);
		}
		
		
		preOrder(tree.root);
		idx = 0;
		postOrder(tree.root);
		
		return answer;
	}
	
	public void preOrder(Node node) {
		if (node != null) {
			answer[0][idx++] = node.v;
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			answer[1][idx++] = node.v;
		}
	}
	
	static class BinaryTree {
		Node root;
		
		public void insert(int v, int x) {
			if (root == null) {
				root = new Node(v, x);
			} else {
				Node node = root;
				
				while (true) {
					if (x < node.x) {
						if (node.left == null) {
							node.left = new Node(v, x);
							break;
						} else {
							node = node.left;
						}
					} else if (x > node.x) {
						if (node.right == null) {
							node.right = new Node(v, x);
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
		Node left, right;
		int v, x;
		
		public Node(int v, int x) {
			this.x = x;
			this.v = v;
		}
	}

	public static void main(String[] args) throws Exception {
		new PGS_길_찾기_게임().solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
	}
}
