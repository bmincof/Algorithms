import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author   : bmincof
// date     : 2023-08-13
public class Main {
    static int n;
    static List<int[]>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        adj = new List[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new LinkedList<>();
        }

        // 트리 만들기
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[u].add(new int[]{v, c});
            adj[v].add(new int[]{u, c});
        }

        int leaf = dfs(1)[0];
        System.out.println(dfs(leaf)[1]);
    }

    static int[] dfs(int root) {
        // 가장 먼 노드, 거리
        int farthest = root;
        int distance = 0;
        // 현재노드, 거리
        Deque<int[]> stack = new LinkedList<>();
        boolean[] vis = new boolean[n + 1];

        stack.push(new int[]{root, 0});
        vis[root] = true;

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            if (curr[1] > distance) {
                farthest = curr[0];
                distance = curr[1];
            }

            // 붙어 있는 간선 확인
            for (int[] next : adj[curr[0]]) {
                // 부모노드로 이어지는 간선이면 무시
                if (vis[next[0]]) {
                    continue;
                }
                // 거리 더해주기
                stack.push(new int[]{next[0], next[1] + curr[1]});
                vis[next[0]] = true;
            }
        }

        return new int[]{farthest, distance};
    }
}
