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
    public void nReinas()
    {
        boolean[] answers = {true,false,false,true,true};
        for(int i=1;i<answers.length;i++){
            if(answers[i] == NQueens.nReinas(i)){
                System.out.println(true);
            }
        }
    }
}