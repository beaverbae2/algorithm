package A2020_12_18;

import java.util.*;

public class PGS_방금그곡 {
	private List<Music> list;
	private List<String> mList;
	
	static class Music {
		int num, runtime;
		String name;
		
		public Music(int num, int runtime, String name) {
			this.num = num;
			this.runtime = runtime;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Music [num=" + num + ", runtime=" + runtime + ", name=" + name + "]";
		}
	}
	
	public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		
		list = new ArrayList<>();
		mList = getMelodyList(m);
		
		for (int i = 0; i < musicinfos.length; i++) {
			String[] split = musicinfos[i].split("[,]");
			String start = split[0];
			String end = split[1];
			String name = split[2];
			String melody = split[3];
			
			List<String> melodyList = getMelodyList(melody);
			int runtime = getRuntime(start, end);
			List<String> trueMelodyList = getTrueMelody(melodyList, runtime);
			
			System.out.println("trueMelodyList : "+trueMelodyList);
			if (checkMelody(mList, trueMelodyList)) {
				list.add(new Music(i, runtime, name));
			}
		}
		sortList();
		
		System.out.println(list);
		System.out.println();
		if (!list.isEmpty()) {
			answer = list.get(0).name;
		}
		
		return answer;
	}

	public List<String> getMelodyList(String melody) {
		List<String> result = new ArrayList<>();
		
		int i = 0;
		while(true) {
			if(i >= melody.length()-1) break;
			
			char c1 = melody.charAt(i);
			char c2 = melody.charAt(i+1);
			String str = "";
			if(c2 == '#') {
				str += c1;
				str += c2;
				result.add(str);
				i++;
			}else {
				str += c1;
				result.add(str);
			}
			i++;
		}
			
		if (melody.charAt(melody.length()-1) != '#') {
			result.add(""+melody.charAt(melody.length()-1));
		}
		
		return result;
		
	}
	
	
	public List<String> getTrueMelody(List<String> melodyList, int runtime) {
		List<String> result = new ArrayList<>();
		
		int mod = melodyList.size();
		for (int i = 0; i < runtime; i++) {
			result.add(melodyList.get(i%mod));
		}
		
		return result;
	}
	
	public boolean checkMelody(List<String> melodyList, List<String> trueMelody) {
		if (melodyList.size() > trueMelody.size()) return false;
		
		int idx = 0;
		while(true) {
			if (idx == trueMelody.size()) break;
			
			for (int i = 0; i < melodyList.size(); i++) {
				if (i+idx > trueMelody.size()-1) break;
				
				else {
					String s1 = melodyList.get(i);
					String s2 = trueMelody.get(i+idx);
					
					if (!s1.equals(s2)) break;
					else if(i==melodyList.size()-1) return true;
				}
			}
			idx++;
		}
		
		return false;
	}


	public void sortList() {
		Collections.sort(list, new Comparator<Music>() {

			@Override
			public int compare(Music o1, Music o2) {
				if (o1.runtime != o2.runtime) {
					return Integer.compare(o2.runtime, o1.runtime);
				}else {
					return Integer.compare(o1.num, o2.num);
				}
			}
		});
	}

	public int getRuntime(String start, String end) {
		String[] split1 = start.split("[:]");
		String[] split2 = end.split("[:]");
		
		int start_hr = Integer.parseInt(split1[0]);
		int start_min = Integer.parseInt(split1[1]);
		
		int end_hr = Integer.parseInt(split2[0]);
		int end_min = Integer.parseInt(split2[1]);
		
		return (end_hr - start_hr) * 60 + (end_min - start_min);
	}

	public static void main(String[] args) {
		PGS_방금그곡 a = new PGS_방금그곡();
		System.out.println(a.solution("ABCDEFG#" , new String[] {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
		a = new PGS_방금그곡();
		System.out.println(a.solution("CC#BCC#BCC#BCC#B" , new String[] {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
		a = new PGS_방금그곡();
		System.out.println(a.solution("ABC" , new String[] {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
	}
}
