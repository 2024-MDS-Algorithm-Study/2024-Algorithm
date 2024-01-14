package algo;
import java.util.*;
import java.io.*;
 /*
  * 전깃줄
  */
public class BOJ2565 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
        
		int[] dp = new int[n];	
		int[][] map = new int[n][2];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int max = 0;
		for(int i = 0; i < dp.length; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(map[i][1] > map[j][1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(max,  dp[i]);
		}
//		System.out.println(Arrays.toString(dp));
		
		System.out.println(n - max);
	}
}
