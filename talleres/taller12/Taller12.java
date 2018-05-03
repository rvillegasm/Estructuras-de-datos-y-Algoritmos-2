/**
 * N-Queens with Local Search
 */
public class Taller12 {

  public static int[] nreinas(int n){
    int[] tablero = new int[n];
    int[] temp = tablero;
    int[] resTemp = tablero;
    int min = Integer.MAX_VALUE;
    for(int posicion : tablero){
      tablero[posicion] = 0;
    }
    int heuristic = attacks(tablero);
    boolean mejor = true;
    while(mejor){
      mejor = false;
      
      for(int i = 0; i < temp.length; i++){ 
        if(temp[i] < temp.length) {
          for(int j = temp[i]; j < temp.length; j++){ 
            temp[i]=j;
            heuristic = attacks(temp);
            if(heuristic < min){
              min = heuristic;
              resTemp = temp;
              mejor = true;
            }
          }
          temp = tablero;
        }
        else {
          for(int j = temp[i]; j >= 0; j--){
            temp[i]=j;
            heuristic = attacks(temp);
            if(heuristic < min){
              min = heuristic;
              resTemp = temp;
              mejor = true;
            }
          }
          temp = tablero;
        }
      }
      temp = resTemp; 
      tablero = resTemp;
    }
    return tablero;
  }

  private static int attacks(int[] tablero){
    int ataques = 0;
    for(int i=0;i<tablero.length;i++){
      for(int j=0;j<tablero.length;j++){
        if((Math.abs(tablero[i] - tablero[j]) == Math.abs(i - j)) && (i != j)) {
          ataques++;
        }
        if((tablero[i] == tablero[j]) && (i != j)) {
          ataques++;
        }
      }
    }
    return ataques;
  }

  public static void main(String[] args) {
    int[] tablero = nreinas(4);
    for(int queen : tablero) {
      System.out.println(queen);
    }
  }
  
}