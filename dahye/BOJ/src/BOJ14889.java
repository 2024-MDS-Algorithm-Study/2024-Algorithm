import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 스타트와 링크
 */

public class BOJ14889 {
	static boolean v[];
	static int result, map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		result = Integer.MAX_VALUE;
		
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		v = new boolean[N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
						
			}
		}
		
		comb(0, 0, N);
		System.out.println(result);
	}

	private static void comb(int idx, int start, int k) {
		if(idx == k / 2) {
			
			int sumStart = 0;
			int sumLink = 0;
			
			for(int i = 0; i < v.length; i++) {
				for(int j = 0; j < v.length; j++) {
					if(i == j) continue;
					if(v[i] == v[j]) {
						if(v[i]) sumStart += map[i][j];
						else sumLink += map[i][j];
					}
				}
			}
			
			result = Math.min(result, Math.abs(sumStart - sumLink));
			return;
		}
		
		for(int i = start; i < k; i++) {
			if(v[i]) continue;
			v[i] = true;
			comb(idx + 1, i + 1, k);
			v[i] = false;
		}
	}
}
