package A2021_01_05;

/**
 * 
 * @author beaverbae
 * @see https://yabmoons.tistory.com/610
 * 
 */

public class PGS_스타_수열Solution {
	public int solution(int[] a) {
		int answer = 0;
		//a를 구성하는 숫자 개수 구하기
		int[] count = new int[a.length+1];
		for (int i = 0; i < a.length; i++) {
			count[a[i]]++;
		}
		
		//공통된 값을 i 로 설정
		for (int i = 0; i < count.length; i++) {
			if(count[i] <= answer) continue;//이 경우 항상 answer보다 작거나 같은 값만 존재
			int temp = 0;
			
			//a에 대해서 탐색
			for (int j = 0; j < a.length-1; j++) {
				if(a[j] != i && a[j+1] != i) continue;
				if(a[j] == i && a[j+1] == i) continue;
			
				//스타수열의 구성요소 인경우
				temp++;
				j++;
			}
			answer = Math.max(answer, temp);
		}
		
		return answer*2;
	}

	public static void main(String[] args) {
		System.out.println(new PGS_스타_수열Solution().solution(new int[] {0}));
		System.out.println(new PGS_스타_수열Solution().solution(new int[] {5,2,3,3,5,3}));
		System.out.println(new PGS_스타_수열Solution().solution(new int[] {0,3,3,0,7,2,0,2,2,0}));
	}
}
