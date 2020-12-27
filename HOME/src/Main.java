import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[][] arr;
    static boolean[][][] visit;
    static int[][] drx;
    static int answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        answer = -1;
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1][2];
        drx = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        for (int i = 1; i < N + 1; i++) {
            String str = sc.next();
            char[] c = str.toCharArray();
            for (int j = 1; j < M + 1; j++) {
                arr[i][j] = c[j - 1] - 48;
                //System.out.print(arr[i][j]);
            }
            //System.out.println();
        }

        bfs();

        System.out.println(answer);

    }

    public static class Point {
        int x;
        int y;
        int b;
        int c;

        public Point(int x, int y, int b, int c) {
            this.x = x;
            this.y = y;
            this.b = b;
            this.c = c;
        }
    }


    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1, 1, 0, 1));
        visit[1][1][1] = true;
        visit[1][1][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            int b = p.b;
            int c = p.c;

            if (x == N & y == M) {
                answer = c;
                return;
            }

            for (int i = 0; i < drx.length; i++) {
                int nx = x + drx[i][0];
                int ny = y + drx[i][1];
                int nb = b;
                int nc = c;

                if (nx > 0 && nx < N + 1 && ny > 0 && ny < M + 1) {

                    if (arr[nx][ny] == 1 && nb == 0 && visit[nx][ny][1] == false) {
                        visit[nx][ny][1] = true;
                        q.add(new Point(nx, ny, 1, nc + 1));
                    }

                    if (arr[nx][ny] == 0 && visit[nx][ny][nb] == false) {
                        visit[nx][ny][b] = true;
                        q.add(new Point(nx, ny, b, nc + 1));
                    }

                }
            }
        }
    }

}