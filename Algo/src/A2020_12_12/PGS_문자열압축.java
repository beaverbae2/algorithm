package A2020_12_12;

import java.util.*;

public class PGS_문자열압축 {
	public int solution(String s) {
		int answer = Integer.MAX_VALUE;
		for (int len = 0; len < s.length(); len++) {
			int start = 0;
			int end = start+len;
			StringBuilder sb = new StringBuilder();
			TreeMap<String, Integer> map = new TreeMap<>();
			
			while(true) {
				if(start>s.length()-1) {
					int value =  map.get(map.firstKey());
					
					if(value==1) sb.append(map.firstKey());
					else sb.append(value).append(map.firstKey());
					
					break;
				}
				
				String str = "";
				if(end+1>s.length()-1) {
					str = s.substring(start, s.length());
				}else {
					str = s.substring(start, end+1);
				}
				if(map.get(str) == null) {
					if(!map.isEmpty()) {
						int value =  map.get(map.firstKey());
						
						if(value==1) sb.append(map.firstKey());
						else sb.append(value).append(map.firstKey());
						
						map.clear();
					}
					map.put(str, 1);
				}
				
				else if(map.get(str)>=1) {
					int value = map.get(str);
					map.put(str, value+1);
				}
				
				start = end+1;
				end = start+len;
			}
			answer = Math.min(answer, sb.toString().length());
		}
		
		return answer;
	}

	public static void main(String[] args) {
		PGS_문자열압축 a = new PGS_문자열압축();
		System.out.println(a.solution("aabbaccc"));
		a = new PGS_문자열압축();
		System.out.println(a.solution("ababcdcdababcdcd"));
		a = new PGS_문자열압축();
		System.out.println(a.solution("abcabcdede"));
		a = new PGS_문자열압축();
		System.out.println(a.solution("abcabcabcabcdededededede"));
		a = new PGS_문자열압축();
		System.out.println(a.solution("xababcdcdababcdcd"));
	}
}
