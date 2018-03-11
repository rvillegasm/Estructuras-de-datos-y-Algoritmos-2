import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * ShortestBT
 */
public class ShortestBT {

  public static void main(String[] args) throws IOException {
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    String aux = scan.readLine();
    String[] auxS = aux.split(" ");

    int n = Integer.parseInt(auxS[0]);
    int m = Integer.parseInt(auxS[1]);

    DigraphAL g = new DigraphAL(n);
    for(int i = 0; i < m; i++) {
      String auxArc = scan.readLine();
      String[] auxArcS = auxArc.split(" ");
      g.addArc(Integer.parseInt(auxArcS[0])-1, Integer.parseInt(auxArcS[1])-1 , Integer.parseInt(auxArcS[2]));
    }

    Pair<ArrayList<Integer>, Integer> ans = shortest(g, 0, m-1);
    for(int v : ans.getKey()) {
      System.out.print(v+1+" ");
    }
    System.out.println();
  }
  
  public static Pair<ArrayList<Integer>, Integer> shortest(Graph g, int a, int b) {
    boolean visitados[] = new boolean[g.size()];
    ArrayList<Integer> camino = new ArrayList<Integer>();
    Stack<Integer> caminoAux = new Stack<Integer>();
    int peso = 0;
    if(shortest(g, a, b, visitados, caminoAux, peso)) {
      camino.add(a);
      while(!caminoAux.isEmpty()) {
        camino.add(caminoAux.pop());
      }
    }
    else {
      camino.add(-1);
    }
    Pair<ArrayList<Integer>, Integer> res = new Pair<ArrayList<Integer>, Integer>(camino,peso);
    return res;
	}

	private static boolean shortest(Graph g, int v, int w, boolean[] visitados, Stack<Integer> caminoAux, int peso) {
		visitados[v] = true;
		ArrayList<Integer> sucesores = g.getSuccessors(v);
		for (int i = 0; i < sucesores.size(); i++) {
      if (!visitados[sucesores.get(i)] && (sucesores.get(i) == w || shortest(g, sucesores.get(i), w, visitados, caminoAux, peso))) {
        caminoAux.push(sucesores.get(i));
        peso += g.getWeight(v, sucesores.get(i));
        return true;
      }
		}
		return false;
  }
}