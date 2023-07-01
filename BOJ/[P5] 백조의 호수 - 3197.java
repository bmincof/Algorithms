package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-01
public class Main {
    static int SWAN1 = -2;
    static int SWAN2 = -2;

    static int R;
    static int C;
    static int[] group;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        final int ICE = -1;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int[][] melt = new int[R][C];
        group = new int[R*C];


        // 초기화
        for(int i = 0; i < R; i++) {
            char[] row = br.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                map[i][j] = row[j];
                melt[i][j] = -1;
                int idx = index(i, j);
                group[idx] = idx;

                if(map[i][j] == 'L') {
                    if(SWAN1 == -2) {
                        SWAN1 = idx;
                    } else {
                        SWAN2 = idx;
                    }
                    map[i][j] = 0;
                } else if(map[i][j] == 'X') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        // 바다 부분을 flood fill하며 그룹 초기화
        Queue<Point> q = new LinkedList<>();
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] == -1 || melt[r][c] != -1) continue;

                Queue<Point> tempQ = new LinkedList<>();
                tempQ.offer(new Point(r, c));
                melt[r][c] = 0;
                while(!tempQ.isEmpty()) {
                    Point curr = tempQ.poll();
                    int cdx = index(curr.r, curr.c);

                    for(int dir = 0; dir < 4; dir++) {
                        int nr = curr.r + dr[dir];
                        int nc = curr.c + dc[dir];

                        // 밖이면 건너뛰기
                        if(isOutside(nr, nc)) continue;
                        int ndx = index(nr, nc);

                        // 서로 다른 백조이면 정답 출력
                        if(isSwan(cdx) && isSwan(ndx) && find(cdx) != find(ndx)) {
                            // (0일, 0일)에 만났다면 (= 처음부터 이어져있음) 0을 출력
                            // (0일, 1일)에 만났다면 (= 빙판 1칸에 막혀있다면) 1을 출력
                            System.out.println(Math.max(melt[curr.r][curr.c], melt[nr][nc]));
                            return;
                        }
                        // 하나라도 백조가 아니면서 방문한 적 있으면 그룹지어주고 재방문하지 않음
                        // LX.X.XL 같은 상황 방지
                        if(melt[nr][nc] != -1) continue;

                        // 얼음이면 q에 담기
                        if(map[nr][nc] == ICE) {
                            q.offer(new Point(nr, nc));
                            melt[nr][nc] = melt[curr.r][curr.c] + 1;
                        } else {
                            tempQ.offer(new Point(nr, nc));
                            melt[nr][nc] = 0;
                        }
                        union(cdx, ndx);
                    }
                }
            }
        }

        // 백조가 언제 만나는지 찾기
        while(!q.isEmpty()) {
            Point curr = q.poll();
            int cdx = index(curr.r, curr.c);

            for(int dir = 0; dir < 4; dir++) {
                int nr = curr.r + dr[dir];
                int nc = curr.c + dc[dir];

                if(isOutside(nr, nc)) continue;
                int ndx = index(nr, nc);

                // 서로 다른 백조이면 정답 출력
                if(isSwan(cdx) && isSwan(ndx) && find(cdx) != find(ndx)) {
                    // (n일, n일)에 만났다면 (= 녹은 빙판끼리 인접해있음) n을 출력
                    // (n일, n+1일)에 만났다면 (= 빙판 1칸에 막혀있다면) n+1을 출력
                    System.out.println(Math.max(melt[curr.r][curr.c], melt[nr][nc]));
                    return;
                }
                // 하나라도 백조가 아니면서 방문한 적 있으면 그룹지어주고 재방문하지 않음
                union(cdx, ndx);
                if(melt[nr][nc] != -1) continue;

                // 얼음이면 q에 담기
                q.offer(new Point(nr, nc));
                melt[nr][nc] = melt[curr.r][curr.c] + 1;

            }
        }
    }

    // 위치 정보
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 그룹 대표 찾기
    static int find(int u) {
        if(group[u] == u) return u;

        return group[u] = find(group[u]);
    }

    // 그룹 합치기
    static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u == v) return;

        if(isSwan(u)) group[v] = u;
        else group[u] = v;
    }

    // 그룹번호로 변경
    static int index(int r, int c) {
        return r * C + c;
    }

    // 맵 밖인지
    static boolean isOutside(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    // 해당 영역에 백조가 있는지
    static boolean isSwan(int idx) {
        return find(idx) == SWAN1 || find(idx) == SWAN2;
    }
}
