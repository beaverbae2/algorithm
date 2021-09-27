package A2021_02_22;

import java.util.*;

/**
 * BST
 * @author beaverbae
 *
 */

public class PGS_길_찾기_게임_review2 {
	int[][] answer;
	int idx;
	
	public int[][] solution(int[][] nodeinfo) {
		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < nodeinfo.length; i++) {
			list.add(new Pair(i+1, nodeinfo[i][0], nodeinfo[i][1]));
		}
		
		// 이진 트리에 입력하기 위해 정렬
		Collections.sort(list, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.y != o2.y) {// y좌표
					return Integer.compare(o2.y, o1.y);// 내림차순
				}else {// x좌표
					return Integer.compare(o1.x, o2.x);// 오름차순
				}
			}
		});
        
		BinaryTree tree = new BinaryTree();
        for (int i = 0; i < list.size(); i++) {
        	Pair p = list.get(i);
        	tree.insert(p.n, p.x);
		}
        answer= new int[2][nodeinfo.length];
        
        idx = 0;
        preOrder(tree.root);
        
        idx = 0;
        postOrder(tree.root);
        
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
	

	public void preOrder(Node node) {
		if (node != null) {
			answer[0][idx++] = node.n;
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			answer[1][idx++] = node.n;
		}
	}
	
	static class BinaryTree{
		Node root;
		
		public void insert(int n, int x) {
			Node node = root;
			
			if (root == null) {
				root = new Node(n,x);
				return;
			}
			
			while(true) {				
				if (x < node.x) {
					if (node.left == null) {
						node.left = new Node(n,x);
						break;
					}
					node = node.left;
				} else {
					if (node.right == null) {
						node.right = new Node(n,x);
						break;
					}
					node = node.right;
				}
			}
		}
	}
	
	static class Node {
		int n, x;
		Node left, right;
		
		public Node(int n, int x) {
			this.n = n;
			this.x = x;
		}
	}
		
	
	public static void main(String[] args) {
		new PGS_길_찾기_게임_review2().solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
	}
}
