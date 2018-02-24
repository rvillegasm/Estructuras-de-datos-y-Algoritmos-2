/**
 * NQueens
 */
public class NQueens {

  public static int queenAns;

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
        // imprimirTablero(tablero);
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
    queens(8);
  }
  
}