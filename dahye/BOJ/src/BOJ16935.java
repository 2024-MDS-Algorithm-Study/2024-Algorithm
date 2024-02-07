import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
배열 돌리기 3
 */

public class BOJ16935 {
    static int map[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < R; i++) {
            int num = Integer.parseInt(st.nextToken());
            switch(num) {
                case 1: map = func1(); break;
                case 2: map = func2(); break;
                case 3: map = func3(); break;
                case 4: map = func4(); break;
                case 5: map = func5(); break;
                case 6: map = func6(); break;
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j] + " ");
            } sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[][] func6() {
        int point[][] = {{0, 0},
                {0, map[0].length / 2},
                {map.length / 2, map[0].length / 2},
                {map.length / 2, 0}};

        int copy[][] = new int[map.length][map[0].length];

        for(int i = 0; i < 4; i++) {
            for(int r = 0; r < map.length / 2; r++) {
                for(int c = 0; c < map[0].length / 2; c++) {
                    copy[point[i][0] + r][point[i][1] + c] = map[point[(i + 1) % 4][0] + r][point[(i + 1) % 4][1] + c];
                }
            }
        }
        return copy;
    }


    private static int[][] func5() {
        int point[][] = {{0, 0},
                {0, map[0].length / 2},
                {map.length / 2, map[0].length / 2}, {map.length / 2, 0}};
        int copy[][] = new int[map.length][map[0].length];

        for(int i = 0; i < 4; i++) {
            for(int r = 0; r < map.length / 2; r++) {
                for(int c = 0; c < map[0].length / 2; c++) {
                    int nd = i - 1 < 0 ? i - 1 + 4 : i - 1;
                    copy[point[i][0] + r][point[i][1] + c] = map[point[nd][0] + r][point[nd][1] + c];
                }
            }
        }
        return copy;
    }

    private static int[][] func4() {
        int copy[][] = new int[map[0].length][map.length];

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                copy[copy.length - 1 - c][r] = map[r][c];
            }
        }
        return copy;
    }

    private static int[][] func3() {
        int copy[][] = new int[map[0].length][map.length];

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                copy[c][copy[0].length - 1 -r] = map[r][c];
            }
        }
        return copy;
    }

    private static int[][] func2() {
        int copy[][] = new int[map.length][map[0].length];
        for(int c = 0; c < map[0].length; c++) {
            for(int r = 0; r < map.length; r++) {
                copy[r][map[0].length - 1 - c] = map[r][c];
            }
        }

        return copy;
    }


    // 배열 상하 반전
    private static int[][] func1() {
        int copy[][] = new int[map.length][map[0].length];
        for(int r = 0; r < map.length; r++) {
            copy[copy.length - 1 - r] = Arrays.copyOf(map[r], map[r].length);
        }

        return copy;
    }
}