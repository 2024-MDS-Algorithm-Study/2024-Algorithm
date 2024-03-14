import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 킹
 */
public class BOJ1063 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Point king = toPosition(st.nextToken());
		Point stone = toPosition(st.nextToken());
		
		int num = Integer.parseInt(st.nextToken());
		
		while(num-- > 0) {
			int dir = getDir(br.readLine());
			
			int nr = king.r + dr[dir];
			int nc = king.c + dc[dir];
			
			int sr = stone.r;
			int sc = stone.c;
			if(stone.r == nr && stone.c == nc) {
				sr = stone.r + dr[dir];
				sc = stone.c + dc[dir];
			}
			
			if(!check(nr, nc) || !check(sr, sc)) continue;
			king.r = nr;
			king.c = nc;
			
			stone.r = sr;
			stone.c = sc;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(getPosition(king) + "\n" + getPosition(stone));
		
		System.out.println(sb);
	}
	private static String getPosition(Point p) {
		return (char)(p.c + 'A') + String.valueOf(p.r + 1);
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 8 && nc >= 0 && nc < 8;
	}
	private static int getDir(String s) {
		if(s.equals("R")) return 3;
		else if(s.equals("L")) return 7;
		else if(s.equals("B")) return 1;
		else if(s.equals("T")) return 5;
		else if(s.equals("RT")) return 4;
		else if(s.equals("LT")) return 6;
		else if(s.equals("RB")) return 2;
		else return 0;
	}
	private static Point toPosition(String s) {
		return new Point(s.charAt(1) - '0' - 1, s.charAt(0) - 'A');
	}
}
