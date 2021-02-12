package A2021_02_12;

import java.util.*;
import java.io.*;
/**
 * 
 * @author beaverbae
 * @see https://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder/
 *
 */

public class BOJ_2263_트리의_순회_solution {
	static int N;
	static int inOrder[];
	static int postOrder[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		inOrder = new int[N];
		postOrder = new int[N];
		BinaryTree tree = new BinaryTree();
		for (int i = 0; i < N; i++) { // l -> 본 -> r
			inOrder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken()); // l -> r -> 본
		}

		Node root = tree.build(0, N - 1, 0, N - 1);
		tree.preOrder(root);
		System.out.println(sb);
	}

	static class BinaryTree {
		Node build(int instart, int inend, int poststart, int postend) {
			if (instart > inend) {
				return null;
			}
			Node node = new Node(postOrder[postend]); // 처음 루트
			if (instart == inend) {
				return node;
			}
			int iIndex = search(instart, inend, node.data);

			node.left = build(instart, iIndex - 1, poststart, poststart - instart + iIndex - 1);
			node.right = build(iIndex + 1, inend, postend - inend + iIndex, postend - 1);

			return node;
		}

		private int search(int start, int end, int data) {
			int i;
			for (i = start; i <= end; i++) {
				if (inOrder[i] == data)
					break;
			}
			return i;
		}

		void preOrder(Node node) {
			if (node == null)
				return;
			sb.append(node.data + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	static class Node {
		Node left;
		Node right;
		int data;

		public Node(int data) {
			this.data = data;
			left = right = null;
		}
	}
}