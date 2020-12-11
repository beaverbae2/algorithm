package A2020_12_11;

import java.util.*;

public class PGS_보석쇼핑 {
	
	static class Elem{
		int start, end, len;

		public Elem(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Elem [start=" + start + ", end=" + end + ", len=" + len + "]";
		}
	}
	
	public int[] solution(String[] gems) {
		Set<String> allGem = new HashSet<>();
		HashMap<String, Integer> selectedGem = new HashMap<>();
		List<Elem> gemList = new ArrayList<>();
		
		//전체 보석 개수 파악
		for (String gem : gems) {
			allGem.add(gem);
		}
		
		int start = 0;
		int end = 0;
		while(true) {
			
			if(selectedGem.size()==allGem.size()) {
				gemList.add(new Elem(start+1, end+1, end-start));
				
				int v = selectedGem.get(gems[start])-1;
				if(v==0) selectedGem.remove(gems[start]);
				else selectedGem.put(gems[start], v);
				
				if (selectedGem.size()==allGem.size()) start++;
				else {
					end++;
					start++;
				}
			}else {
				if(selectedGem.get(gems[end]) == null) {
					selectedGem.put(gems[end], 1);
				}else {
					int v = selectedGem.get(gems[end]);
					selectedGem.put(gems[end], v+1);
				}
				if(selectedGem.size()!=allGem.size()) end++;
			}		
			if(end>=gems.length) break;
		}
		
		Collections.sort(gemList, new Comparator<Elem>() {

			@Override
			public int compare(Elem o1, Elem o2) {
				if(o1.len!=o2.len) {
					return Integer.compare(o1.len, o2.len);
				}else {
					if(o1.start!=o2.start) {
						return Integer.compare(o1.start, o2.start);
					}
					return 0;
				}
			}
		});
		
		Elem elem = gemList.get(0);
		int[] answer = new int[] {elem.start, elem.end};
		return answer;
	}
	
	
	public static void main(String[] args) {
		PGS_보석쇼핑 a = new PGS_보석쇼핑();
		System.out.println(Arrays.toString(a.solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
		a = new PGS_보석쇼핑();
		System.out.println(Arrays.toString(a.solution(new String[] {"AA", "AB", "AC", "AA", "AC"})));
		a = new PGS_보석쇼핑();
		System.out.println(Arrays.toString(a.solution(new String[] {"XYZ", "XYZ", "XYZ"})));
		a = new PGS_보석쇼핑();
		System.out.println(Arrays.toString(a.solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
		
	}
}
