package A2022_03_01;

import java.util.*;

/**
 * DFS
 * @author beaverbae
 * - 라이언이 낮은 점수의 과녁부터 맞추게 재귀 최적화
 */

public class KAKAO_양궁대회2_v2 {
	final int N = 11;
	int n;
	int[] result = new int[N];
	int[] arr = new int[N];
	int[] info;
	int maxPoint = 0;
	
	public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        
        dfs(0, N-1);
        if (maxPoint == 0) return new int[] {-1};
        return result;
    }
	
	private void dfs(int idx, int end) {
		if (idx == n) {
			calc();
			return;
		}
		
		for (int i = end; i >= 0; i--) {
			arr[i]++;
			dfs(idx + 1, i);
			arr[i]--;
		}
	}
	
	private void calc() {
		int apeach = 0, ryan = 0;
	    
		for (int i = 0; i < N; i++) {
			if (info[i] == 0 && arr[i] == 0) continue;
			
			if (info[i] >= arr[i]) {
				apeach += (10 - i);
			} else {
				ryan += (10 - i);
			}
		}
		
		if (ryan > apeach) {
			int sub = ryan - apeach;
			if (sub > maxPoint) {
				maxPoint = sub;
				result = arr.clone();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new KAKAO_양궁대회2_v2().solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(new KAKAO_양궁대회2_v2().solution(1, new int[] {1,0,0,0,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(new KAKAO_양궁대회2_v2().solution(9, new int[] {0,0,1,2,0,1,1,1,1,1,1})));
		System.out.println(Arrays.toString(new KAKAO_양궁대회2_v2().solution(10, new int[] {0,0,0,0,0,0,0,0,3,4,3})));
	}
}
