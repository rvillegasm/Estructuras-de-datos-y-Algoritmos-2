import java.util.ArrayList;

/**
 * Coloring
 */
public class Coloring {

  public static boolean mColoring(Graph g,int m){
    int[] colors = new int[g.size()];
    return mColoring(g, 0, colors, m);
  }

  private static boolean isSafe(Graph g, int v, int[] colors, int c){
    ArrayList<Integer> succsessors = g.getSuccessors(v);
      for(int w : succsessors) {
        if(colors[w] == c && w < v) {
          return false;
        }
      }
      return true;
  }

  private static boolean mColoring(Graph g, int v , int[] colors, int m){
    if(m <= 0) {
      return false;
    }
    else if(v == g.size()) {
      return true;
    }
    else if(!isSafe(g,v,colors,m)){
      return mColoring(g,v,colors,m-1);
    }
    else {
      colors[v] = m;
      return mColoring(g,v+1,colors,colors[0]);
    }
  }
  

  public static void main(String[] args) {
    DigraphAL g = new DigraphAL(5);
    g.addArc(0, 1, 1);
    g.addArc(0, 3, 1);
    g.addArc(0, 2, 1);
    g.addArc(1, 0, 1);
    g.addArc(1, 3, 1);
    g.addArc(2, 0, 1);
    g.addArc(2, 3, 1);
    g.addArc(3, 2, 1);
    g.addArc(3, 0, 1);
    g.addArc(3, 1, 1);
    g.addArc(3, 4, 1);
    g.addArc(4, 3, 1);

    System.out.println(mColoring(g, 2));

  }
}