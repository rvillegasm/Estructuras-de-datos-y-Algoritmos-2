import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BFSTest
{
    @Test
    public void bfs()
    {

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

      int[] answers = {0, 1, 2, 5, 3, 4};
      for(int i=0;i<answers.length;i++){
        if(answers[i] == BFS.hayCaminoBFS(g)){
          System.out.println(answers[i]);
        }
      }
    }
}