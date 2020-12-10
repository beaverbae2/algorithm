package Binary_Search;

public class BinarySearch_repeat {
	
	public static void main(String[] args) {
		int[] arr = {1,3,6,7,10,13};
		int answer = binary_search(arr,2,0, arr.length-1);
		
		if(answer<0) System.out.println("None");
		else System.out.println(answer);
	}

	private static int binary_search(int[] arr, int target, int start, int end) {
		while(true) {
			if(start>end) return -1;//target이 존재x
			
			int mid = (start+end)/2;
			System.out.println("mid : "+mid);
			
			if(target>arr[mid]) {
				start=mid+1;
			}else if(target<arr[mid]) {
				end = mid-1;
			}else return mid;
		}
		
	}
}
