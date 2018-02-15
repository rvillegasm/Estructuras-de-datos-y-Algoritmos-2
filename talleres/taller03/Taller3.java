import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase en la cual se implementan los metodos del Taller de Clase #3
 * 
 * @author Mauricio Toro, Mateo Agudelo
 */
public class Taller3 {
	
	private static boolean puedoPonerReina(int r, int c, int[] tablero) {
    for(int i = 0; i < tablero.length; i++) {
      if((Math.abs(tablero[r] - i) == Math.abs(r - i))) {
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
		if(r>n){
      imprimirTablero(tablero);
      return 1;
    }
    int s = 0;
    for(int i=0;i<n;i++){
      if(puedoPonerReina(r, i, tablero)){
        tablero[r]=i;
        s = s + nReinas(r+1, n, tablero);
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
	
	//public static ArrayList<Integer> camino(Digraph g, int inicio, int fin) {
		// complete...
	//}

	// recomendacion
	//private static boolean dfs(Digraph g, int nodo, int objetivo, boolean[] visitados, ArrayList<Integer> list) {
		// complete...
  //}
  
  public static void main(String[] args) {
    System.out.println(nReinas(4));
  }

}