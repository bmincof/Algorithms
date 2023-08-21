import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-21
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int[] dr = {1, -1, 0, 0};
        final int[] dc = {0, 0, 1, -1};

        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];
        int[][][] turn = new int[4][H][W];
        // {행, 열, 이전 방향, 여태 회전한 횟수}
        PriorityQueue<int[]> q = new PriorityQueue<>((n1, n2) -> n1[3] - n2[3]);

        for (int i = 0; i < H; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = line[j];
                for (int dir = 0; dir < 4; dir++) {
                    turn[dir][i][j] = Integer.MAX_VALUE;
                }

                if (map[i][j] == 'C' && q.isEmpty()) {
                    for (int dir = 0; dir < 4; dir++) {
                        q.offer(new int[]{i, j, dir, 0});
                        turn[dir][i][j] = 0;
                    }
                    map[i][j] = '.';
                }
            }
        }

        int minTurn = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[3] > turn[curr[2]][curr[0]][curr[1]]) {
                continue;
            }

            if (map[curr[0]][curr[1]] == 'C') {
                minTurn = Math.min(minTurn, curr[3]);
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = curr[0] + dr[dir];
                int nc = curr[1] + dc[dir];

                // 범위 밖이면 진행 x
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '*') continue;
                int newTurn = curr[3] + (curr[2] != dir ? 1 : 0);
                if (turn[dir][nr][nc] <= newTurn) continue;

                turn[dir][nr][nc] = newTurn;
                q.offer(new int[]{nr, nc, dir, newTurn});
            }
        }
        
        System.out.println(minTurn);
    }
}