package A2021_02_02;

import java.util.*;
import java.io.*;
/**
 * Union Find
 * 
 * @author beaverbae
 *
 */
public class BOJ_4195_친구_네트워크 {
	static StringBuilder sb;
	static HashMap<String, Integer> map;
	static int[] parent;
	static int[] children;// children[i] : i번쨰 노드의 자식의 개수
	static String[][] input;
	static int c;// 자식 그래프 집합
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < tc; t++) {
			map = new HashMap<>();
			
			int N = Integer.parseInt(br.readLine());
			input = new String[N][2];
			int num = 1;// 번호
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 2; j++) {
					String s = st.nextToken();
					input[i][j] = s;
					if (!map.containsKey(s)) {
						map.put(s, num++);
					}
				}
			}
		
			parent = new int[map.size()+1];
			children = new int[map.size()+1];
			for (int i = 1; i < parent.length; i++) {
				parent[i] = i;
				children[i] = 1;// 자식 개수 1로 초기화
			}
			
			for (int i = 0; i < input.length; i++) {
				int a = map.get(input[i][0]);
				int b = map.get(input[i][1]);
				
				unionParent(a, b);// 두 그래프를 연결
				if (findParent(a, b)) {// 두 그래프의 공통 조상 구함
					int p = parent[a];// 부모 그래프 집합 parent[a] == parent[b]
					
					if (p != c) {// 같은 그래프에 속하는 경우 배제
						children[p] += children[c];// 부모 그래프에 자식 그래프 추가
						children[c] = 0;// 자식 그래프는 이제 없음
					}
					
					sb.append(children[p]).append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) {
			
			return v;
		}
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
	
		if (a < b) {
			c = b;// c: 자식 그래프
			parent[b] = a;
		}
		else {
			c = a;// c: 자식 그래프
			parent[a] = b;
		}
	}
	
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		return false;
	}
	
}
