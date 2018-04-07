import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Bus
 */
public class Bus {
  public static int moneyDue(int total, int max, int extra){
    if(total>max){
    int extraH = total - max;
    return extraH*extra;
    }
    else {
      return 0;
    }
  }

  public static void main(String[] args) throws IOException{

    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Integer> res = new ArrayList<Integer>();

    while(true) {
      String aux = scan.readLine();
      String[] auxS = aux.split(" ");

      int n = Integer.parseInt(auxS[0]);
      int d = Integer.parseInt(auxS[1]);
      int r = Integer.parseInt(auxS[2]);

      if(n == 0 && d == 0 && r == 0){
        break;
      }

      int[] horasB = new int[n];  //Horas Buseros totales

      for(int i=0; i < n;i++){
        String hora = scan.readLine();
        String[] horaS = hora.split(" ");
        for(int j=0;j<horaS.length;j++){
          horasB[j] = horasB[j] + Integer.parseInt(horaS[j]);
        }
      }

      int monto = 0; //Monto total a pagar de las horas extra

      for(int buseroH:horasB){
        monto += moneyDue(buseroH, d, r);
      }

      res.add(monto);
    }

    for(int respuesta:res){
      System.out.println(respuesta);
    }
  }
}