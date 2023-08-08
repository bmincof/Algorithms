import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-08
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        // 지도 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[N][M];
        boolean canReach = false;

        for (int j = 0; j < M; j++) {
            if (vis[0][j] || map[0][j] == '1') continue;

            q.offer(new int[]{0, j});
            vis[0][j] = true;

            while (!q.isEmpty()) {
                int[] curr = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nr = curr[0] + dr[dir];
                    int nc = curr[1] + dc[dir];

                    if (nr < 0 || nc < 0 || nc >= M) {
                        continue;
                    }
                    if (nr == N) {
                        canReach = true;
                        break;
                    }
                    if (vis[nr][nc] || map[nr][nc] == '1') {
                        continue;
                    }

                    q.offer(new int[]{nr, nc});
                    vis[nr][nc] = true;
                }

                if (canReach) break;
            }
        }
        System.out.println(canReach ? "YES" : "NO");
    }
}