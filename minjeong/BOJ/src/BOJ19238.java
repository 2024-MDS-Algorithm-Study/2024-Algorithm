import java.io.*;
import java.util.*;

public class BOJ19238 {
    static int n, m, fuel;
    static Map<Integer, Node> map = new HashMap<>();
    static int[][] del = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}, arr;
    static Node taxi;
    static class Node {
        int x;
        int y;
        int dist;
        int num;

        public Node(int x, int y, int dist, int num) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {
        makeInput();

        while (m-- > 0) {
            find();
            if (taxi == null || fuel <= 0) {
                System.out.println(-1);
                return;
            }

            int amount = move();
            if (amount < 0 || fuel - amount < 0) {
                System.out.println(-1);
                return;
            }
            fuel += amount;
        }

        System.out.println(fuel);
    }

    static void find() {
        Queue<Node> q = new ArrayDeque<>();
        Node customer = null;
        boolean[][] v = new boolean[n][n];
        v[taxi.x][taxi.y] = true;
        taxi.dist = 0;
        taxi.num = arr[taxi.x][taxi.y];
        q.offer(taxi);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node cur = q.poll();
                if (arr[cur.x][cur.y] > 0 && (customer == null || customer.x > cur.x || (customer.x == cur.x && customer.y > cur.y))) customer = cur;

                findNext(v, cur, q);
            }
            if (customer != null) {
                arr[customer.x][customer.y] = 0;
                fuel -= customer.dist;
                break;
            }
        }
        taxi = customer;
    }

    static int move() {
        Queue<Node> q = new ArrayDeque<>();
        Node dest = map.get(taxi.num);
        boolean[][] v = new boolean[n][n];
        v[taxi.x][taxi.y] = true;
        taxi.dist = 0;
        q.offer(taxi);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == dest.x && cur.y == dest.y) {
                taxi = cur;
                return cur.dist;
            }

            findNext(v, cur, q);
        }
        return -1;
    }

    static void findNext(boolean[][] v, Node cur, Queue<Node> q) {
        for (int i = 0; i < 4; i++) {
            int nx = cur.x + del[i][0];
            int ny = cur.y + del[i][1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || v[nx][ny] || arr[nx][ny] == -1) continue;
            v[nx][ny] = true;
            q.offer(new Node(nx, ny, cur.dist + 1, arr[nx][ny]));
        }
    }

    static void makeInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = -Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0, 0);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = i + 1;
            map.put(i + 1, new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0, 0));
        }
    }
}