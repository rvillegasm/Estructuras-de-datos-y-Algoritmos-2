

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class NQueensTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NQueensTest
{
    @Test
    public void nQueens()
    {
        int[] answers = {1,0,0,2,10,4,40,92,352};
        for(int i=1;i<answers.length;i++){
            if(answers[i] == NQueens.queens(i)){
                System.out.println(answers[i]);
            }
        }
    }
}

