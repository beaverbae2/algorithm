package A2021_03_16;

import java.util.*;
import java.io.*;

public class BOJ_1085_직사각형에서_탈출 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		
		int min_x = Math.min(x, Math.abs(x-w));
		int min_y = Math.min(y, Math.abs(y-h));
		
		int ans = Math.min(min_x, min_y);
		System.out.println(ans);
	}
}
