package A2021_01_22;

import java.util.*;

/**
 * Two Pointers
 * @author beaverbae
 *
 */

public class PGS_보석쇼핑 {
	public int[] solution(String[] gems) {
	    HashSet<String> set = new HashSet<>();// 보석 종류의 개수 파악
	    for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}
		
	    int N = set.size();// 보석의 개수
		TreeMap<Integer, String> map1 = new TreeMap<>();
	    HashMap<String, Integer> map2 = new HashMap<>();
		ArrayList<Pair> list = new ArrayList<>();
	    HashSet<String> gemSet = new HashSet<>();
	    
	    int start = 0, end = 0;
	    
	    while (true) {
	    	if (end >= gems.length) {;
	    		if (map2.size() != N) {
	    			break;
	    		}
	    		
	    		if (map2.size() == N){
		    		int first = map1.firstKey();
		    		int last = map1.lastKey();
		    		
		    		list.add(new Pair(first, last));
		    		map1.remove(first);
		    		
		    		int cnt = map2.get(gems[first]);
		    		if (cnt == 1) {
		    			map2.remove(gems[first]);
		    		}else {
		    			map2.put(gems[first], cnt - 1);
		    		}
		    		start++;
		    	}
	    	}else {
	    		if (map2.size() != N) {
		    		map1.put(end, gems[end]);
		    		if (map2.containsKey(gems[end])) {
		    			int cnt = map2.get(gems[end]);
		    			map2.put(gems[end], cnt+1);
		    		}else {
		    			map2.put(gems[end], 1);
		    		}
		    		end++;
		    	}
		    	
		    	if (map2.size() == N){
		    		int first = map1.firstKey();
		    		int last = map1.lastKey();
		    		
		    		list.add(new Pair(first, last));
		    		map1.remove(first);
		    		
		    		int cnt = map2.get(gems[first]);
		    		if (cnt == 1) {
		    			map2.remove(gems[first]);
		    		}else {
		    			map2.put(gems[first], cnt - 1);
		    		}
		    		start++;
		    	}
	    	}
	    }
	    
	    Collections.sort(list);
	    int[] answer = new int[] {list.get(0).first+1, list.get(0).last+1};
	    return answer;
	}
	
	static class Pair implements Comparable<Pair>{
		int first, last;

		public Pair(int first, int last) {
			this.first = first;
			this.last = last;
		}                                                                        

		@Override
		public String toString() {
			return "Pair [first=" + first + ", last=" + last + "]";
		}
		
		@Override
		public int compareTo(Pair o) {
			int len1 = this.last - this.first;
			int len2 = o.last - o.first;
			
			if (len1 != len2) {
				return Integer.compare(len1, len2);
			}else {
				return Integer.compare(this.first, o.first);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new PGS_보석쇼핑().solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
		System.out.println(Arrays.toString(new PGS_보석쇼핑().solution(new String[] {"AA", "AB", "AC", "AA", "AC"})));
		System.out.println(Arrays.toString(new PGS_보석쇼핑().solution(new String[] {"XYZ", "XYZ", "XYZ"})));
		System.out.println(Arrays.toString(new PGS_보석쇼핑().solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
	}
}
