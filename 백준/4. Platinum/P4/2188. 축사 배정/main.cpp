#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MAX_V = 402;
const int MAX_INT = 1'000'000'000;

struct Edge {
    int to, cap, flow;
    // 역방향 간선을 빠르게 찾아서 업데이트 하기 위함
    Edge *rev;
};

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int N, M;
    vector<Edge*> adj[MAX_V];

    cin >> N >> M;

    int source = 0;
    int sink = MAX_V - 1;

    for (int u = 1; u <= N; u++) {
        Edge *forward = new Edge{u, 1, 0};
        Edge *rev = new Edge{source, 0, 0};
        forward->rev = rev;
        rev->rev = forward;

        adj[source].push_back(forward);
        adj[u].push_back(rev);
    }

    for (int v = 201; v <= 200 + M; v++) {
        Edge *forward = new Edge{sink, 1, 0};
        Edge *rev = new Edge{v, 0, 0};
        forward->rev = rev;
        rev->rev = forward;

        adj[v].push_back(forward);
        adj[sink].push_back(rev);
    }

   for(int u = 1; u <= N; u++) {
       int S, v;
       cin >> S;
       while(S--) {
           cin >> v;
           v += 200;
           Edge *forward = new Edge{v, 1, 0};
           Edge *rev = new Edge{u, 0, 0};
           forward->rev = rev;
           rev->rev = forward;

           adj[u].push_back(forward);
           adj[v].push_back(rev);
       }
   }

   int max_flow = 0;
   while(1) {
       int prev[MAX_V];
       Edge *path[MAX_V] = {nullptr};
       fill(prev, prev + MAX_V, -1);

       queue<int> Q;
       Q.push(source);

       while(!Q.empty() && prev[sink] == -1) {
           int curr = Q.front();
           Q.pop();

           for(Edge *e : adj[curr]) {
               int next = e->to;
               if(e->cap - e->flow <= 0 || prev[next] > -1) continue;

               Q.push(next);
               prev[next] = curr;
               path[next] = e;
               if(next == sink) break;
           }
       }

       if(prev[sink] == -1) break;

       int mf = MAX_INT;
       for(int i = sink; i != source; i = prev[i]) {
           Edge *e = path[i];
           mf = min(mf, e->cap - e->flow);
       }
       for(int i = sink; i != source; i = prev[i]) {
           Edge *e = path[i];
           e->flow += mf;
           e->rev->flow -= mf;
       }
       max_flow += mf;
   }

   cout << max_flow;
}
