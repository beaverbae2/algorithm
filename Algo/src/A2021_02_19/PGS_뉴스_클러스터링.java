package A2021_02_19;

import java.util.*;

/**
 * 문제 똑바로 읽자
 * Set
 * @author beaverbae
 * 
 */

public class PGS_뉴스_클러스터링 {
	public int solution(String str1, String str2) {
		// str 변환 작업
		// 소문자로 만들기
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		
		// 집합 구하기 (중복 허용)
		int common = 0;// 교집합의 수
		int total = 0;// 합집합의 수
		LinkedHashMap<String, Integer> map1 = new LinkedHashMap<>();// 집합 A
		LinkedHashMap<String, Integer> map2 = new LinkedHashMap<>();// 집합 B
		
		// str1
		for (int i = 1; i < str1.length(); i++) {
			String sub_str = str1.substring(i-1, i+1);
			if (!isAlpabet(sub_str)) continue;
			
			if (map1.containsKey(sub_str)) {
				int cnt = map1.get(sub_str);
				map1.put(sub_str, cnt+1);
			} else {
				map1.put(sub_str, 1);
			}
		}
		
		// str2
		for (int i = 1; i < str2.length(); i++) {
			String sub_str = str2.substring(i-1, i+1);
			if (!isAlpabet(sub_str)) continue;
			
			if (map2.containsKey(sub_str)) {
				int cnt = map2.get(sub_str);
				map2.put(sub_str, cnt+1);
			} else {
				map2.put(sub_str, 1);
			}
		}

		Iterator<String> iter = map2.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			int v2 = map2.get(key);
			
			if (map1.containsKey(key)) {
				int v1 = map1.get(key);
				common += Math.min(v1, v2);
				map1.put(key, Math.max(v1, v2));
			} else {
				map1.put(key, v2);
			}
		}
		
		iter = map1.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			total += map1.get(key);
		}
		
		double j = 0;
		if (total == 0) {// 공집합
			j = 1;
		}else {
			j = ((double) common / (double) total);
		}
		
		int answer = (int) (j * 65536);
		return answer;
	}
	
	public boolean isAlpabet(String str) {
		char ch1 = str.charAt(0);
		char ch2 = str.charAt(1);
		
		return ch1>='a' && ch1<='z' && ch2>='a' && ch2<='z';
	}

	public static void main(String[] args) throws Exception {
		new PGS_뉴스_클러스터링().solution("FRANCE", "french");
		new PGS_뉴스_클러스터링().solution("handshake", "shake hands");
		new PGS_뉴스_클러스터링().solution("aa1+aa2", "AAAA12");
		new PGS_뉴스_클러스터링().solution("E=M*C^2", "e=m*c^2");
	}
}
