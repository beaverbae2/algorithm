package A2021_06_07;

import java.util.*;
import java.io.*;

/**
 * 
 * Tree
 * 2H28MIN
 * 어려웠던 이유
 * - 재귀 함수 진행을 잘못 파악 : 순회 -> 재귀 다시 공부
 * - 입력되는 순서가 BST 입력 순서가 아님 -> 즉, 1이 루트가 아닐 수 있음 (가장 헷갈렸던 부분)
 * - 굳이 BST를 만드는 클래스가 필요 없었음
 * 
 * @author beaverbae
 *
 */

public class BOJ_2250_트리의_높이와_너비 {
	static int N;
	static int col;
	
	static int ans_level, ans_breadth;
	static Node[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new Node[N+1];
		boolean[] isChildNode = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			tree[n] = new Node(left, right);
			if (left != -1) isChildNode[left] = true;
			if (right != -1) isChildNode[right] = true;
		}
		
		// 루트 찾기
		int root = 0;
		for (int i = 1; i < isChildNode.length; i++) {
			if (!isChildNode[i]) {
				root = i;
				break;
			}
		}

		inOrder(root);

		bfs(root);
		
		System.out.println(ans_level+" "+ans_breadth);
	}
	
	private static void bfs(int root) {
		Queue<Node> q = new LinkedList<>();
		Node rootNode = tree[root];
		rootNode.level = 1;
		q.offer(rootNode);
		
		int min_c = N+1;
		int max_c = 0;
		int level = 1;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.level > level) {
				int temp_breadth = max_c - min_c + 1;
				
				if (ans_breadth < temp_breadth) {
					ans_breadth = temp_breadth;
					ans_level = level; 
				}
				
				level = cur.level;
				min_c = N+1;
				max_c = 0;
			}
			
			if (cur.col < min_c) {
				min_c = cur.col;
			}
			
			if (cur.col > max_c) {
				max_c = cur.col;
			}
			
			if (cur.left != -1) {
				Node leftNode = tree[cur.left];
				leftNode.level = cur.level+1;
				q.offer(leftNode);
			}
			
			if (cur.right != -1) {
				Node rightNode = tree[cur.right];
				rightNode.level = cur.level+1;
				q.offer(rightNode);
			}
		}
		
		// 마지막 레벨이 정답이 될 수도 있으므로
		int temp_breadth = max_c - min_c + 1;
		if (ans_breadth < temp_breadth) {
			ans_breadth = temp_breadth;
			ans_level = level; 
		}
	}

	private static void inOrder(int n) {
		if (n != -1) {
			Node node = tree[n];
			
			inOrder(node.left);
			node.col = col++;
			inOrder(node.right);
		}
	}

	static class Node {
		int col, left, right, level;
		
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "Node [col=" + col + ", left=" + left + ", right=" + right + ", level=" + level + "]";
		}
	}
}
