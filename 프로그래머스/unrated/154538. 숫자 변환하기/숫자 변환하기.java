import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int x, int y, int n) {        
        Queue<Integer> q = new LinkedList<>();
        
        int[] dist = new int[y+1];
        q.offer(x);
        dist[x] = 1;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == y) break;
            
            if (curr + n <= y && dist[curr + n] == 0) {
                dist[curr + n] = dist[curr] + 1;
                q.offer(curr + n);
            }
            if (curr * 2 <= y && dist[curr * 2] == 0) {
                dist[curr * 2] = dist[curr] + 1;
                q.offer(curr * 2);
            }
            if (curr * 3 <= y && dist[curr * 3] == 0) {
                dist[curr * 3] = dist[curr] + 1;
                q.offer(curr * 3);
            }
        }
        
        return dist[y] == 0 ? -1 : (dist[y] - 1);
    }
}