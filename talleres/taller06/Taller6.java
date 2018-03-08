import java.util.ArrayList;

/**
 * Taller6
 */
public class Taller6 {

  public static int[] cambioGreedy(int n, int[] denominaciones) {
    int[] devolucion = new int[denominaciones.length];
    int monto = n;
    for(int i = 0; i < denominaciones.length; i++) {
      int contador = 0;
      while((monto-denominaciones[i])>=0){
        monto -= denominaciones[i];
        contador++;
      }
      devolucion[i]=contador;
    }
    return devolucion;
  }

  public static int recorridoMC(Graph g) {
    int inicio = 0;
    int actual = inicio;
    int auxActual= 0;
    int costoCamino = 0;
    int contV = 0;
    boolean[] visitados = new boolean[g.size()];
    while(contV != g.size()-1) {

      visitados[actual] = true;
      contV++;
      ArrayList<Integer> sucesores = g.getSuccessors(actual);
      int min = Integer.MAX_VALUE;

      for(int v : sucesores) {
        if(g.getWeight(actual, v) < min && !visitados[v]) {
          min = g.getWeight(actual, v);
          auxActual = v;
        }
      }
      actual = auxActual;
      costoCamino += min;
    }
    costoCamino += g.getWeight(actual, inicio);
    return costoCamino;  
  }

  // public static boolean hayCamino(boolean[] visitados) {
  //   for(boolean v : visitados) {
  //     if(v == false) {
  //       return true;
  //     }
  //   }
  //   return false;
  // }

  public static void main(String[] args) {
    // Punto 1
    int[] denominaciones = { 500, 300, 200, 50};
    int[] cambio = cambioGreedy(1000, denominaciones);
    for(int n : cambio) {
      System.out.println(n);
    }

    // Punto 2
    DigraphAL g3 = new DigraphAL(4);
    g3.addArc(0, 0, 0);
    g3.addArc(0, 1, 7);
		g3.addArc(0, 2, 15);
		g3.addArc(0, 3, 6);
		g3.addArc(1, 0, 2);
		g3.addArc(1, 1, 0);
		g3.addArc(1, 2, 7);
		g3.addArc(1, 3, 3);
		g3.addArc(2, 0, 9);
		g3.addArc(2, 1, 6);
		g3.addArc(2, 2, 0);
		g3.addArc(2, 3, 12);
		g3.addArc(3, 0, 10);
		g3.addArc(3, 1, 4);
		g3.addArc(3, 2, 8);
    g3.addArc(3, 3, 0);

    int costo = recorridoMC(g3);
    System.out.println(costo);
  }
  
}