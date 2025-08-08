#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int NODE_CNT = 52;
const int MAX_INT = 1'000'000'000;

int ctoi(char c) {
    if (c >= 'a') return c - 'a';

    return c - 'A' + 26;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int N, total = 0;
    vector<int> pipes[NODE_CNT];
    int cap[NODE_CNT][NODE_CNT], flow[NODE_CNT][NODE_CNT] = {0};

    cin >> N;

    while (N--) {
        char u, v;
        int c;
        cin >> u >> v >> c;

        int from = ctoi(u);
        int to = ctoi(v);

        pipes[from].push_back(to);
        pipes[to].push_back(from);
        cap[from][to] = cap[to][from] += c;
    }

    int source = ctoi('A');
    int sink = ctoi('Z');

    // max flow 계산
    while (1) {
        int prev[NODE_CNT];
        fill(prev, prev + NODE_CNT, -1);
        queue<int> q;
        q.push(source);

        while (!q.empty() && prev[sink] == -1) {
            int curr = q.front();
            q.pop();

            for (int next : pipes[curr]) {
                // from -> next에 용량이 남아있고 아직 방문하지 않았을 경우
                if (cap[curr][next] - flow[curr][next] > 0 && prev[next] == -1) {
                    q.push(next);
                    prev[next] = curr;
                    if (next == sink) break;
                }
            }
        }

        // sink에 도달할 수 있는 경로가 남지 않았다면 종료
        if (prev[sink] == -1) break;

        int max_flow = MAX_INT;
        // 경로 상에서 흐를 수 있는 최대 유량 찾기
        for (int i = sink; i != source; i = prev[i]) {
            max_flow = min(max_flow, cap[prev[i]][i] - flow[prev[i]][i]);
        }
        // 찾은 최대 유량만큼 흘려보내기
        for (int i = sink; i != source; i = prev[i]) {
            flow[prev[i]][i] += max_flow;
            flow[i][prev[i]] -= max_flow;
        }
        total += max_flow;
    }

    cout << total;
}
