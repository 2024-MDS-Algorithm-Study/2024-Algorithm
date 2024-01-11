import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] del = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static List<Node>[][] balls;
    static int N, M, K, res;

    static class Node {
        int m;
        int s;
        int d;

        Node(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        makeInputs();

        for (int i = 0; i < K; i++) {
            List<Node>[][] tmpBalls = setList();
            stepOne(N, tmpBalls);
            stepTwo(N, tmpBalls);
            balls = tmpBalls;
        }

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                for (Node ball : balls[i][j]) res += ball.m;

        System.out.println(res);
    }

    private static void stepOne(int N, List<Node>[][] tmpBalls) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (balls[i][j].isEmpty()) continue;
                move(i, j, tmpBalls);
            }
        }
    }

    private static void move(int x, int y, List<Node>[][] tmpBalls) {
        for (Node ball : balls[x][y]) {
            int nx = (x + del[ball.d][0] * ball.s) % N;
            int ny = (y + del[ball.d][1] * ball.s) % N;
            if (nx <= 0) nx += N;
            if (ny <= 0) ny += N;
            tmpBalls[nx][ny].add(new Node(ball.m, ball.s, ball.d));
        }
    }

    private static void stepTwo(int N, List<Node>[][] tmpBalls) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (tmpBalls[i][j].size() < 2) continue;
                split(i, j, tmpBalls);
            }
        }
    }

    private static void split(int x, int y, List<Node>[][] tmpBalls) {
        int mSum = 0, sSum = 0, odd = 0;
        for (Node tmpBall : tmpBalls[x][y]) {
            mSum += tmpBall.m;
            sSum += tmpBall.s;
            if (tmpBall.d % 2 == 1) odd++;
        }

        int newM = mSum / 5;
        int newS = sSum / tmpBalls[x][y].size();
        int size = tmpBalls[x][y].size();
        tmpBalls[x][y].clear();
        if (newM == 0) return;

        int start = 1;
        if (odd == 0 || odd == size) start = 0;
        for (int i = start; i < 8; i += 2) {
            tmpBalls[x][y].add(new Node(newM, newS, i));
        }
    }

    private static void makeInputs() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        K = Integer.parseInt(inputs[2]);
        balls = setList();
        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int a, b;
            a = Integer.parseInt(inputs[0]);
            b = Integer.parseInt(inputs[1]);
            balls[a][b].add(new Node(
                    Integer.parseInt(inputs[2]),
                    Integer.parseInt(inputs[3]),
                    Integer.parseInt(inputs[4])
            ));
        }
    }

    private static List<Node>[][] setList() {
        List<Node>[][] list = new List[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                list[i][j] = new ArrayList<>();
            }
        }
        return list;
    }
}
