package A2021_01_05;

import java.util.*;
import java.io.*;

//천천히 코드 짜자
public class BOJ_3190_뱀 {
	static int N;
	static Deque<Elem> snake;
	static int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
	static boolean[][] isApple;
	static HashMap<Integer, Character> rotate;
	
	static class Elem {
		int r, c, dir;

		public Elem(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Elem [r=" + r + ", c=" + c + ", dir=" + dir + "]";
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		snake = new LinkedList<>();
		rotate = new HashMap<>();
		N = Integer.parseInt(br.readLine());
		isApple = new boolean[N][N];
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			isApple[r][c] = true;
		}
		
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			rotate.put(time, dir);
		}
		
		//뱀 초기화
		snake.add(new Elem(0, 0, 0));
		
		//이동
		int answer = 1;
		while(true) {
			Elem old_head = snake.peekFirst();
			
			int or = old_head.r;
			int oc = old_head.c;
			int odir = old_head.dir;
			
			//머리를 다음칸으로 이동
			int nr = -1, nc = -1, ndir = odir;
			nr = or+dirs[odir][0];
			nc = oc+dirs[odir][1];
			
			//방향 변환을 하는 경우
			if(rotate.get(answer) != null) {
				ndir = changeDirection(odir, rotate.get(answer));
			}
			
			//머리 추가
			snake.addFirst(new Elem(nr, nc, ndir));
			
			if (!isInMap(nr, nc)) break;
			if (isConflictOwn(nr, nc)) break;
			
			//사과 유무
			if(!isApple[nr][nc]) {
				snake.pollLast();
			}else {
				isApple[nr][nc] = false;
			}
			
			answer++;
		}
		System.out.println(answer);
	}


	private static int changeDirection(int odir, char dir) {
		int ndir = -1;
		
		if(dir == 'D') {//오
			ndir = odir+1;
		}else {//왼
			ndir = odir+3;
		}
		ndir = ndir%4;
		return ndir;
	}


	private static boolean isConflictOwn(int nr, int nc) {
		int idx = 0;
		
		for(Elem e : snake){
			if(idx !=0 && nr == e.r && nc == e.c) return true;
			idx++;
		}
		return false;
	}


	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<N&&nc>=0&&nc<N;
	}
}
