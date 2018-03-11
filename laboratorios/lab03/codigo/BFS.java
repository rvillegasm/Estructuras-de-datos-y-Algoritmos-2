import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * BFS
 */
public class BFS {

  public static ArrayList<Integer> hayCaminoBFS(Graph g) {
    boolean visitados[] = new boolean[g.size()];
    ArrayList<Integer> c = new ArrayList<Integer>();
    hayCaminoBFSAux(g,0, visitados, c);
    return c;
	}

	private static void hayCaminoBFSAux(Graph g, int v , boolean[] visitados, ArrayList<Integer> c) {
		visitados[v] = true;
		Queue<Integer> cola = new LinkedList<Integer>();
		ArrayList<Integer> camino = new ArrayList<Integer>();
		cola.add(v);
		while (!cola.isEmpty()) {
      c.add(cola.peek());
			camino = g.getSuccessors(cola.poll());
			for (int sucesor : camino) {
				if (!visitados[sucesor]) {
					visitados[sucesor] = true;
					cola.add(sucesor);
				}
			}
		}
  }
  
  public static boolean hayCiclo(Graph g) {
    boolean visitados[] = new boolean[g.size()];
    ArrayList<Integer> c = new ArrayList<Integer>();
    return hayCicloAux(g, 0, visitados, c);
	}

	private static boolean hayCicloAux(Graph g, int v , boolean[] visitados, ArrayList<Integer> c) {
		visitados[v] = true;
		Queue<Integer> cola = new LinkedList<Integer>();
		ArrayList<Integer> camino = new ArrayList<Integer>();
		cola.add(v);
		while (!cola.isEmpty()) {
      c.add(cola.peek());
			camino = g.getSuccessors(cola.poll());
			for (int sucesor : camino) {
				if (!visitados[sucesor]) {
					visitados[sucesor] = true;
					cola.add(sucesor);
        }
        else {
          return true;
        }
			}
    }
    return false;
  }
}