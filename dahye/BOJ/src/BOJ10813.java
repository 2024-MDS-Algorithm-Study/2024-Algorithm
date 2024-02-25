import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 공 바꾸기
 */

public class BOJ10813 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N + 1];
		for(int i = 1; i < N + 1; i++) arr[i] = i;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int tmp = arr[a];
			
			arr[a] = arr[b];
			arr[b] = tmp;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N + 1; i++) {
			sb.append(arr[i] + " ");
		}
		
		System.out.println(sb);
	}
}
