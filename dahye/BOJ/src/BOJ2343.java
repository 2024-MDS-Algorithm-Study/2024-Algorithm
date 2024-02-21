import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 기타 레슨
 */

public class BOJ2343 {
	static int arr[];
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		int max = 0;
		int total = 0;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
			max = Math.max(max, arr[i]);
		}
		
		int start = max;
		int end = total - 1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(getBlue(mid) > M) {
				start = mid + 1;
			} else end = mid - 1;
		}
		
		System.out.println(start);
	}

	private static int getBlue(int mid) {
		int sum = 0;
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			if(sum + arr[i] > mid) {
				sum = 0;
				count++;
			}
			
			sum += arr[i];
		}
		return count;
	}
}
