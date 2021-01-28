package A2020_12_22;


public class PGS_등굣길 {
	private int[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{1,0},{0,1}};
	private int R,C;
	private final int MOD = 1000000007;
	
	public int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		R = n;
		C = m;
		map = new int[R][C];
		visited = new boolean[R][C];
		
		map[R-1][C-1] = 1;
		for (int i = 0; i < puddles.length; i++) {
			int r = puddles[i][1] -1;
			int c = puddles[i][0] -1;
			visited[r][c] = true;
		}
		
		dfs(0, 0);
		answer = map[0][0];
		
		return answer;
	}
	
	public int dfs(int r, int c) {
		//이미 한번 계산한 경우
		if (map[r][c] != 0) return map[r][c];
		
		//처음 계산하는 경우
		for (int d = 0; d < dirs.length; d++) {
			int nr = r+dirs[d][0];
			int nc = c+dirs[d][1];
			
			if (isInMap(nr, nc)&&!visited[nr][nc]) {
				map[r][c] += dfs(nr, nc);
				map[r][c] = map[r][c]%MOD;
			}
		}
		return map[r][c];
	}
	
	public boolean isInMap(int nr, int nc) {
		return nr>=0 && nr< R && nc>=0 && nc<C;
	}

	public static void main(String[] args) {
		PGS_등굣길 a = new PGS_등굣길();
		System.out.println(a.solution(4, 3, new int[][] {{2,2}}));
	}
}
