package A2020_12_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541_잃어버린_괄호_재현이형_풀이{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		String arr[] = line.split("-");
		int ans = 0, minus=0;
		for (int i = 0; i < arr.length; i++) {
			String[] tmp = arr[i].split("\\+");
			for (String string : tmp) {
				if(i==0)
					ans+=Integer.parseInt(string);
				else
					minus+=Integer.parseInt(string);
			}
		}
		System.out.println(ans-minus);
	}
}