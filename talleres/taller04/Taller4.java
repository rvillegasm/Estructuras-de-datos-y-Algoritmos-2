import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase en la cual se implementan los metodos del Taller de Clase #4
 * 
 * @author Mauricio Toro, Mateo Agudelo
 */
public class Taller4 {

	public static ArrayList<Integer> profundidad(Graph g, int start) {
		ArrayList<Integer> camino = new ArrayList<Integer>();
		boolean[] visitados = new boolean[g.size];
		profundidad(g, start, camino, visitados);
		return camino;
	}

	public static void profundidad(Graph g, int start, ArrayList<Integer> camino, boolean[] visitados) {
		visitados[start] = true;
		camino.add(start);
		ArrayList<Integer> sucesores = g.getSuccessors(start);
		for (int sucesor : sucesores) {
			if (!visitados[sucesor]) {
				profundidad(g, sucesor, camino, visitados);
			}
		}
	}

	public static ArrayList<Integer> anchura(Graph g, int start) {
		ArrayList<Integer> camino = new ArrayList<Integer>();
		boolean[] visitados = new boolean[g.size];
		anchura(g, start, camino, visitados);
		return camino;
	}

	public static void anchura(Graph g, int start, ArrayList<Integer> camino, boolean[] visitados) {
		visitados[start] = true;
		Queue<Integer> cola = new LinkedList<Integer>();
		cola.add(start);
		while (!cola.isEmpty()) {
			camino = g.getSuccessors(cola.poll());
			for (int sucesor : camino) {
				if (!visitados[sucesor]) {
					visitados[sucesor] = true;
					cola.add(sucesor);
				}
			}
		}
	}

	// tomado de respuestas taller 3
	public static boolean hayCaminoDFS(Graph g, int a, int b) {
		boolean visitados[] = new boolean[g.size()];
		return hayCaminoDFSAux(g, a, b, visitados);
	}

	private static boolean hayCaminoDFSAux(Graph g, int v, int w, boolean[] visitados) {
		visitados[v] = true;
		ArrayList<Integer> sucesores = g.getSuccessors(v);
		for (int i = 0; i < sucesores.size(); i++) {
			if (!visitados[sucesores.get(i)] && (sucesores.get(i) == w || hayCaminoDFSAux(g, sucesores.get(i), w, visitados)))
				return true;
		}
		return false;
	}

	public static boolean hayCaminoBFS(Graph g, int a, int b) {
		boolean visitados[] = new boolean[g.size()];
		return hayCaminoDFSAux(g, a, b, visitados);
	}

	private static boolean hayCaminoBFSAux(Graph g, int v, int w, boolean[] visitados) {
		visitados[v] = true;
		Queue<Integer> cola = new LinkedList<Integer>();
		ArrayList<Integer> camino = new ArrayList<Integer>();
		cola.add(v);
		while (!cola.isEmpty()) {
			if (cola.peek() == w) {
				camino = g.getSuccessors(cola.poll());
				return true;
			} else {
				camino = g.getSuccessors(cola.poll());
				for (int sucesor : camino) {
					if (!visitados[sucesor]) {
						visitados[sucesor] = true;
						cola.add(sucesor);
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
	
	}
}