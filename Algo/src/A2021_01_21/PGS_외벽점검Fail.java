package A2021_01_21;

import java.util.*;

public class PGS_외벽점검Fail {
	private int[] selectedDist;
	private ArrayList<int[]> selectedWeak;
	private int[] tempSelectedWeak;
	private boolean[] visitedWeak;
	private HashSet<Integer> weakSet;
			
	private boolean flag;
	private int[] dist;
	private int[] weak;
	private int n;
	
	private ArrayList<Pair> list;
	
	public int solution(int n, int[] weak, int[] dist) {
		int answer = -1;
		
		flag = false;
		this.dist = dist;
		this.weak = weak;
		this.n = n;
		weakSet = new HashSet<>();
		for (int i = 0; i < weak.length; i++) {
			weakSet.add(weak[i]);
		}
		
		// 1개 부터 N개 까지 조합으로 dist의 요소(친구) 선택
		for (int i = 0; i < dist.length; i++) {
			selectedWeak = new ArrayList<>();
			visitedWeak = new boolean[weak.length];
			tempSelectedWeak = new int[i+1];
			selectWeak(0, i+1);
			
			
			selectedDist = new int[i+1];
			selectDist(0, 0, i+1);
		
			if (flag) {
				answer = i+1;
				break;
			}
		}
		
		//System.out.println("정답 : "+answer);
		return answer;
	}
	
	public void selectDist(int start, int r, int n) {
		// 종료 조건
		if (r == n) {
			//System.out.println("dist : "+Arrays.toString(selectedDist));
			//arrangeFriends();
			check();
			
			return;
		}
		
		for (int i = start; i < dist.length; i++) {
			selectedDist[r] = dist[i];
			selectDist(i+1, r+1, n);
		}
	}
	
	public void selectWeak(int r, int n) {
		if (r == n) {
			int[] temp = new int[n];
			for (int i = 0; i < tempSelectedWeak.length; i++) {
				temp[i] = tempSelectedWeak[i];
			}
			selectedWeak.add(temp);
//			System.out.println(Arrays.toString(tempSeletedWeak));
			return;
		}
		
		for (int i = 0; i < weak.length; i++) {
			if (visitedWeak[i]) continue;
			
			visitedWeak[i] = true;
			tempSelectedWeak[r] = i;
			selectWeak(r+1, n);
			visitedWeak[i] = false;
		}
	}
	
	// 친구들을 weak에 배치
	public boolean check() {
		for (int[] temp : selectedWeak) {
			list = new ArrayList<>();
			for (int i = 0; i < temp.length; i++) {
				int w = weak[temp[i]];
				int d = selectedDist[i];
				list.add(new Pair(w, d));
			}
			//System.out.println("list : "+list);
			checkWall(0, list.size(), 0, new HashSet<Integer>());
			//System.out.println(flag);
			if (flag) break;
			//System.out.println();
		}
		return flag;
	}
	
	public void checkWall(int r, int n, int cnt, HashSet<Integer> tempWeakSet){
		if (cnt == weak.length) {
			flag = true;
			return;
		}
		
		if (r >= n) return;
		
		
		int next_cnt = cnt;
		HashSet<Integer> nextTempWeakSet = new HashSet<Integer>();
		nextTempWeakSet.addAll(tempWeakSet);
		next_cnt += goLeft(r,nextTempWeakSet);
		checkWall(r+1, n, next_cnt, nextTempWeakSet);
		
		next_cnt = cnt;
		nextTempWeakSet = new HashSet<Integer>();
		next_cnt += goRight(r,tempWeakSet);
		checkWall(r+1, n, next_cnt, nextTempWeakSet);
	}
	
	public int goLeft(int r, HashSet<Integer> tempWeakSet) {
		Pair p = list.get(r);
		int w = p.w;
		int d = p.d;
		
		int cnt = 0;
		int result = 0;
		//System.out.println("w : "+w+", d : "+d);
		while(true) {
			if (cnt == d+1) break;
			
			if (weakSet.contains(w) && !tempWeakSet.contains(w)) {
				tempWeakSet.add(w);
				result++;
			}
			
			w--;
			if (w < 0) w = n-1;
			cnt++;
		}
		//System.out.println("result : "+ result);
		return result;
	}
	
	public int goRight(int r, HashSet<Integer> tempWeakSet) {
		Pair p = list.get(r);
		int w = p.w;
		int d = p.d;
		
		int cnt = 0;
		int result = 0;
		while(true) {
			if (cnt == d+1) break;
			
			if (weakSet.contains(w) && !tempWeakSet.contains(w)) {
				tempWeakSet.add(w);
				result++;
			}
			
			w++;
			if (w >= n) w = 0;
			cnt++;
		}
		return result;
	}

	static class Pair {
		int w, d;

		public Pair(int w, int d) {
			this.w = w;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Pair [w=" + w + ", d=" + d + "]";
		}
	}

	public static void main(String[] args) {
		new PGS_외벽점검Fail().solution(12, new int[] {1, 5, 6, 10}, new int[] {1, 2, 3, 4});
		new PGS_외벽점검Fail().solution(12, new int[] {1, 3, 4, 9, 10}, new int[] {3, 5, 7} );
	}
	
	
}
