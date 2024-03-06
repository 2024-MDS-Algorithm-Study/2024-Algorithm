import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 빗물
 */

public class BOJ14719 {
	static int dc[] = {-1, 1};
	static int H, W, water, map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		st = new StringTokenizer(br.readLine());
		
		// 1이면 블럭, 2이면 물이 채워져 있는 것
		for(int c = 0; c < W; c++) {
			int h = Integer.parseInt(st.nextToken());
			for(int r = 0; r < h; r++) {
				map[H - 1 - r][c] = 1;
			}
		}
		
		for(int c = 0; c < W; c++) {
			for(int r = 0; r < H; r++) {
				if(map[r][c] != 0) continue;
				
				map[r][c] = 2;

				// 오른쪽으로 
				int rWater = checkWater(r, c, 1);
				int lWater = checkWater(r, c, 0);

				if(rWater != 0 && lWater != 0) {
					water += (1 + rWater - 1 + lWater - 1);
				}
			}
		}
		System.out.println(water);
	}

	private static int checkWater(int r, int c, int i) {
		boolean flag = false;
		int cnt = 0;
		
		while(true) {
			c += dc[i];
			
			if(!check(c)) break;
			if(map[r][c] == 1) {
				flag = true;
				break;
			}
			
			cnt++;
			map[r][c] = 2;
		}
		
		if(flag) return cnt + 1;
		else return 0;
	}

	private static boolean check(int c) {
		return c >= 0 && c < W;
	}
}
