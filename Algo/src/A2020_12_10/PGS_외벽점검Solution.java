package A2020_12_10;

import java.util.*;

public class PGS_외벽점검Solution {
	private int n, num;
	private int[] weak, dist;
	private LinkedList<Integer> choiceDist, finalDist;
	private boolean[] visited;
	private int[][] rotateWeak;
	
	public int solution(int n, int[] weak, int[] dist) {
		this.n = n;
		this.weak = weak;
		this.dist = dist;
		this.choiceDist = new LinkedList<>();
		this.num = Integer.MAX_VALUE;
		
		setWeak();//원형 weak배열 만들기
		setDist(0,0);
	
		return num != Integer.MAX_VALUE ? num : -1;
	}
	
	public void permutation(int r, int n) {
		if(r==n) {
//			System.out.println(finalDist);
			check();
			return;
		}
		
		for (int i = 0; i < choiceDist.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				finalDist.add(choiceDist.get(i));
				permutation(r+1, n);
				visited[i] = false;
				finalDist.removeLast();
			}
		}
	}

	//부분집합 생성
	public void setDist(int r, int s) {
		if(r == dist.length) {
			//배정
//			System.out.println(choiceDist);
			visited = new boolean[choiceDist.size()];
			finalDist = new LinkedList<>();
			permutation(0, s);
			return;
		}
		
		choiceDist.add(dist[r]);
		setDist(r+1, s+1);
		choiceDist.removeLast();
		setDist(r+1, s);
	}
	
	public void check() {
		int result = finalDist.size();
		
		for (int i = 0; i < rotateWeak.length; i++) {
			int v = rotateWeak[i][0];
			int cnt = 0;
			LinkedHashMap<Integer, Boolean> copy = new LinkedHashMap<>();
			for (int j = 0; j < rotateWeak.length; j++) {
				copy.put(rotateWeak[i][j], true);
			}
			for (int j = 0; j < finalDist.size(); j++) {
				int move = finalDist.get(j);
				int flag = 0;
				for (int k = 0; k <= move; k++) {
					if(copy.get(v%n)==null) {
						v++;
						v=v%n;
						continue;
					}
					else if(copy.get(v%n)) {
						copy.remove(v%n);
						flag++;
						cnt++;
						v++;
						v=v%n;
					}
				}
				if(flag>=1&&!copy.isEmpty()) {
					int next = copy.keySet().iterator().next();
					v = next%n;
				}
			}
			if(cnt==weak.length) {
//				System.out.println(choiceDist);
				num = Math.min(num, result);
			}
		}
	}

	public void setWeak() {
		int len = weak.length;
		rotateWeak = new int[len][len];
		for (int i = 0; i < len; i++) {
			rotateWeak[i] = rotate(weak, i);
		}
		
		
//		for (int i = 0; i < len; i++) {
//			System.out.println(Arrays.toString(rotateWeak[i]));
//		}
	}
	
	//원형
	public int[] rotate(int[] weak, int idx) {
		int len = weak.length;
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			if (i + idx < len)
				result[i] = weak[i + idx];
			else
				result[i] = (weak[i + idx - len] + n)%n;
		}
		return result;
	}
	
	public static void main(String[] args) {
		PGS_외벽점검Solution a = new PGS_외벽점검Solution(); 
		System.out.println(a.solution(12, new int[] { 1, 5, 6, 10 }, new int[] { 1, 2, 3, 4 }));
		a = new PGS_외벽점검Solution(); 
		System.out.println(a.solution(12, new int[] { 1, 3, 4, 9, 10 }, new int[] { 3, 5, 7 }));
		a = new PGS_외벽점검Solution(); 
		System.out.println(a.solution(200, new int[] { 0, 100 }, new int[] { 1, 1}));
	
	}
}
