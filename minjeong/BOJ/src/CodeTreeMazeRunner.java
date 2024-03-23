import java.io.*;
import java.util.*;

public class CodeTreeMazeRunner {
    static int[][] map, del = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n, m, k, distSum;
    static Node exit;
    static Map<Integer, Node> participents;
    static Set<Integer>[][] positions;
    static class Node {
        int r;
        int c;
        int len;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        input();

        while (k-- > 0) {
            move();
            exitParticipents();
            if (participents.isEmpty()) {
                printRes();
                return;
            }
            rotate();
        }

        printRes();
    }

    private static void move() {
        for (int key : participents.keySet()) {
            Node participent = participents.get(key);
            int minDist = Math.abs(exit.r - participent.r) + Math.abs(exit.c - participent.c);
            Node movement = makeMovement(minDist, participent);

            if (movement.r != -1 && map[movement.r][movement.c] == 0) {
                positions[participent.r][participent.c].remove(key);
                positions[movement.r][movement.c].add(key);
                participent.r = movement.r;
                participent.c = movement.c;
                distSum++;
            }
        }
    }

    private static Node makeMovement(int minDist, Node participent) {
        int moveR = -1, moveC = -1;
        for (int i = 0; i < 4; i++) {
            int nr = participent.r + del[i][0];
            int nc = participent.c + del[i][1];
            if (nr <= 0 || nr > n || nc <= 0 || nc > n || map[nr][nc] != 0) continue;
            int nDist = Math.abs(nr - exit.r) + Math.abs(nc - exit.c);
            if (minDist > nDist) {
                moveR = nr;
                moveC = nc;
                minDist = nDist;
            }
        }
        return new Node(moveR, moveC);
    }

    private static void exitParticipents() {
        List<Integer> finished = new ArrayList<>();
        for (int key : participents.keySet()) {
            Node participent = participents.get(key);
            if (participent.r == exit.r && participent.c == exit.c) {
                positions[participent.r][participent.c].remove(key);
                finished.add(key);
            }
        }
        for (int participent : finished) participents.remove(participent);
    }

    private static void rotate() {
        Node startPosition = findStartPosition();
        int len = startPosition.len + 1;
        int r = startPosition.r;
        int c = startPosition.c;

        while (len >= 1) {
            if (len == 1 && map[r][c] > 0) map[r][c]--;

            for (int i = r; i < r + len - 1; i++) {
                int tmp = map[i][c];
                Node tmpExit = null;
                if (exit.r == i && exit.c == c) tmpExit = new Node(exit.r, exit.c);
                if (tmp > 0) tmp--;
                Set<Integer> tmpPosition = positions[i][c];

                rotateEliments(r + len - 1, c + i - r, i, c);
                rotateEliments(r + len - 1 - (i - r), c + len - 1, r + len - 1, c + i - r);
                rotateEliments(r, c + len - 1 - (i - r), r + len - 1 - (i - r), c + len - 1);
                map[r][c + len - 1 - (i - r)] = tmp;
                positions[r][c + len - 1 - (i - r)] = tmpPosition;
                if (tmpExit != null) rotateExit(i, c, r, c + len - 1 - (i - r));
            }
            len -= 2;
            r++;
            c++;
        }
        rotateParticipents();
    }

    private static void rotateEliments(int a, int b, int c, int d) {
        if (map[a][b] > 0) map[a][b]--;
        map[c][d] = map[a][b];
        positions[c][d] = positions[a][b];
        rotateExit(a, b, c, d);
    }

    private static void rotateParticipents() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int num : positions[i][j]) {
                    participents.get(num).r = i;
                    participents.get(num).c = j;
                }
            }
        }
    }

    private static Node findStartPosition() {
        int minLen = Integer.MAX_VALUE;
        Node startPosition = new Node(n + 1, n + 1);

        for (int key : participents.keySet()) {
            Node participent = participents.get(key);
            int tmpLen = Math.max(Math.abs(participent.r - exit.r), Math.abs(participent.c - exit.c));
            if (minLen >= tmpLen) {
                Node tmpStartPosition = makeStartPosition(tmpLen, participent);

                if (checkRenew(minLen, tmpLen, startPosition, tmpStartPosition)) {
                    minLen = tmpLen;
                    startPosition = tmpStartPosition;
                    startPosition.len = minLen;
                }
            }
        }
        return startPosition;
    }

    private static Node makeStartPosition(int len, Node participent) {
        Node startPosition = new Node(0, 0);
        startPosition.r = makePosition(participent.r, exit.r, len);
        startPosition.c = makePosition(participent.c, exit.c, len);
        return startPosition;
    }

    private static int makePosition(int p, int e, int len) {
        int s;
        if (p <= e) s = e - len;
        else s = p - len;

        if (s < 1) s = 1;
        return s;
    }

    private static boolean checkRenew(int minLen, int tmpLen, Node startPosition, Node tmpStartPosition) {
        return minLen > tmpLen
                || minLen == tmpLen && startPosition.r > tmpStartPosition.r
                || minLen == tmpLen && startPosition.r == tmpStartPosition.r && startPosition.c > tmpStartPosition.c;
    }

    private static void rotateExit(int r, int c, int toR, int toC) {
        if (exit.r == r && exit.c == c) {
            exit.r = toR;
            exit.c = toC;
        }
    }

    private static void printRes() {
        System.out.println(distSum);
        System.out.println(exit.r + " " + exit.c);
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        positions = new Set[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                positions[i][j] = new HashSet<>();
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        participents = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int r, c;
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            positions[r][c].add(i);
            participents.put(i, new Node(r, c));
        }

        st = new StringTokenizer(br.readLine());
        exit = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
}
