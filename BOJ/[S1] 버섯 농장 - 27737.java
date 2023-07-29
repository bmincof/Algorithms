package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-29
public class Main {
    // x개의 포자를 사용하면 어떻게 심어도 x * K만큼의 칸에 버섯이 퍼짐
    // 버섯이 자랄 수 있는 칸의 개수를 세고 비교하면 됨
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        Queue<int[]> q = new LinkedList<>();

        // 지도 전처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 칸을 채우는데 사용한 포자의 개수
        int used = 0;

        // 모든 영역마다 BFS
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 버섯이 자랄 수 없는 곳이면 건너뛰기
                if (map[i][j] == 1) {
                    continue;
                }
                // 버섯이 자랄 수 있는 칸의 수
                int count = 0;

                // BFS
                q.offer(new int[] {i, j});
                map[i][j] = 1;

                while (!q.isEmpty()) {
                    int[] curr = q.poll();
                    // K칸을 채울 수 있다면 사용한 포자 +1
                    if (++count == K) {
                        used++;
                        count = 0;
                    }

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = curr[0] + dx[dir];
                        int ny = curr[1] + dy[dir];

                        // 지도 밖이거나 방문한 적 있거나 갈 수 없는 땅이면 제외
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1) continue;

                        // 방문 처리 후 큐에 넣기
                        map[nx][ny] = 1;
                        q.offer(new int[]{nx, ny});
                    }

                }
                // [1, K) 개의 칸을 채웠다면 포자 1개가 더 필요하므로 +1
                used += count != 0 ? 1 : 0;
            }
        }

        // 포자를 하나도 사용할 수 없거나 모두 사용해도 칸을 다 채우지 못한다면
        if (used == 0 || used > M) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println("POSSIBLE");
            System.out.println(M - used);
        }
    }
}
