import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * BrokenNQueens
 */
public class BrokenNQueens {

  public static void main(String[] args) throws IOException {
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Integer> col = new ArrayList<Integer>();
    ArrayList<Integer> fil = new ArrayList<Integer>();
    ArrayList<Integer> res = new ArrayList<Integer>();

    int n = Integer.parseInt(scan.readLine());

    while(n != 0) {
      for (int i = 0; i < n; i++) {
        String fila = scan.readLine();
        for (int j = 0; j < fila.length(); j++) {
          char position = fila.charAt(j);
          if (position == '*') {
            col.add(j);
            fil.add(i);
          }
        }
      }
      res.add(queens(n, fil, col));
      n = Integer.parseInt(scan.readLine());
      col.clear();
      fil.clear();
    }

    for(int i = 1; i <= res.size(); i++){
      System.out.println("case " + i + ": " + res.get(i-1));
    }
  }

  public static int queenAns;

  public static boolean esValido(int[] tablero, ArrayList<Integer> fil, ArrayList<Integer> col) {
    for(int i = 0; i < tablero.length; i++) {

      if(fil.size() != 0) { 
        for(int k=0;k<fil.size();k++){
          if(fil.get(k)==tablero[i] && col.get(k)==i){
            return false;
          }
        }
      }
      
      for(int j = 0; j < tablero.length; j++) {
        if((Math.abs(tablero[i] - tablero[j]) == Math.abs(i - j)) && (i != j)) {
          return false;
        }
      }
      
    }
    return true;
  }

  private static void permutationsQueen(String pre, String pos, int[] tablero, ArrayList<Integer> fil, ArrayList<Integer> col) {
    if (pos.length() == 0) {
      String respuesta = pre.substring(1);
      String[] respuesta2 = respuesta.split(",");
      for(int i = 0; i < respuesta2.length; i++) {
          tablero[i] = Integer.parseInt(respuesta2[i]);
        
      }
      if(esValido(tablero, fil, col)) {
        queenAns++;
      }

    } else {
      for (int i = 0; i < pos.length(); i++) {
        permutationsQueen(pre + "," + pos.charAt(i), pos.substring(0, i) + pos.substring(i + 1), tablero, fil, col);
      }
    }
  }

  public static int queens(int n, ArrayList<Integer> fil, ArrayList<Integer> col) {
    queenAns = 0;
    int[] tablero = new int[n];
    String numeros = "";
    for(int i = 0; i < n; i++) {
      numeros = numeros + i;
    }
    permutationsQueen("", numeros, tablero, fil, col);
    return queenAns;
  }
}