package A2020_12_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17281_야구ver2 {
	
	static int N,answer;
	static int hitter_index, hitter;
	static int[][] player;//i번쨰 이닝의 j번째 타자의 결과
	static int[] hitters;//원래 타순
	static int[] selected_hitters;//구한 타순
	static int[] field;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());//이닝
		player = new int[N+1][10];
		hitters = new int[] {0,1,2,3,4,5,6,7,8,9};
		selected_hitters = new int[10];
		visited = new boolean[10];
		field = new int[4];//루
		
		answer = 0;
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		//1번과 4번 타순 변경
		selected_hitters[4] = 1;
		visited[1] = true; //기존 1번 순서는 고정 되어 있으므로
		perm(1);
		System.out.println(answer);
	}
	
	//4번째 냅두고 타순(순열) 만들기 - 오래걸림ㅠㅠ
	private static void perm(int r) {
		if (r==4) {
			perm(r+1);
			return;//꼭 해줘야함 
		}
		
		if(r==10) {
			//게임 시작
			//System.out.println(Arrays.toString(selected_hitters));
			answer = Math.max(answer, game());
			return;
		}
		
		for (int i = 1; i < hitters.length; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			selected_hitters[r] = hitters[i];
			perm(r+1);
			visited[i] = false;
		}
	}

	private static int game() {
		hitter_index = 1;
		hitter = selected_hitters[hitter_index];
		int point = 0;
		for (int i = 1; i <= N; i++) {//N이닝 동안 반복
			int out_cnt = 0;
			field = new int[4];//매 이닝마다 초기화(놓쳤던거)
			while(true) {
				if(out_cnt==3) break; //종료
				
				//게임 진행
				field[0] = hitter;
				if(player[i][hitter]==0) {//아웃
					nextHitter();
					out_cnt++;
				}else {
					point += move(player[i][hitter]);
					nextHitter();
				}
			}
		}
		return point;
	}
	//진루 하기
	private static int move(int hit_result) {
		int result = 0;
		if(hit_result==1) {
			if(field[3]!=0) {
				result++;
				field[3] = 0;
			}if(field[2]!=0) {
				field[3] = field[2];
				field[2] = 0;
			}if(field[1]!=0) {
				field[2] = field[1];
				field[1] = 0;
			}
			field[1] = field[0];
			return result;
		}else if(hit_result==2) {
			if(field[3]!=0) {
				result++;
				field[3] = 0;
			}if(field[2]!=0) {
				result++;
				field[2] = 0;
			}if(field[1]!=0) {
				field[3] = field[1];
				field[1] = 0;
			}
			field[2] = field[0];
			return result;
		}else if(hit_result==3) {
			if(field[3]!=0) {
				result++;
				field[3] = 0;
			}if(field[2]!=0) {
				result++;
				field[2] = 0;
			}if(field[1]!=0) {
				result++;
				field[1] = 0;
			}
			field[3] = field[0];
			return result;
		}else if(hit_result==4) {
			result = 1;
			if(field[3]!=0) {
				result++;
			}if(field[2]!=0) {
				result++;
			}if(field[1]!=0) {
				result++;
			}
			field = new int[4];
			return result;
		}
		
		return 0;
		
		
	}

	//다음 타자
	private static void nextHitter() {
		hitter_index++;
		if(hitter_index>=10) hitter_index = 1; //10이 되면 index 1로(첨에 9로 해버림...) 
		hitter = selected_hitters[hitter_index];
	}
}
