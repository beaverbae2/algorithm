package A2020_12_11;

public class PGS_단어변환 {
	private boolean[] visited;
	private int answer,n,word_len;
	private String[] words;
	 
	public int solution(String begin, String target, String[] words) {
		this.answer = Integer.MAX_VALUE;
		this.n = words.length;
		this.visited = new boolean[n];
		this.word_len = begin.length();
		this.words = words;
		
		dfs(0, begin, target);
		return answer == Integer.MAX_VALUE ? 0 : answer;
	}

	public void dfs(int r, String begin, String target) {
		if(r>n) return;
		
		if(begin.equals(target)) {
			answer = Math.min(answer, r);
			return;
		}
		
		for (int i = 0; i < words.length; i++) {
			if(visited[i]) continue;
			
			int same = 0;//같은 알파벳
			for (int j = 0; j < word_len; j++) {
				if(begin.charAt(j)==words[i].charAt(j)) {
					same++;
				}
			}
			
			if(same==word_len-1) {
				visited[i] = true;
				dfs(r+1, words[i] , target);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		PGS_단어변환 a = new PGS_단어변환();
		System.out.println(a.solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log", "cog" }));
		a = new PGS_단어변환();
		System.out.println(a.solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log" }));
	}
}
