import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 부분합
 */

public class BOJ1806 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		long arr[] = new long[N];
	
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int end = 0;
		long sum = arr[0];
		
		int result = Integer.MAX_VALUE;
		
		while(start <= end) {
			if(sum < S) {
				if(end < N - 1) {
					end++;
					sum += arr[end];
				} else break;
			}
			else {
				result = Math.min(result, end - start + 1);
				sum -= arr[start];
				start++;
			}
		}
		
		System.out.println(result == Integer.MAX_VALUE ? 0 : result);
	}
}
