import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.HashSet;

/**
 * Clase en la cual se implementan los metodos del Taller de Clase #4
 * 
 * @author Rafael Villegas, Felipe Cortes, Mauricio Toro, Mateo Agudelo
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

	public static int minVertex(HashSet<Integer> q, int[] dist) {
		int min = Integer.MAX_VALUE;
		for(int vertex : q) {
			if(dist[vertex] < min) {
				min = vertex;
			}
		}
		return min;
	}

	public static void printPath(int[] dist, int[] prev, int start, int target) {
		Stack<Integer> s = new Stack<Integer>();
		int u = target;
		while (u != start) {
			s.push(u);
			u = prev[u];
		}
		s.push(u);

		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}

	/** camino mas corto entre dos veritces implementado con dijktra (camino de costo minimo) */
	public static int dijktra(Graph g, int start, int target) {
		
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
				printPath(dist, prev, start, target);
				return 0;
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
		return 1;

	}

	/** Prueba de Dijkstra */
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