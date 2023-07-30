package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-31
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 치즈의 위치를 나타내는 배열 (있으면 1, 없으면 0)
        int[][] cheese = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 치즈가 모두 없어지는데 걸리는 날짜
        int day = -1;
        // 치즈가 모두 없어졌으면 false
        boolean isRemain = true;

        Queue<int[]> q = new LinkedList<>();

        // BFS
        while (isRemain) {
            day++;
            isRemain = false;

            // 치즈가 공기에 닿은 칸 수
            int[][] nearAir = new int[N][M];
            q.offer(new int[] {0, 0});

            while (!q.isEmpty()) {
                int[] curr = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = curr[0] + dx[dir];
                    int ny = curr[1] + dy[dir];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                        continue;
                    }
                    // 공기이면
                    if (cheese[nx][ny] == 0 && nearAir[nx][ny] == 0) {
                        nearAir[nx][ny]++;
                        q.offer(new int[] {nx, ny});
                    // 치즈이고 공기에 2칸 미만으로 닿았다면
                    } else if (cheese[nx][ny] == 1 && nearAir[nx][ny] < 2) {
                        // 치즈가 남아있다는 뜻이므로 remain을 true로
                        isRemain = true;
                        // 공기와 새로 접촉했으므로 공기와 닿은 칸 수 + 1
                        if (++nearAir[nx][ny] == 2) {
                            // 공기로 변경해서 다음 순회에서 공기역할을 하도록
                            cheese[nx][ny] = 0;
                        }
                    }
                }
            }
        }

        System.out.println(day);
    }
}
