package A2021_01_22;

import java.util.*;

/**
 * Two Pointers
 * @author beaverbae
 *
 */

public class PGS_보석쇼핑ver2 {
	public int[] solution(String[] gems) {
	    HashSet<String> set = new HashSet<>();// 보석 종류의 개수 파악
	    for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}
		
	    int N = set.size();// 보석의 개수
		HashMap<String, Integer> map = new HashMap<>(); // <보석이름, 보석개수>
		ArrayList<Pair> list = new ArrayList<>();// 보석이 모두 포함된 구간의 시작과 끝 번호가 담긴 리스트
	    
	    int start = 0, end = 0;// 투 포인터 초기화
	    
	    while (true) {
	    	if (map.size() == N) {// 모든 종류의 보석이 다 있는 경우
	    		list.add(new Pair(start, end-1));// 리스트에 추가
	    		
	    		int cnt = map.get(gems[start]);// 맨 앞 보석의 개수
	    		if (cnt == 1) {// 1개인 경우 삭제
	    			map.remove(gems[start]);
	    		}else {// 그 외는 -1 씩
	    			map.put(gems[start], cnt - 1);
	    		}
	    		start++;
	    	}else {// 아닌 경우
	    		if (end == gems.length) break;
	    		
	    		if (map.containsKey(gems[end])) {// 이미 보석이 있는 경우
	    			int cnt = map.get(gems[end]);
	    			map.put(gems[end], cnt + 1);
	    		}else {//처음인 경우 +1
	    			map.put(gems[end], 1);
	    		}
	    		end++;
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
		System.out.println(Arrays.toString(new PGS_보석쇼핑ver2().solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
		System.out.println(Arrays.toString(new PGS_보석쇼핑ver2().solution(new String[] {"AA", "AB", "AC", "AA", "AC"})));
		System.out.println(Arrays.toString(new PGS_보석쇼핑ver2().solution(new String[] {"XYZ", "XYZ", "XYZ"})));
		System.out.println(Arrays.toString(new PGS_보석쇼핑ver2().solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
	}
}
