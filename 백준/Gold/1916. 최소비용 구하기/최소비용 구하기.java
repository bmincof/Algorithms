import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		int[] dist = new int[V+1];
		List<Edge>[] adj = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
			adj[i] = new ArrayList<>();
		}
		
		while(E-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Edge(v, w));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.v] != cur.w) continue;
			for(Edge next : adj[cur.v]) {
				if(dist[next.v] <= dist[cur.v] + next.w) continue;
				
				dist[next.v] = dist[cur.v] + next.w;
				pq.offer(new Edge(next.v, dist[next.v]));
			}
		}
		
		System.out.println(dist[end]);
		
	}
	
	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
		
	}
}