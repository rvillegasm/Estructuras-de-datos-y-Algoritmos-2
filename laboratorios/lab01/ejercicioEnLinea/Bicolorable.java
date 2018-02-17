import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Bicolorable
 */
public class Bicolorable {

  public static boolean isBicolorable(Graph g) {

    HashSet<Integer> blue = new HashSet<Integer>();
    HashSet<Integer> red = new HashSet<Integer>();

    for(int i = 0; i < g.size; i++) {
      if(i==0){
        blue.add(i);
      }else if(blue.contains(i)){
        ArrayList<Integer> aux = g.getSuccessors(i);
        for(int j=0;j<aux.size();j++){
          red.add(aux.get(j));
        }
      }else{
        ArrayList<Integer> aux = g.getSuccessors(i);
        for(int k=0;k<aux.size();k++){
          blue.add(aux.get(k));
        }
      }
    }
    blue.retainAll(red);
    if(blue.size() == 0) {
      return true;
    }
    else {
      return false;
    }
  }

  public static void main(String[] args) throws IOException {

    int n = 0;
    int arc = 0;
    int m1 = 0;
    int m2 = 0;
    ArrayList<String> res = new ArrayList<String>();
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Ingrese un numero de nodos");
    n = Integer.parseInt(scan.readLine());

    while (n != 0) {
      DigraphAL graph = new DigraphAL(n);

      System.out.println("Ingrese un numero de arcos");
      arc = Integer.parseInt(scan.readLine());

      System.out.println("Ingrese los nodos que se conectan, separados por espacio (una sola relacion por linea)");
      for (int i = 0; i < arc; i++) {
        String[] aux = scan.readLine().split(" ");
        m1 = Integer.parseInt(aux[0]);
        m2 = Integer.parseInt(aux[1]);
        graph.addArc(m1, m2, 1);
      }
      if(isBicolorable(graph)) {
        res.add("BICOLORABLE");
      }
      else {
        res.add("NOT BICOLORABLE");
      }
      System.out.println("Ingrese un numero de nodos (0 si desea terminar y evaluar");
      n = Integer.parseInt(scan.readLine());
    }
    for(String s : res) {
      System.out.println(s);
    }
  }

}