package A2020_12_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1541_잃어버린_괄호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		
		//괄호 치기
		StringBuilder sb2 = new StringBuilder();
		sb2.append('(');
		for (int i = 0; i < s1.length(); i++) {
			char ch = s1.charAt(i);
			
			if(ch == '-') {
				sb2.append(')').append(ch).append('(');
			}else {
				sb2.append(ch);
				if(i==s1.length()-1) sb2.append(')');
			}
		}
		
		//덧셈(괄호 안에 존재) 계산
		String s2 = sb2.toString();
		String s3 = "";
		String temp3 = "";
		for (int i = 0; i < s2.length(); i++) {
			char ch = s2.charAt(i);
			if(ch=='(') temp3 = "";
			else if(ch==')') {
				String[] nums = temp3.split("[+]");
				int sum = 0;
				for(String num : nums) {
					sum += Integer.parseInt(num);
				}
				s3 += Integer.toString(sum);

			}
			else if(ch=='-') s3 += ch;
			else {
				temp3 += ch;
			}
		}
		
		//뺄샘 계산
		int result = 0;
		String[] nums = s3.split("[-]");
		result = Integer.parseInt(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			result -= Integer.parseInt(nums[i]);
		}
		System.out.println(result);
	}
}
