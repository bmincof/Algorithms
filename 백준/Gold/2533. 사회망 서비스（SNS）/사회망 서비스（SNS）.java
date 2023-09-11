import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-09-12
public class Main {
    // i번째 사람이 {얼리어답터일 때, 아닐 때} 얼리어답터의 최소 수
    static long[][] earlyAdopters;
    static boolean[] vis;
    // 트리형태의 관계
    static List<Integer>[] relations;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        earlyAdopters = new long[2][N + 1];
        vis = new boolean[N + 1];
        relations = new LinkedList[N + 1];
        for (int i = 1; i <= N; i++) {
            relations[i] = new LinkedList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            relations[u].add(v);
            relations[v].add(u);
        }

        System.out.println(dfs(1));
    }

    // 본인이 얼리어답터라면 자식노드들의 최소 얼리어답터의 수 합 + 1
    // 본인이 얼리어답터가 아니면 자식노드들이 얼리어답터인 경우들의 합
    static long dfs(int curr) {
        vis[curr] = true;
        long minEarlyAdopters = 0L;

        for (int next : relations[curr]) {
            if (vis[next]) {
                continue;
            }
            minEarlyAdopters += dfs(next);
            // curr이 얼리어답터가 아닐 때
            earlyAdopters[1][curr] += earlyAdopters[0][next];
        }

        // 얼리어답터일 때
        earlyAdopters[0][curr] = 1L + minEarlyAdopters;
        return Math.min(earlyAdopters[0][curr], earlyAdopters[1][curr]);
    }
}