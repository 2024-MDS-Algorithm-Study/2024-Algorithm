import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] board = new int[9][9];
    static List<Node> list = new ArrayList<>();
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = s.charAt(j) - '0';
                if (board[i][j] == 0) list.add(new Node(i, j));
            }
        }

        solve(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static boolean solve(int cnt) {
        if (cnt == list.size()) return true;

        int x = list.get(cnt).x;
        int y = list.get(cnt).y;
        for (int num = 1; num <= 9; num++) {
            if (check(x, y, num)) {
                board[x][y] = num;
                if (solve(cnt + 1)) return true;
            }
        }
        board[x][y] = 0;
        return false;
    }

    static boolean check(int x, int y, int num) {
        for (int i = 0; i < 9; i++)
            if (board[(x / 3) * 3 + i / 3][(y / 3) * 3 + i % 3] == num) return false;

        for (int i = 0; i < 9; i++)
            if (board[x][i] == num || board[i][y] == num) return false;

        return true;
    }
}
