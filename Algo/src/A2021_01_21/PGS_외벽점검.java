package A2021_01_21;
/**
 * 
 * @author beaverbae
 * @see ? 
 *
 */
public class PGS_외벽점검 {
	private int n;
	private int[] weak;
	private int[] dist;
	private int[][] rotateWeak;// rotateWeak[i] : weak[i] 부터 weak[i-1]까지를 표현
	private boolean isAllChecked;// 모든 외벽이 점검되었는지
	private int[] selectedDist;// dist에서 순열로 뽑은 요소

	public int solution(int n, int[] weak, int[] dist) {
		int answer = -1;
		this.n = n;
		this.weak = weak;
		this.dist = dist;
		setRotateWeak();

		boolean[] visited = new boolean[dist.length];

		for (int i = 0; i < dist.length; i++) {
			selectedDist = new int[i + 1];
			permutation(0, i + 1, visited);
		
			if (isAllChecked) { 
				answer = i+1;
				break;
			}
		}
		
		return answer;
	}
	
	// 순열
	public void permutation(int r, int n, boolean[] visited) {
		if (isAllChecked) return;
		
		if (r == n) {
			check();// 점검 시작
			return;
		}

		for (int i = 0; i < dist.length; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			selectedDist[r] = dist[i];
			permutation(r + 1, n, visited);
			visited[i] = false;
		}

	}
	
	// 외벽 점검
	public void check() {
		for (int[] rotate : rotateWeak) {// 점검해야 하는 외벽의 경우의 수에 대해
			int idx = 0;// rotate(외벽 하나)의 인덱스
			int cnt = 0;// 점검한 외벽의 개수
			
			for (int i = 0; i < selectedDist.length; i++) {// dist에서 뽑은 요소들에 대해
				int d = selectedDist[i];// 이동거리
				
				for (int j = idx; j < rotate.length; j++) {// 현재 외벽부터 외벽의 끝까지
					if (rotate[idx] + d >= rotate[j]) {// 외벽의 현재 위치 + 이동거리 >= 다음 외벽의 위치
						cnt++;
					}else break;
				}
				
				idx = cnt;// 다음으로 확인해야할 외벽의 인덱스
				if (cnt == weak.length) isAllChecked = true;// 정답 찾은 경우
				
				if (isAllChecked) break;
			}
			if (isAllChecked) break;
		}
	}
	
	
	// rotateWeak 구현
	public void setRotateWeak() {
		int len = weak.length;
		rotateWeak = new int[len][len];

		for (int i = 0; i < rotateWeak.length; i++) {
			rotateWeak[i] = getRotateWeak(i);
		}
	}
	
	// rotateWeak 구현
	public int[] getRotateWeak(int idx) {
		int len = weak.length;
		int[] result = new int[len];
		boolean flag = false;

		for (int i = 0; i < weak.length; i++) {
			if (!flag) {
				result[i] = weak[idx];
			} else {
				result[i] = weak[idx] + n;
			}
			idx++;
			if (idx == len) {
				idx = 0;
				flag = true;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		new PGS_외벽점검().solution(12, new int[] { 1, 5, 6, 10 }, new int[] { 1, 2, 3, 4 });
		new PGS_외벽점검().solution(12, new int[] { 1, 3, 4, 9, 10 }, new int[] { 3, 5, 7 });
	}
}
