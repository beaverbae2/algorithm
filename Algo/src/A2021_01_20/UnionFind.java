package A2021_01_20;

import java.util.Arrays;

public class UnionFind {
	private static int[] parent;// parent[i] = i번쨰 노드의 부모 노드

	public static void main(String[] args) {
		parent = new int[4 + 1];

		// 주의 : 1과 2는 서로 같은 집합(그래프)이다!!
		// 같은 집합(그래프)이어도 parent의 원소는 다를 수 있다
		// 두 원소(노드)가 같은 집합(그래프)에 속하는지 확인할 땐 findParent를 실행
		
		// 1
		initParent();
		unionParent(1, 2);
		unionParent(2, 3);
		unionParent(3, 4);
		System.out.println(Arrays.toString(parent));// [0, 1, 1, 1, 1]
		System.out.println(findParent(1, 4));// true
		System.out.println(Arrays.toString(parent));// [0, 1, 1, 1, 1]
		System.out.println(findParent(2, 3));// true
		System.out.println(Arrays.toString(parent));// [0, 1, 1, 1, 1]

		// 2
		initParent();
		unionParent(3, 4);
		unionParent(2, 3);
		unionParent(1, 4);
		System.out.println(Arrays.toString(parent));// [0, 1, 1, 2, 2]
		System.out.println(findParent(1, 4));// true
		System.out.println(Arrays.toString(parent));// [0, 1, 1, 2, 1]
		System.out.println(findParent(2, 3));// true
		System.out.println(Arrays.toString(parent));// [0, 1, 1, 1, 1]
	}

	// parent 배열 초기화
	public static void initParent() {
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
	}

	// 부모 찾기
	public static int getParent(int v) {
		if (parent[v] == v) return parent[v];
		return parent[v] = getParent(parent[v]);
	}

	// 부모 합치기
	public static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a < b) parent[b] = a;
		else parent[a] = b;
	}

	// 같은 부모를 가지는지 확인
	public static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a == b) return true;
		return false;
	}

}
