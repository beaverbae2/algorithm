package A2020_12_18;

public class PGS_방금그곡solution {
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) {
		System.out
				.println(solution("ABC", new String[] { "12:00,12:14,HELLO,C#DEFGAB","13:00,13:05,WORLD,ABCDEF" }));
	}

	public static String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		String new_m = change(m);
		for (String str : musicinfos) {
			
			String song[] = str.split(",");
			String start[] = song[0].split(":");
			String end[] = song[1].split(":");

			int song_start = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
			int song_end = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

			String name = song[2];
			String type = song[3];
			type = change(type);
			StringBuilder sb = new StringBuilder();
			int time = song_end - song_start;
			
			for (int i = 0; i < time; i++) {
				sb.append(type.charAt(i%type.length()));
			}
			
			if (sb.toString().contains(new_m)) {
				if (MAX < time) {
					MAX = time;
					answer = name;
				}
			}
		}
		return answer;
	}

	// A,B,C,D,E,F,G A#-> H C# -> I D# -> J F# - >K G# - >L
	private static String change(String m) {
		String new_m = m.replace("A#", "H");
		new_m = new_m.replace("C#", "I");
		new_m = new_m.replace("D#", "J");
		new_m = new_m.replace("F#", "K");
		new_m = new_m.replace("G#", "L");
		return new_m;
	}
}