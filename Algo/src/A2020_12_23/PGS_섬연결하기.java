package A2020_12_23;

import java.util.*;

public class PGS_섬연결하기 {
	private List<Edge> list;
	private int[] parent;
	
	
	static class Edge implements Comparable<Edge> {
		int a, b, w;

		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Edge [a=" + a + ", b=" + b + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	
	public int solution(int n, int[][] costs) {
        int answer = 0;
        list = new ArrayList<>();
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
        
        for (int i = 0; i < costs.length; i++) {
			int a = costs[i][0];
			int b = costs[i][1];
			int w = costs[i][2];
			
			list.add(new Edge(a, b, w));
		}
        Collections.sort(list);
        
        int cnt = 0;
        for (Edge edge : list) {
        	int a = edge.a;
        	int b = edge.b;
        	int w = edge.w;
        	
        	if (findParent(a, b)) continue;
        	unionParent(a, b);
        	answer += w;
        	cnt++;
        	
        	if(cnt == n-1) break;
        }
         
        return answer;
    }
	
	public int getParent(int v) {
		if (v == parent[v]) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	public void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	public boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		PGS_섬연결하기 a = new PGS_섬연결하기();
		System.out.println(a.solution(4, new int[][] {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
	}
}
