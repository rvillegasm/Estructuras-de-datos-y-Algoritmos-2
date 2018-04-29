import java.lang.Math;

/**
 * 
 */
public class LongestCommonSubsecuence {

  public static int lcs(String x, String y) {
    int[][] table = new int[x.length()+1][y.length()+1];

    for(int i = 0; i <= x.length(); i++) {
      table[i][0] = 0;
    }
    for(int j = 0; j <= y.length(); j++) {
      table[0][j] = 0;
    }

    for(int i = 1; i <= x.length(); i++) {
      for(int j = 1; j <= y.length(); j++) {
        
        if(x.charAt(i-1) == y.charAt(j-1)) {
          table[i][j] = table[i-1][j-1] + 1;
        }
        else {
          table[i][j] = Math.max(table[i][j-1], table[i-1][j]);
        }

      }
    }

    return table[x.length()][y.length()];
  }
  
  public static void main(String[] args) {
    System.out.println(lcs("CTGAC", "CGTATGC"));  
  }
}