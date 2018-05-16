import java.util.*;
import javafx.util.*;

/**
 * Kmeans Implementation
 * @author Felipe Cortes J. & Rafael Villegas
 * @version 1
 */
public class ImplEkmeans {

    public ArrayList<Pair<Graph, Integer>> clustersFinales(double[][] points, ArrayList<Pair<double[], Integer>> stations, double[] depot, int k){ //Recibe un File de donde se van a leer los datos

        ArrayList<Pair<Graph, Integer>> resultado = new ArrayList<Pair<Graph, Integer>>();
        Random random = new Random(System.currentTimeMillis());    

        //double[][] points = new double[n][2]; // Valores: [][0] -> x [][1] -> y

        //Hallamos los centroides basados en el numero k que se necesitan y se elijen arbitrariamente, es decir con la funcion Random
        double[][] centroids = new double[k][2];

        for(int i=0;i<k;i++){
            int randomPoint = Math.abs(random.nextInt() % points.length-1);
            centroids[i][0] = points[randomPoint][0]; //Coordenadas en x
            centroids[i][1] = points[randomPoint][1]; //Coordenadas en y
        }

        //Corremos Kmeans para que nos divida el conjunto total de puntos en los clusters con los centroides indicados en un principio
        Kmeans eKmeans = new Kmeans(centroids, points);
        int[] assignments = eKmeans.run();

        //Creo un conjunto que tenga subconjuntos dependiendo del numero de clusters que hay, cada uno va a tener sus respectivos puntos asignados en kmeans
        ArrayList<ArrayList<Integer>> conjuntos = new ArrayList<ArrayList<Integer>>(k);
        ArrayList<Integer> conjunto = new ArrayList<Integer>();
        int cont = 0; //contador para el while (numero de clusters)
        while(cont<k){
         conjuntos.add(conjunto);
         conjunto = new ArrayList<Integer>();
         cont += 1;
        }

        //Llenamos cada subconjunto respectivamente
        for(int i = 0; i < points.length; i++){
            conjuntos.get(assignments[i]).add(i);
        }

        //Creamos los grafos respectivamente SIN INCLUIR LAS ESTACIONES TODAVIA
        for(int i=0;i<conjuntos.size();i++){
            //Creamos el grafo
            DigraphAL g = new DigraphAL(conjuntos.get(i).size()+2); //El +2 es debido a que se va a adicionar el depot + la estacion de carga

            for(int j=0;j<conjuntos.get(i).size();j++){
                //Relacionamos el depot con cada nodo y el depot siempre va de primero
                    g.addArc(0,j+1,distanciaS(depot[0],depot[1],points[conjuntos.get(i).get(j)][0],points[conjuntos.get(i).get(j)][1]));
                    g.addArc(j+1,0,distanciaS(depot[0],depot[1],points[conjuntos.get(i).get(j)][0],points[conjuntos.get(i).get(j)][1]));
                if(j+1<conjuntos.get(i).size()){
                    for(int d=j+1;d<conjuntos.get(i).size();d++){
                        g.addArc(j+1,d+1,distanciaP(conjuntos.get(i).get(j),conjuntos.get(i).get(d),points)); //Adicionamos el arco - FALTA IMPLEMENTAR DISTANCIA P
                        g.addArc(d+1,j+1,distanciaP(conjuntos.get(i).get(j),conjuntos.get(i).get(d),points)); //Se adiciona dos veces porque el grafo es no dirigido
                    }
                }   
            }

            //Adicionamos el grafo creado al resultado final
            Pair<Graph, Integer> resultadoParcial = new Pair<>(g,0);
            resultado.add(resultadoParcial);
        }

        //Adicionamos las estaciones de tal manera que se agregue a cada grafo una estaciones cercana al centroide respectivo, para asi asegurar una estacion de c.
        for(int i=0;i<k;i++){
            double dist = 0;
            double distMin =  Double.POSITIVE_INFINITY;
            double xS = 0;
            double yS = 0;
            int stationType = 0;
            Graph temp = resultado.get(i).getKey(); 
            for(int j=0;j<stations.size();j++){
                dist = distanciaS(centroids[i][0],centroids[i][1],stations.get(j).getKey()[0],stations.get(j).getKey()[1]);
                if(dist<distMin){
                    xS = stations.get(j).getKey()[0]; //Guardo las coordenadas de la estacion
                    yS = stations.get(j).getKey()[1];
                    stationType = stations.get(j).getValue();
                }
            }
            //Adicionamos la estacion ideal al grafo especifico en la ultimo posicion
            for(int d=0;d<temp.size()-1;d++){ //Se agregan dos veces porque el grafo es no dirigido
                if(d==0){
                    temp.addArc(temp.size()-1,0,distanciaS(xS,yS,depot[0],depot[1]));
                    temp.addArc(0,temp.size()-1,distanciaS(xS,yS,depot[0],depot[1]));
                }else{
                    temp.addArc(temp.size()-1,d,distanciaS(xS,yS,points[conjuntos.get(i).get(d-1)][0],points[conjuntos.get(i).get(d-1)][1]));
                    temp.addArc(d,temp.size()-1,distanciaS(xS,yS,points[conjuntos.get(i).get(d-1)][0],points[conjuntos.get(i).get(d-1)][1]));
                }   
            }

            //Creamos la nueva pareja modificada
            Pair<Graph, Integer> resultadoParcial2 = new Pair<>(temp,stationType);
            resultado.set(i,resultadoParcial2);
        }
        
        //Retornamos arreglo terminado y modificado
        return resultado;
    }

    public static double distanciaP(int p1, int p2, double[][]points){ //Retorna el valor del arco entre dos puntos, aplicando la formula de pitagoras 
        double arco = Math.hypot(Math.abs(points[p1][0]-points[p2][0]),Math.abs(points[p1][1]-points[p2][1]));
        return arco;
    }

    public static double distanciaS(double x1, double y1, double x2, double y2){ //Retorna el valor del arco entre un punto y la estacion aplicando pitagoras 
        double arco = Math.hypot(Math.abs(x2 - x1),Math.abs(y2 - y1));
        return arco;
    }

}