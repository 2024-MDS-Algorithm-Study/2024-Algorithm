package algo;
import java.util.*;
import java.io.*;
 /*
  * 호텔
  */
public class BOJ1106 {
    public static void main(String args[]) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int c = Integer.parseInt(st.nextToken());
    	int n = Integer.parseInt(st.nextToken());
    	
    	int[][] map = new int[n][2];
    	int[] dp = new int[c+1];
    	
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		map[i][1] = Integer.parseInt(st.nextToken()); //비용
    		map[i][0] = Integer.parseInt(st.nextToken()); //고객 수
    	}
    	Arrays.sort(map, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
    	Arrays.fill(dp, 200001);
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<=map[i][0]; j++) {
    			if(j>c) break;
    			dp[j] = Math.min(dp[j], map[i][1]);
    		}
    		for(int j=map[i][0]+1; j<=c; j++) {
    			dp[j] = Math.min(dp[j], dp[j-map[i][0]]+map[i][1]);
    		}
//        	System.out.println(Arrays.toString(dp));
    		
    	}
    	System.out.println(dp[c]);
    }
}