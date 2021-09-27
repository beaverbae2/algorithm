package A2021_03_06;

import java.util.*;

/**
 * Two Pointer
 * 33 MIN
 * @author beaverbae
 *
 */

public class PGS_보석_쇼핑 {
	public int[] solution(String[] gems) {
		HashSet<String> set = new HashSet<>();
		for (String gem : gems) {
			set.add(gem);
		}
		
		int N = set.size();
		List<Pair> list = new ArrayList<>();
		TreeMap<Integer, String> gems_pos = new TreeMap<>();
		HashMap<String, Integer> gems_cnt = new HashMap<>();
		
		int start = 0;
		int end = 0;
		
		while(true) {
			if (gems_cnt.size() == N) {
				list.add(new Pair(start+1, end));
				String gem = gems_pos.get(start);
				gems_pos.remove(start);
				
				int next_cnt = gems_cnt.get(gem) - 1;
				
				if (next_cnt == 0) gems_cnt.remove(gem);
				else gems_cnt.put(gem, next_cnt);
				start++;
				
				if (start > end) end = start;
			} else {
				if (end == gems.length) break;
				
				String gem = gems[end];
				gems_pos.put(end, gem);
				
				if (gems_cnt.containsKey(gem)) {
					int next_cnt = gems_cnt.get(gem) + 1;
					gems_cnt.put(gem, next_cnt);
				} else {
					gems_cnt.put(gem, 1);
				}
				end++;
			}
		}
		
		Collections.sort(list, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				int bound1 = o1.end - o1.start;
				int bound2 = o2.end - o2.start;
				
				if (bound1 != bound2) {
					return Integer.compare(bound1, bound2);
				} else {
					return Integer.compare(o1.start, o2.start);
				}
			}
		});
		
        int[] answer = {list.get(0).start, list.get(0).end};
        return answer;
	}
	
	static class Pair {
		int start, end;

		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Pair [start=" + start + ", end=" + end + "]";
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new PGS_보석_쇼핑().solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
		System.out.println(Arrays.toString(new PGS_보석_쇼핑().solution(new String[] {"AA", "AB", "AC", "AA", "AC"})));
		System.out.println(Arrays.toString(new PGS_보석_쇼핑().solution(new String[] {"XYZ", "XYZ", "XYZ"})));
		System.out.println(Arrays.toString(new PGS_보석_쇼핑().solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
	}
}
