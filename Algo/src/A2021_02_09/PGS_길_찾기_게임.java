package A2021_02_09;

import java.util.*;

/**
 * Binary Search Tree
 * 
 * @author beaverbae
 * @see https://velog.io/@agugu95/Java-Binary-Tree-Binary-Search-Tree
 * 
 */

public class PGS_길_찾기_게임 {
	private int idx;

	public int[][] solution(int[][] nodeinfo) {
		List<Node> list = new ArrayList<>();

		for (int i = 0; i < nodeinfo.length; i++) {
			int x = nodeinfo[i][0];
			int y = nodeinfo[i][1];
			int n = i + 1;

			list.add(new Node(x, y, n));
		}

		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.y != o2.y) {
					return Integer.compare(o2.y, o1.y);
				} else {
					return Integer.compare(o1.x, o2.x);
				}
			}
		});

		BinaryTree tree = new BinaryTree();
		for (Node newNode : list) {
			tree.insert(newNode);
		}

		int[][] answer = new int[2][list.size()];

		idx = 0;
		preOrder(answer, tree.root);

		idx = 0;
		postOrder(answer, tree.root);

		return answer;
	}

	public void preOrder(int[][] arr, Node node) {
		if (node != null) {
			arr[0][idx++] = node.n;
			preOrder(arr, node.left);
			preOrder(arr, node.right);
		}
	}

	public void postOrder(int[][] arr, Node node) {
		if (node != null) {
			postOrder(arr, node.left);
			postOrder(arr, node.right);
			arr[1][idx++] = node.n;
		}
	}

	static class BinaryTree {
		Node root;

		void insert(Node newNode) {
			Node node = root;

			if (root == null) {
				root = newNode;
			} else {
				while (true) {
					if (node.x > newNode.x) {
						if (node.left == null) {
							node.left = newNode;
							return;
						}
						node = node.left;
					} else {
						if (node.right == null) {
							node.right = newNode;
							return;
						}
						node = node.right;
					}
				}
			}
		}
	}

	static class Node {
		Node left;
		Node right;
		int x, y, n;

		Node(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}

	public static void main(String[] args) {
		new PGS_길_찾기_게임().solution(new int[][] { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 },
				{ 7, 2 }, { 2, 2 } });
	}
}
