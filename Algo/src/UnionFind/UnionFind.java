package UnionFind;

import java.util.Arrays;

public class UnionFind {
	static int[] parent = new int[11];//부모노드의 값
	
	public static void main(String[] args) {
		//자기 자신으로 초기화
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		unionParent(1, 2);
		unionParent(2, 3);
		unionParent(3, 4);
		unionParent(5, 6);
		unionParent(6, 7);
		unionParent(7, 8);
		System.out.println(findParent(1, 5));
		unionParent(1, 5);
		System.out.println(findParent(1, 5));
		System.out.println(Arrays.toString(parent));
	}
	
	//부모노드 확인
	private static int getParent(int v) {
		if(v==parent[v]) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	//각 부모노드를 합침
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	
	//부모노드가 같은지 확인
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		else return false;
	}
}
