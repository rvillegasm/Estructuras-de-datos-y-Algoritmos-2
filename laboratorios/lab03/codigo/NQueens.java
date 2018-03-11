/**
 * NQueens
 */
public class NQueens {

  private static boolean puedoPonerReina(int r, int c, int[] tablero) {
		for (int i = 0; i < r; i++) {
			if (tablero[i] == c) {
				return false;
			}
			if (Math.abs(tablero[i] - c) == Math.abs(i - r)) {
				return false;
			}
		}
		return true;
	}

	public static boolean nReinas(int n) {
		int[] tablero = new int[n];
    
		if(nReinas(0, n, tablero)){
      imprimirTablero(tablero);
      return true;
    }else{
      return false;
    }
	}

	private static boolean nReinas(int r, int n, int[] tablero) {
		if (r == n) {
			//imprimirTablero(tablero);
			return true;
		}
		
		for (int i = 0; i < n; i++) {
			if (puedoPonerReina(r, i, tablero)) {
				tablero[r] = i;
				if(nReinas(r + 1, n, tablero)){
          return true;
        }
			}
		}
		return false;
	}

	public static void imprimirTablero(int[] tablero) {
		int n = tablero.length;
		System.out.print("    ");
		for (int i = 0; i < n; ++i)
			System.out.print(i + " ");
		System.out.println("\n");
		for (int i = 0; i < n; ++i) {
			System.out.print(i + "   ");
			for (int j = 0; j < n; ++j)
				System.out.print((tablero[i] == j ? "Q" : "#") + " ");
			System.out.println();
		}
		System.out.println();
  }
  
  public static void main(String[] args) {
		for(int i = 4; i <= 32; i++) {
		long startTime = System.currentTimeMillis();
    nReinas(i);
    long endTime = System.currentTimeMillis();
    System.out.println(i +" queens took " + (endTime - startTime) + " milliseconds");
		}
		System.out.println();
		
  }
  
}