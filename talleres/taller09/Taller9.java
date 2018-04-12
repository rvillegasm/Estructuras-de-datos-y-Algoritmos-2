/**
 * Taller9
 */
public class Taller9 {

  public static int lavenshtein(String a, String b) {
    int al = a.length() + 1;
    int bl = b.length() + 1;
    int [][] matrix = new int[al][bl];

    for(int i = 0; i < al; i++){
      matrix[i][0] = i;
    }

    for(int j = 0; j < bl; j++){
      matrix[0][j] = j;
    }

    for(int j = 1; j < bl; j++){
      for(int i = 1; i < al; i++){
        if(a.charAt(i - 1)==b.charAt(j - 1)){
          matrix[i][j] = matrix[i - 1][j - 1];
        }else{
          matrix[i][j] = Math.min(matrix[i - 1][j - 1] + 1, Math.min(matrix[i][j - 1] + 1, matrix[i - 1][j] + 1));
        }
      }
    }

    return matrix[al - 1][bl - 1];

  }
}