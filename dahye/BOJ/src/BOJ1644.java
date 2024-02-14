import java.util.*;

/*
 * 소수의 연속합
 */

public class BOJ1644 {
	static boolean prime[];
	static int N;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		prime = new boolean[N + 1];
		Arrays.fill(prime, true);
		
		getDecimal();
		
		int start = 2;
		int end = 2;
		int sum = 2;
		int count = 0;
		
		while(start <= end) {
			if(sum < N) {
				while(end < prime.length - 1) {
					end++;
					if(prime[end]) break;
				}
				
				sum += end;
				
			} else if(sum >= N) {
				if(sum == N) count++;
				sum -= start;
				
				while(true) {
					start++;
					if(start > end || prime[start]) break;
				}
			}
		}
		System.out.println(count);
	}

	private static void getDecimal() {
		prime[0] = false;
		prime[1] = false;
		
		for(int i = 2; i <= Math.sqrt(N); i++) {
			if(!prime[i]) continue;
			for(int j = i * i; j <= N; j += i) {
				prime[j] = false;
			}
		}
	}
}
