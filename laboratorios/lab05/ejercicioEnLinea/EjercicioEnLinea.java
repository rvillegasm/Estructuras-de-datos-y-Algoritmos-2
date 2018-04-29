import java.util.*;
import javafx.util.Pair;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * EjercicioEnLinea
 */ 

public class EjercicioEnLinea {

    public static int distance(ArrayList<Pair<Integer,Integer>> vertices, Pair<Integer,Integer> inicial){
        Map<Pair<Integer,Integer>,Integer> path = new HashMap<>();
        ArrayList<Pair<Integer,Integer>> temp = new ArrayList<>(vertices);
        Pair<Integer,Integer> iterateVertex = inicial;
        Pair<Integer,Integer> currentVertex = inicial;
        int mincost = Integer.MAX_VALUE;
        int finalcost = 0;

        while(!temp.isEmpty()){
            int verticesDispo = temp.size();
            for(int i=0;i<verticesDispo;i++){
                int cost = Math.abs(currentVertex.getKey()-temp.get(i).getKey()) + Math.abs(currentVertex.getValue()-temp.get(i).getValue());
                if(cost < mincost){
                    mincost = cost;
                    iterateVertex = temp.get(i);
                }
            }
            path.put(iterateVertex, mincost);
            currentVertex = iterateVertex;
            temp.remove(iterateVertex);
            mincost = Integer.MAX_VALUE;
        }
        int returnCost = Math.abs(currentVertex.getKey()-inicial.getKey()) + Math.abs(currentVertex.getValue()-inicial.getValue());
        path.put(inicial,returnCost);

        //Sumatoria total del camino
        for(int currentcost : path.values()){
            finalcost = finalcost + currentcost;
        }

        //Retornamos respuesta final
        return finalcost;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Pair<Integer,Integer>> vertices = new ArrayList<>();

        String aux = scan.readLine();
        int numCases = Integer.parseInt(aux);
        for(int i=0;i<numCases;i++){
            scan.readLine();
            aux = scan.readLine();
            String[] auxS = aux.split(" ");
            Pair<Integer,Integer> inicial = new Pair<>(Integer.parseInt(auxS[0]),Integer.parseInt(auxS[1]));
            aux = scan.readLine(); //Numero de desechos
            int numWaste = Integer.parseInt(aux);
            for(int j=0;j<numWaste;j++){
                aux = scan.readLine();
                auxS = aux.split(" ");
                Pair<Integer,Integer> pareja = new Pair<>(Integer.parseInt(auxS[0]),Integer.parseInt(auxS[1]));
                vertices.add(pareja);
            }
            System.out.println("El camino mas corto para el escenario: "+(i+1)+" es: "+distance(vertices,inicial));
            vertices.clear();
        }

    }

    
}