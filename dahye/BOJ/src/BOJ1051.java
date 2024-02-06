import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 숫자 정사각형
 */

public class BOJ1051 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		for(int r = 0; r < map.length; r++) {
			String input = br.readLine();
			map[r] = input.toCharArray();
		}
		
		int result = 1;
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				// k: 정사각형의 길이
				for(int k = 0; k < Math.min(map.length - r, map[0].length - c); k++) {
					if(map[r][c] == map[r][c + k] && map[r][c] == map[r + k][c] && map[r][c] == map[r + k][c + k]) {
						result = Math.max(result, k + 1);
					}
				}
			}
		}
		
		System.out.println(result * result);
	}
}
