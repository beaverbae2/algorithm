package A2022_03_01;

import java.util.*;

/**
 * DFS
 * @author beaverbae
 * - 라이언과 어피치의 점수차가 가장 큰 경우를 봐야함
 * - (중요) 라이언이 이기고 점수차가 같은 경우 
 *     - 해당 점수차를 가진 **모든** 라이언의 점수 배열중 낮은 점수의 개수가 가장 많은 것 선택해야함
 *     - 단순히 정답 후보인 라이언의 점수 배열과 점수가 같은 현재 뽑은 라이언의 점수 배열의 낮은 점수의 개수를 비교하는 건 오류 발생  
 */

public class KAKAO_양궁대회 {
	final int N = 11;
	int n;
	int[] result = new int[N];
	int[] arr = new int[N];
	int[] info;
	int maxPoint = 0;
	List<int[]> list = new ArrayList<>();
	
	public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        
        dfs(0, 0);
        Collections.sort(list, new Comparator<int[]>() {
        	@Override
			public int compare(int[] o1, int[] o2) {
				for (int i = N-1; i >= 0; i--) {
					if (o1[i] == o2[i]) continue;
					return o2[i] - o1[i];
				}
				return 0;
        	}
		});
        if (list.isEmpty()) return new int[] {-1};
        return list.get(0);
    }
	
	private void dfs(int idx, int start) {
		if (idx == n) {
			calc();
			return;
		}
		
		for (int i = start; i < N; i++) {
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
				list.clear();
				list.add(arr.clone());
			} else if (sub == maxPoint) {
				list.add(arr.clone());
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new KAKAO_양궁대회().solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(new KAKAO_양궁대회().solution(1, new int[] {1,0,0,0,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(new KAKAO_양궁대회().solution(9, new int[] {0,0,1,2,0,1,1,1,1,1,1})));
		System.out.println(Arrays.toString(new KAKAO_양궁대회().solution(10, new int[] {0,0,0,0,0,0,0,0,3,4,3})));
	}
}
