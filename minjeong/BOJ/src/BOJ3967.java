import java.io.*;
import java.util.*;

public class BOJ3967 {
    static String res;
    static boolean flag;
    static List<Node> list = new ArrayList<>();
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][][] lines = {{{0, 4}, {1, 3}, {2, 2}, {3, 1}},
            {{0, 4}, {1, 5}, {2, 6}, {3, 7}},
            {{3, 1}, {3, 3}, {3, 5}, {3, 7}},
            {{1, 1}, {1, 3}, {1, 5}, {1, 7}},
            {{1, 1}, {2, 2}, {3, 3}, {4, 4}},
            {{1, 7}, {2, 6}, {3, 5}, {4, 4}}};
    static char[][] star;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        star = new char[5][9];
        boolean[] v = new boolean[12];
        for (int i = 0; i < 5; i++) {
            star[i] = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                if (star[i][j] >= 'A' && star[i][j] <= 'L') v[star[i][j] - 'A'] = true;
                else if (star[i][j] == 'x') list.add(new Node(i, j));
            }
        }

        dfs(0, v);
        System.out.println(res);
    }

    private static void dfs(int depth, boolean[] v) {
        if (flag || check()) return;

        if (depth == list.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < star.length; i++) {
                for (int j = 0; j < star[0].length; j++) sb.append(star[i][j]);
                sb.append('\n');
            }
            res = sb.toString();
            flag = true;
            return;
        }

        for (int i = 0; i < 12; i++) {
            if (v[i]) continue;
            v[i] = true;
            star[list.get(depth).x][list.get(depth).y] = (char) (i + 'A');
            dfs(depth + 1, v);
            v[i] = false;
            star[list.get(depth).x][list.get(depth).y] = 'x';
        }
    }

    static boolean check() {
        for (int i = 0; i < 6; i++) {
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                if (star[lines[i][j][0]][lines[i][j][1]] >= 'A' && star[lines[i][j][0]][lines[i][j][1]] <= 'L')
                    sum += (star[lines[i][j][0]][lines[i][j][1]] - 'A' + 1);
            }
            if (sum > 26) return true;
        }
        return false;
    }
}
