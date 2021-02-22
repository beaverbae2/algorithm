package A2021_02_22;

import java.util.*;

/**
 * BST
 * @author beaverbae
 *
 */

public class PGS_길_찾기_게임_review1 {

	static List<Integer> list1 = new ArrayList<>();
	static List<Integer> list2 = new ArrayList<>();

	public int[][] solution(int[][] nodeinfo) {
		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < nodeinfo.length; i++) {
			list.add(new Pair(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
		}

		// 이진 트리에 입력하기 위해 정렬
		Collections.sort(list, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.y != o2.y) {// y좌표
					return Integer.compare(o2.y, o1.y);// 내림차순
				} else {// x좌표
					return Integer.compare(o1.x, o2.x);// 오름차순
				}
			}
		});

		BinaryTree tree = new BinaryTree();
		for (int i = 0; i < list.size(); i++) {
			Pair p = list.get(i);
			tree.insert(p.n, p.x);
		}

		tree.preOrder(tree.root);
		tree.postOrder(tree.root);

		int[][] answer = new int[2][nodeinfo.length];
		for (int i = 0; i < nodeinfo.length; i++) {
			answer[0][i] = list1.get(i);
			answer[1][i] = list2.get(i);
		}

		return answer;
	}

	static class Pair {
		int n, x, y;

		public Pair(int n, int x, int y) {
			this.n = n;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [n=" + n + ", x=" + x + ", y=" + y + "]";
		}
	}

	static class BinaryTree {
		Node root;

		static class Node {
			int n, x = -1;
			Node left, right;

		}

		public BinaryTree() {
			root = new Node();
		}

		public void insert(int n, int x) {
			Node node = root;

			while (true) {
				if (node.x == -1) {
					node.x = x;
					node.n = n;
					break;
				}

				if (x < node.x) {
					if (node.left == null) {
						node.left = new Node();
					}
					node = node.left;
				} else {
					if (node.right == null) {
						node.right = new Node();
					}
					node = node.right;
				}
			}
		}

		public void preOrder(Node node) {
			if (node != null && node.n != 0) {
				list1.add(node.n);
				preOrder(node.left);
				preOrder(node.right);
			}
		}

		public void postOrder(Node node) {
			if (node != null && node.n != 0) {
				postOrder(node.left);
				postOrder(node.right);
				list2.add(node.n);
			}
		}
	}

}
