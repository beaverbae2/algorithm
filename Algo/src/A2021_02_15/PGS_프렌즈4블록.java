package A2021_02_15;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */

public class PGS_프렌즈4블록 {
	int[][] dirs = {{1,0},{0,1},{1,1}};
	int R, C;
	char[][] map;
	boolean[][] isBlank;
	char[][] next_map;
	final char blank = '.';
	
	public int solution(int m, int n, String[] board) {
		R = m;
		C = n;
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = board[i];
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		game();
		
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == blank) answer++;
			}
		}

		return answer;
	}
	
	public void game() {
		while(true) {
			if (!isPop()) break;// 2x2가 터지는 곳이 없으면 종료
			
			arrage();
		}
	}
	
	public void arrage() {
		next_map = new char[R][C];
		
		for (int c = 0; c < C; c++) {
			int row = R-1;
			for (int r = R-1; r >= 0; r--) {
				if (!isBlank[r][c]) {
					next_map[row][c] = map[r][c];
					row--;
				}
			}
			for (int r = row; r >= 0; r--) {
				next_map[r][c] = blank;
			}
		}
		
		map = next_map;
	}

	public boolean isPop() {
		boolean result = false;
		isBlank = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '.') continue;
				
				int sameCnt = 1;
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (!isinMap(nr, nc)) continue;
					
					if (map[r][c] == map[nr][nc]) sameCnt++;
				}
				
				if (sameCnt==4) {
					result = true;
					
					isBlank[r][c] = true;
					for (int d = 0; d < dirs.length; d++) {
						int nr = r + dirs[d][0];
						int nc = c + dirs[d][1];
						
						isBlank[nr][nc] = true;
					}
				}
			}
		}

		return result;
	}
	
	public boolean isinMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}

	public static void main(String[] args) {
		new PGS_프렌즈4블록().solution(4, 5, new String[] {"CCBDE", 
														"AAADE", 
														"AAABF", 
														"CCBBF"});
		new PGS_프렌즈4블록().solution(6, 6, new String[] {"TTTANT", 
														"RRFACC", 
														"RRRFCC", 
														"TRRRAA", 
														"TTMMMF", 
														"TMMTTJ"});
	}
}
