import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
선분 위의 점
 */

public class BOJ11663 {
	static int arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());

		arr = new int[N];
		
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(minBinary(b) - maxBinary(a) + "\n");
		}
		
		System.out.print(sb);
	}

	private static int maxBinary(int target) {
		int start = 0;
		int end = arr.length - 1;
		int middle = 0;
		
		while(start <= end) {
			middle = (start + end) / 2;
			if(arr[middle] < target) start = middle + 1;
			else end = middle - 1;
		}
		
		return start;
	}

	private static int minBinary(int target) {
		int start = 0;
		int end = arr.length - 1;
		int middle = 0;
		
		while(start <= end) {
			middle = (start + end) / 2;
			if(arr[middle] > target) end = middle - 1;
			else start = middle + 1;
		}
		
		return end + 1;
	}
}
