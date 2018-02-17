import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
/**
 * This class contains algorithms for digraphs
 * Adapted from: http://cs.fit.edu/~ryan/java/programs/graph/Dijkstra-java.html
 * @author Mauricio Toro
 * @version 1
 */
public class DigraphAlgorithms
{

  public static int maxSuccesors(Graph g) {
    int max = 0;
    int vMax = 0;
    int current = 0;
    ArrayList<Integer> listaS = new ArrayList<Integer>();
    for(int i = 0 ;i<g.size;i++){
      listaS = g.getSuccessors(i);
      current = listaS.size();
      if(current > max){
        vMax = i;
        max = current;
      }
      listaS.clear();
    }
    return vMax;
  }

  public static void colombianGraph() throws IOException {
    
    BufferedReader scan = new BufferedReader(new FileReader("medellin_colombia-grande.txt"));
    HashMap<String, Integer> map = new HashMap<String, Integer>(310153);
    String cadena = scan.readLine();
    int i = 0;
    int j = 0;
    DigraphAL colombia = new DigraphAL(310153);

    cadena = scan.readLine();

    try{
      while(!cadena.equals("")){
        String[] info = cadena.split(" ");
        map.put(info[0],i);
        i += 1;
        cadena = scan.readLine();
      }
      
      cadena = scan.readLine();
      cadena = scan.readLine();

      HashMap<String, Integer> weightsMap = new HashMap<String, Integer>();

      while(!cadena.equals("")) {
        String[] infoArc = cadena.split(" ");
        weightsMap.put(infoArc[2], j);
        colombia.addArc(map.get(infoArc[0]),
                        map.get(infoArc[1]),
                        weightsMap.get(infoArc[2])
                        );
        
        j += 1;
        cadena = scan.readLine();
      }
    }catch(NullPointerException e){
      System.out.println("Grafo Creado"); // excepcion necesaria para terminar la lectura del .txt
    }
  }

  private static int minVertex (int [] dist, boolean [] v) {
    int x = Integer.MAX_VALUE;
    int y = -1;   // graph not connected, or no unvisited vertices
    for (int i=0; i<dist.length; i++) {
      if (!v[i] && dist[i]<x) {y=i; x=dist[i];}
    }
      return y;
  }
  
    
  static int [] dijsktra(Graph dg, int source)
  {
          final int [] dist = new int [dg.size()];  // shortest known distance from "s"
         final int [] pred = new int [dg.size()];  // preceeding node in path
         final boolean [] visited = new boolean [dg.size()]; // all false initially
   
         for (int i=0; i<dist.length; i++) {
           dist[i] = Integer.MAX_VALUE; //Infinity
       }
        dist[source] = 0;
  
        for (int i=0; i<dist.length; i++) {
           final int next = minVertex (dist, visited);
           visited[next] = true;
  
           // The shortest path to next is dist[next] and via pred[next].
  
           final ArrayList<Integer> n = dg.getSuccessors (next); 
           for (int j=0; j<n.size(); j++) {
              final int v = n.get(j);
              final int d = dist[next] + dg.getWeight(next,v);
              if (dist[v] > d) {
                 dist[v] = d;
                 pred[v] = next;
              }
           }
        }
        return pred;  // (ignore pred[s]==0!)
  }
  
  
       public static ArrayList getPath (int [] pred, int s, int e) {
        final java.util.ArrayList path = new java.util.ArrayList();
       int x = e;
        while (x!=s) {
           path.add (0, x);
           x = pred[x];
        }
        path.add (0, s);
        return path;
     }
 
 // Código para dibujar el grafo en GraphViz
 // Recomiendo www.webgraphviz.com/
     public static void dibujarGrafo(Graph g)
     {
        System.out.println("digraph Grafo {");
        System.out.println("node [color=cyan, style=filled];");
        int nv = g.size();
        for (int i = 0; i < nv; i++)
        {
           ArrayList<Integer> lista = g.getSuccessors(i);
           for (int j = 0; j < lista.size(); j++)
             System.out.println("\"" + i + "\" -> \"" + lista.get(j) + "\" [ label=\""+ g.getWeight(i,lista.get(j)) +"\"];");
        }
        System.out.println("}");
     }
 
 
 public static void main(String[] args) throws IOException
 {
  colombianGraph();
     DigraphAL dgal = new DigraphAL(5);
     dgal.addArc(0,1,10);
     dgal.addArc(0,2,3);
     dgal.addArc(1,2,1);
     dgal.addArc(1,3,2);
     dgal.addArc(2,1,4);
     dgal.addArc(2,3,8);
     dgal.addArc(2,4,2);
     dgal.addArc(3,4,7);
     dgal.addArc(4,3,9);

     System.out.println("max succesor of AL: ");
     System.out.println(maxSuccesors(dgal));
     System.out.println("END Max");
     
     System.out.println(getPath(dijsktra(dgal,0),0,3));
     
     DigraphAM dgam = new DigraphAM(5);
     dgam.addArc(0,1,10);
     dgam.addArc(0,2,3);
     dgam.addArc(1,2,1);
     dgam.addArc(1,3,2);
     dgam.addArc(2,1,4);
     dgam.addArc(2,3,8);
     dgam.addArc(2,4,2);
     dgam.addArc(3,4,7);
     dgam.addArc(4,3,9);

     System.out.println("max succesor of AM: ");
     System.out.println(maxSuccesors(dgal));
     System.out.println("END Max");
     
     System.out.println(getPath(dijsktra(dgam,0),0,3)); 

     DigraphAlgorithms.dibujarGrafo(dgal);
     
 }
}