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

        char[][] map = new char[M][N];

        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[M][N];

        int W = 0;
        int B = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (vis[i][j]) continue;

                q.offer(new int[]{i, j});
                vis[i][j] = true;
                int count = 1;

                while (!q.isEmpty()) {
                    int[] curr = q.poll();

                    for (int dir = 0; dir < 4; dir++) {
                        int nr = curr[0] + dr[dir];
                        int nc = curr[1] + dc[dir];

                        if (nr < 0 || nr >= M || nc < 0 || nc >= N) {
                            continue;
                        }
                        // 방문했거나 시작점과 값이 다르면 건너뛰기
                        if (vis[nr][nc] || map[nr][nc] != map[i][j]) {
                            continue;
                        }

                        count++;
                        q.offer(new int[]{nr, nc});
                        vis[nr][nc] = true;
                    }
                }

                W += map[i][j] == 'W' ? count * count : 0;
                B += map[i][j] == 'B' ? count * count : 0;
            }
        }

        System.out.println(W + " " + B);
    }
}