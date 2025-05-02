#include <iostream>

using namespace std;

// 부모노드의 번호
int parent[100010];
// 부모노드보다 얼마나 무거운 지
int heavier[100010];

// 부모 노드와 무게 차이를 갱신하면서 find
int find(int u) {
    if (parent[u] == u) {
        return u;
    }

    int parent_u = find(parent[u]);
    heavier[u] += heavier[parent[u]];

    return parent[u] = parent_u;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int N, M;

    while(true) {
        cin >> N >> M;
        if (N == 0 && M == 0) break;

        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
            heavier[i] = 0;
        }

        for (int i = 0; i < M; ++i) {
            char cmd;
            int a, b, w;

            cin >> cmd >> a >> b;
            find(a);
            find(b);

            if (cmd == '!') {
                cin >> w;

                if (parent[a] == parent[b]) continue;

                // 기존 b의 부모 노드와의 방향성을 반대로
                heavier[parent[b]] = -heavier[b];
                parent[parent[b]] = b;

                heavier[b] = w;
                parent[b] = a;
            }

            if (cmd == '?') {
                if (parent[a] != parent[b]) {
                    cout << "UNKNOWN\n";
                } else {
                    cout << heavier[b] - heavier[a] << "\n";
                }
            }
        }
    }

    return 0;
}