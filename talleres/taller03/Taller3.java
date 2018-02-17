import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase en la cual se implementan los metodos del Taller de Clase #3
 *
 * @author Rafael Villegas, Felipe Cortes, Mauricio Toro, Mateo Agudelo
 */
public class Taller3 {

	private static boolean puedoPonerReina(int r, int c, int[] tablero) {
		for (int i = 0; i < r; i++) {
			if (tablero[i] == c) {
				return false;
			}
			if (Math.abs(tablero[i] - c) == Math.abs(i - r)) {
				return false;
			}
		}
		return true;
	}

	public static int nReinas(int n) {
		int[] tablero = new int[n];

		return nReinas(0, n, tablero);
	}

	private static int nReinas(int r, int n, int[] tablero) {
		if (r == n) {
			imprimirTablero(tablero);
			return 1;
		}
		int s = 0;
		for (int i = 0; i < n; i++) {
			if (puedoPonerReina(r, i, tablero)) {
				tablero[r] = i;
				s = s + nReinas(r + 1, n, tablero);
			}
		}
		return s;
	}

	public static void imprimirTablero(int[] tablero) {
		int n = tablero.length;
		System.out.print("    ");
		for (int i = 0; i < n; ++i)
			System.out.print(i + " ");
		System.out.println("\n");
		for (int i = 0; i < n; ++i) {
			System.out.print(i + "   ");
			for (int j = 0; j < n; ++j)
				System.out.print((tablero[i] == j ? "Q" : "#") + " ");
			System.out.println();
		}
		System.out.println();
	}

	public static ArrayList<Integer> camino(DigraphAL g, int inicio, int fin) {
		ArrayList<Integer> camino = new ArrayList<Integer>();
		if (dfs(g, inicio, fin, camino)) {	
			return camino;
		}
		camino.clear();
		return camino;
	}

	private static boolean dfs(DigraphAL g, int nodo, int objetivo, ArrayList<Integer> camino) {
		camino.add(nodo);
		if(nodo == objetivo) {
			return true;
		}
		else if(g.lista.get(nodo).size() == 0) {
			return false;
		}
		for(int i = 0; i < g.lista.get(nodo).size(); i++) {
			if(!dfs(g, g.lista.get(nodo).get(i).getKey(), objetivo, camino)) {
				camino.remove(camino.size()-1);
			}
			if(camino.get(camino.size() - 1) == objetivo) {
				break;
			}
		}
		return camino.size() > 1 && camino.get(camino.size() - 1) == objetivo;
	}

	

	public static void main(String[] args) {

		System.out.println("Punto 1");
		System.out.println();
		nReinas(4);

		System.out.println("Punto 2");
		System.out.println();
		DigraphAL grafo = new DigraphAL(8);
		grafo.addArc(2, 3, 1);
		grafo.addArc(2, 7, 1);
		grafo.addArc(4, 3, 1);
		grafo.addArc(1, 7, 1);
		grafo.addArc(1, 6, 1);
		grafo.addArc(3, 0, 1);
		grafo.addArc(3, 5, 1);
		grafo.addArc(7, 5, 1);
		grafo.addArc(3, 6, 1);

		ArrayList<Integer> ans = camino(grafo, 2, 6);
		for(int num : ans) {
			System.out.print(num + " ");
		}
		System.out.println();
		ArrayList<Integer> ans2 = camino(grafo, 1, 0);
		for(int num : ans2) {
			System.out.print(num + " ");
		}
		System.out.println();
		ArrayList<Integer> ans3 = camino(grafo, 1, 5);
		for(int num : ans3) {
			System.out.print(num + " ");
		}
		System.out.println();

	}

}