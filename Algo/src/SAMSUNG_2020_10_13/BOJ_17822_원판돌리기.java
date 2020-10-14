package SAMSUNG_2020_10_13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17822_원판돌리기 {
	static int N,M,T;
	static int[][] onePan;
	static int[][] rotate;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		onePan = new int[N+1][M+1];
		rotate = new int[T+1][4];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				onePan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for (int i = 0; i <= N; i++) {
//			System.out.println(Arrays.toString(onePan[i]));
//		}
//		System.out.println();
//		
//		
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 4; j++) {
				rotate[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		
//		for (int i = 0; i <= T; i++) {
//			System.out.println(Arrays.toString(rotate[i]));
//		}
		
		//작업
		for (int t = 1; t <= T; t++) {
			int x = rotate[t][1];
			int d = rotate[t][2];
			int k = rotate[t][3];
		
			for (int r = 1; r <= N; r++) {
				if(r%x!=0) continue;
				int[] next_elem = new int[M+1];
				
				if(d==0) {
					for (int c = 1; c <= M; c++) {
						int index = 0;
						if(c+k>M) {
							index = (c+k)%M;
						}else {
							index = c+k;
						}
						next_elem[index] = onePan[r][c];
					}
				}else {
					for (int c = M; c >= 1; c--) {
						int index = 0;
						if(c-k<1) {
							index = c-k+M;
						}else {
							index = c-k;
						}
						
						next_elem[index] = onePan[r][c];
					}
				}
				onePan[r] = next_elem;
			}
			arrage();//인접한 녀석 삭제		
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= M; j++) {
//					System.out.print(onePan[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				answer += onePan[i][j];
			}
		}
		System.out.println(answer);
		
	}
	//인접 정리
	private static void arrage() {
		int[][] newOnePan = new int[N+1][M+1];
		boolean isSame = false;
		int sum =0, count = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if(onePan[r][c] == 0) continue;
				sum += onePan[r][c];
				count++;
				
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r+dirs[d][0];
					int nc = c+dirs[d][1];
					
					if(nc==0) nc=M;
					if(nc==M+1) nc=1;
					
					if(!isInMap(nr, nc)) continue;
				
					if(onePan[r][c]==onePan[nr][nc]) {
						newOnePan[nr][nc] = -1;
						isSame = true;
					}
				}
			}
		}
		
		if(isSame) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if(newOnePan[i][j]==-1) {
						onePan[i][j] = 0;
					}
				}
			}
		}else {
			double avg = (double) sum/ (double)count;
			//System.out.println(avg);
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if(onePan[i][j] == 0) continue;
					
					if((double) onePan[i][j]>avg) {
						onePan[i][j]--;
					}else if((double) onePan[i][j]<avg) {
						onePan[i][j]++;
					}
				}
			}
		}
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= M; j++) {
//				System.out.print(onePan[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=1 && nr<=N && nc>=1 && nc<=M;
	}
}
