import java.util.ArrayList;
import java.util.Stack;
/**
 * Shortest
 */
public class Shortest {

  public static ArrayList<Integer> hayCaminoDFS(Graph g, int a, int b) {
    boolean visitados[] = new boolean[g.size()];
    ArrayList<Integer> camino = new ArrayList<Integer>();
    Stack<Integer> caminoAux = new Stack<Integer>();
    hayCaminoDFSAux(g, a, b, visitados, caminoAux);
    camino.add(a);
    while(!caminoAux.isEmpty()) {
      camino.add(caminoAux.pop());
    }
    return camino;
	}

	private static boolean hayCaminoDFSAux(Graph g, int v, int w, boolean[] visitados, Stack<Integer> caminoAux) {
		visitados[v] = true;
		ArrayList<Integer> sucesores = g.getSuccessors(v);
		for (int i = 0; i < sucesores.size(); i++) {
      if (!visitados[sucesores.get(i)] && (sucesores.get(i) == w || hayCaminoDFSAux(g, sucesores.get(i), w, visitados, caminoAux))) {
        caminoAux.push(sucesores.get(i));
        return true;
      }
		}
		return false;
	}
  
  public static void main(String[] args) {
    DigraphAL g = new DigraphAL(3);
    g.addArc(0, 1, 1);
    g.addArc(1, 0, 1);
    g.addArc(2, 1, 1);
    g.addArc(1, 2, 1);
    g.addArc(0, 2, 1);
	  g.addArc(2, 0, 1);
    ArrayList<Integer> res = hayCaminoDFS(g, 0, 2);
    for(int v : res){
      System.out.println(v);
    }
  }
}