package A2021_02_26;

import java.util.*;

/**
 * String
 * @author beaverbae
 *
 */

public class PGS_순위_검색_review {
	static String[][] arr = {{"cpp", "java", "python"},
						{"backend", "frontend"},
						{"junior", "senior"},
						{"chicken", "pizza"}};
	
	HashMap<String, Integer> map = new HashMap<>();
	TreeMap<Integer, String> map2 = new TreeMap<>();
	
	int N;
	List<Integer>[] score_list;
	
	public int[] solution(String[] info, String[] query) {
		
		for (int i = 1; i <= 4; i++) {
			init(0, 0, i, "");
		}
		map.put("-", map.size()+1);
		N = map.size();
		map2.put(N, "-");
		score_list = new List[N+1];
		for (int i = 1; i < score_list.length; i++) {
			score_list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < info.length; i++) {
			String[] split = info[i].split(" ");
			
			for (int j = 1; j < split.length; j++) {
				combination(0, 0, j, split, "");
			}
			score_list[N].add(Integer.parseInt(split[split.length-1]));
		}
		for (int i = 1; i < score_list.length; i++) {
			if (score_list[i].isEmpty()) continue;
			Collections.sort(score_list[i]);
		}
		
//		for (int i = 1; i < score_list.length; i++) {
//			if (score_list[i].isEmpty()) continue;
//			System.out.println(map2.get(i)+" : "+score_list[i]);
//		}
//		System.out.println();
		
		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			String q = query[i];
			StringBuilder sb = new StringBuilder();
			String[] split = q.split(" and ");
			int score = Integer.parseInt(split[split.length-1].substring(split[split.length-1].indexOf(" ")+1));
			split[split.length-1] = split[split.length-1].substring(0,split[split.length-1].indexOf(" "));

			for (int j = 0; j < split.length; j++) {
				if (split[j].equals("-")) continue;
				sb.append(split[j]).append(" ");
			}
			
			if (sb.length()== 0) {
				sb.append("-");
			}
			
			int idx = map.get(sb.toString());
			if (score_list[idx].isEmpty()) answer[i] = 0;
			else {
				answer[i] = score_list[idx].size() - lower_bound(idx, score);
			}
		}
		
		return answer;
	}
	
	private int lower_bound(int idx, int score) {
		List<Integer> list = score_list[idx];
		int start = 0;
		int end = list.size()-1;
		
		int index = list.size();
		
		while (start <= end) {
			int mid = (start+end)/2;
			if (list.get(mid) < score) {
				start = mid+1;
			} else {
				end = mid-1;
				index = mid;
			}
		}
		
		return index;
	}
	
	private void combination(int start, int cnt, int n, String[] input, String s) {
		if (cnt == n) {
			int idx = map.get(s);
			score_list[idx].add(Integer.parseInt(input[input.length-1]));
			return;
		}
		
		for (int i = start; i < input.length-1; i++) {
			combination(i+1, cnt+1, n, input, s+input[i]+" ");
		}
	}
	
	private void init(int start, int cnt, int n, String s) {
		if (cnt == n) {
			int next = map.size()+1;
			map.put(s, next);
			map2.put(next, s);
			return;
		} 
		
		for (int i = start; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				init(i+1, cnt+1, n, s+arr[i][j]+" ");
			}
		}
	}
	
	public static void main(String[] args) {
		new PGS_순위_검색_review().solution(new String[] {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}, new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
	}
}
