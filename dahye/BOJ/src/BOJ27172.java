import java.io.*;
import java.util.*;

/*
 * 수 나누기 게임
 */

public class BOJ27172 {
	
	public static void main(String[] args) throws Exception {
		LinkedHashMap<Integer, Integer> hashMap = new LinkedHashMap<Integer, Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int max = 0;
		int arr[] = new int[N];
		int result[] = new int[1_000_001];
		boolean check[] = new boolean[1_000_001];
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			check[num] = true;
			max = Math.max(max, num);
		}
		
		for(int i: arr) {
			for(int j = 2 * i; j < max + 1; j += i) {
				if(!check[j]) continue;
				result[i]++;
				result[j]--;
			}
		}

		for(int i: arr) {
			sb.append(result[i] + " ");
		}
		System.out.println(sb.toString());
	}
}