import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 시그널
 */

public class BOJ16113 {
	static String nums[] = {"###.#.#######.################",
						    "#.#.#...#..##.##..#....##.##.#",
						    "#.#.#.###############..#######",
						    "#.#.#.#....#..#..##.#..##.#..#",
						    "###.#.######..#######..#######"};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		String input = br.readLine();
		char map[][] = new char[5][N / 5 + 2];
		
		for(int r = 0; r < 5; r++) {
			Arrays.fill(map[r], '.');
			for(int c = 0; c < map[0].length; c++) {
				if(c < N / 5) map[r][c + 1] = input.charAt((N / 5) * r + c);
			}
		}

		for(int k = 0; k < map[0].length - 2; k++) {
			M: for(int n = 0; n < 10; n++) {
				boolean flag = true;
				
				L: for(int r = 0; r < 5; r++) {
					for(int c = 0; c < 3; c++) {
						if(nums[r].charAt(n * 3 + c) != map[r][k + c]) {
							flag = false;
							break L;
						}
					}
				}
				
				if(flag) {
					k += 1;
					sb.append(n);
					break M;
				}
			}
		}
		
		System.out.println(sb);

	}
}
