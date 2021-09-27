package A2021_02_25;

import java.util.*;

public class PGS_무지의_먹방_라이브_효율성탈락 {
	
	
	public int solution(int[] food_times, long k) {
		int answer = -1;
		int N = food_times.length;
		
		boolean isStop = false;
		
		while(true) {
			int i;
			for (i = 0; i < N; i++) {
				if (food_times[i] == 0) {
					continue;
				}
				
				k--;
				food_times[i]--;
				
				if (k == 0) {
					int start_idx = i+1;
					int idx = i+1;
					while(true) {
						if (idx == N) idx = 0;
						if (food_times[idx]>0) break;
						
						idx++;
						if (idx == start_idx) break; 					
					}
					
					if (idx == start_idx) {
						if (food_times[idx] == 0) {
							answer = -1;
						} else {
							answer = idx+1;
						}
					} else {
						answer = idx+1;
					}
					isStop = true;
					break;
				}
			}
			if (isStop) break;
		}
		
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(new PGS_무지의_먹방_라이브_효율성탈락().solution(new int[] {3,1,2}, 5));
		System.out.println(new PGS_무지의_먹방_라이브_효율성탈락().solution(new int[] {1,2,3,1,3}, 8));
//		System.out.println(new PGS_무지의_먹방_라이브().solution(new int[] {1,1,1}, 5));
	}
}
