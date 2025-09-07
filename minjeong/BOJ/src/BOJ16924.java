import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 십자가 찾기
public class BOJ16924 {
    static int[][] del = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static char[][] arr;
    static int n, m;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++)
                arr[i][j] = s.charAt(j);
        }

        int min = Math.min(n, m) / 2;
        int resCnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '.') continue;
                int max = calcMax(min, i, j);
                if (max > 0) {
                    arr[i][j] = 'c';
                    for (int l = 1; l <= max; l++) {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + del[k][0] * l;
                            int ny = j + del[k][1] * l;
                            arr[nx][ny] = 'c';
                        }
                    }
                    resCnt++;
                    sb.append(i + 1).append(" ").append(j + 1).append(" ").append(max).append("\n");
                }
            }
        }

        if (canMake(arr)) {
            System.out.println(resCnt);
            System.out.println(sb);
        }
        else System.out.println(-1);
    }

    static int calcMax(int min, int i, int j) {
        int max = 0;
        for (int l = 1; l <= min; l++) {
            boolean passed = true;
            for (int k = 0; k < 4; k++) {
                int nx = i + del[k][0] * l;
                int ny = j + del[k][1] * l;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] == '.') {
                    passed = false;
                    break;
                }
            }
            if (passed) max = l;
            else break;
        }
        return max;
    }

    private static boolean canMake(char[][] arr) {
        int dotCnt = 0;

        for (char[] chars : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                if (chars[j] == '*') return false;
                if (chars[j] == '.') dotCnt++;
            }
        }
        return dotCnt != arr.length * arr[0].length;
    }
}
