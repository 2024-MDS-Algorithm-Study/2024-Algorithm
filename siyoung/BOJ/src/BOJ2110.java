package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
* 공유기 설치
*/
public class BOJ2110 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int arr[] = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int min = 1;
		int max = arr[n-1] - arr[0];
		int res = 0, dist = 0;
		
		while(min<=max) {
			int mid = (min+max)/2;
			int start = arr[0];
			int cnt = 1;
			for(int i = 1; i<n; i++) {
				dist = arr[i] - start;
				if(dist >= mid) {
					cnt++;
					start = arr[i];
				}
			}
			
			if(cnt>=c) {
				res = mid;
				min = mid+1;
			}
			else {
				max = mid-1;
			}
		}
		System.out.println(res);
	}
}
