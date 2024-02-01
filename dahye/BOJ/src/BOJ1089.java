import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
스타트링크 타워
 */

public class BOJ1089 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static String nums[] = {"###...#.###.###.#.#.###.###.###.###.###",
                            "#.#...#...#...#.#.#.#...#.....#.#.#.#.#",
                            "#.#...#.###.###.###.###.###...#.###.###",
                            "#.#...#.#.....#...#...#.#.#...#.#.#...#",
                            "###...#.###.###...#.###.###...#.###.###"};
    static char map[][];
    static ArrayList<Integer> availNums[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new char[5][N * 3 + (N - 1)];
        availNums = new ArrayList[N];
        for(int i = 0; i < availNums.length; i++) availNums[i] = new ArrayList<>();

        for(int r = 0; r < 5; r++) {
            String input = br.readLine();
            map[r] = input.toCharArray();
        }

        ArrayList<Point> points = new ArrayList<>();

        for(int k = 0; k < N; k++) {
            points.clear();

            for(int r = 0; r < 5; r++) {

                for(int c = 0; c < 3; c++) {
                    if(map[r][k * 4 + c] == '#') points.add(new Point(r, c));
                }
            }

            if(points.isEmpty()) {
                for(int i = 0; i < 10; i++) availNums[k].add(i);
            } else {
                checkNums(k, points);
            }
        }

        double result = 0;
        int totalResultCount = 1;

        for(int i = 0; i < availNums.length; i++) {
            if(availNums[i].size() == 0) continue;
            totalResultCount *= availNums[i].size();
        }

        int k = 0;
        for(int i = availNums.length - 1; i >= 0; i--) {
            long tmp = (int) Math.pow(10, k);
            long sum = 0;
            
            for(int num: availNums[i]) sum += tmp * num;
            if(N == 1) result += sum;
            else result += sum * (returnRemain(i));
            k++;
        }
        
        result = result * 1.0 / totalResultCount == 0 ? -1: result * 1.0 / totalResultCount;
        System.out.println(result);
    }

    private static long returnRemain(int idx) {
    		
    	int k = 1;
    	for(int i = 0; i < availNums.length; i++) {
    		if(i == idx) continue;
    		k *= availNums[i].size();
    	}
    	return k;
	}

	private static void checkNums(int idx, ArrayList<Point> points) {
        for(int num = 0; num < 10; num++) {
            boolean check = true;

            for(Point point: points) {
                if(!(nums[point.r].charAt(num * 4 + point.c) == '#')) {
                    check = false;
                    break;
                }
            }

            if(check) availNums[idx].add(num);
        }
    }
}
