import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;

/**
 * TSP
 */
public class TSP {

  public static int minVertex(HashSet<Integer> q, int[] dist) {
		int min = Integer.MAX_VALUE;
		for(int vertex : q) {
			if(dist[vertex] < min) {
				min = vertex;
			}
		}
		return min;
	}

	public static ArrayList<Integer> printPath(int[] dist, int[] prev, int start, int target) {
		Stack<Integer> s = new Stack<Integer>();
		int u = target;
		while (u != start) {
			s.push(u);
			u = prev[u];
		}
		s.push(u);

    ArrayList<Integer> path = new ArrayList<>();
		while(!s.isEmpty()) {
      //System.out.println(s.pop());
      path.add(s.pop());
    }
    
    return path;
	}

	/** camino mas corto entre dos veritces implementado con dijktra (camino de costo minimo) */
	public static ArrayList<Integer> dijktra(Graph g, int start, int target) {
		
		int[] dist = new int[g.size()];
		int[] prev = new int[g.size()];
		HashSet<Integer> q = new HashSet<Integer>(g.size());

		for(int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			q.add(i);
		}

		dist[start] = 0;

		while(!q.isEmpty()) {

			int u = minVertex(q, dist);

			q.remove(u);
			if(u == target) {
				return printPath(dist, prev, start, target);
			}

			ArrayList<Integer> successors = g.getSuccessors(u);
			for(int v : successors) {
				int alt = dist[u] + g.getWeight(u, v);
				if(alt < dist[v]) {
					dist[v] = alt;
					prev[v] = u;
				}
			}
    }
    ArrayList<Integer> vacio = new ArrayList<Integer>();
		return vacio;
  }
  
  public static void main(String[] args) {
    DigraphAL g = new DigraphAL(6);
		g.addArc(0, 1, 7);
		g.addArc(0, 2, 9);
		g.addArc(0, 5, 14);
		g.addArc(1, 2, 10);
		g.addArc(1, 3, 15);
		g.addArc(2, 5, 2);
		g.addArc(2, 3, 11);
		g.addArc(3, 4, 6);
		g.addArc(5, 4, 9);
		int res = dijktra(g, 0, 4);
  }

} 