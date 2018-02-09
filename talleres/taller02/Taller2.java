import java.util.ArrayList;
import java.lang.Math;

/**
 * Clase en la cual se implementan los metodos del Taller de Clase #2
 * 
 * @author Mauricio Toro, Mateo Agudelo, Rafael Villegas, Felipe Cortés
 */
public class Taller2 {

  public static int queenAns;

  public static ArrayList<String> combinations(String s) {
    ArrayList<String> listas = new ArrayList<String>();
    if (s == "") {
      return listas;
    } else {
      combinations("", s, listas);
      return listas;
    }
  }

  private static void combinations(String pre, String pos, ArrayList<String> list) {
    if (pos.length() == 0) {
      list.add(pre);
    } else {
      combinations(pre + pos.substring(0, 1), pos.substring(1), list);
      combinations(pre, pos.substring(1), list);
    }
  }

  public static ArrayList<String> permutations(String s) {
    ArrayList<String> listas = new ArrayList<String>();
    if (s == "") {
      return listas;
    } else {
      permutations("", s, listas);
      return listas;
    }
  }

  private static void permutations(String pre, String pos, ArrayList<String> list) {
    if (pos.length() == 0) {
      list.add(pre);
    } else {
      for (int i = 0; i < pos.length(); i++) {
        permutations(pre + pos.charAt(i), pos.substring(0, i) + pos.substring(i + 1), list);
      }
    }
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

  public static boolean esValido(int[] tablero) {
    for(int i = 0; i < tablero.length; i++) {
      for(int j = 0; j < tablero.length; j++) {
        if((Math.abs(tablero[i] - tablero[j]) == Math.abs(i - j)) && (i != j)) {
          return false;
        }
      }
    }
    return true;
  }

  private static void permutationsQueen(String pre, String pos, int[] tablero) {
    if (pos.length() == 0) {
      String respuesta = pre.substring(1);
      String[] respuesta2 = respuesta.split(",");
      for(int i = 0; i < respuesta2.length; i++) {
        tablero[i] = Integer.parseInt(respuesta2[i]);
      }
      if(esValido(tablero)) {
        imprimirTablero(tablero);
        queenAns++;
      }

    } else {
      for (int i = 0; i < pos.length(); i++) {
        permutationsQueen(pre + "," + pos.charAt(i), pos.substring(0, i) + pos.substring(i + 1), tablero);
      }
    }
  }

  public static int queens(int n) {
    queenAns = 0;
    int[] tablero = new int[n];
    String numeros = "";
    for(int i = 0; i < n; i++) {
      numeros = numeros + i;
    }
    permutationsQueen("", numeros, tablero);
    return queenAns;
  }
  public static void main(String[] args) {
    queens(4);
  }

}