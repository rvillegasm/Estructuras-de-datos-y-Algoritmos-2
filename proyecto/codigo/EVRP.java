import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Electric Vehicle Routing Problem implementation
 * @author Felipe Cortés, Rafael Villegas
 * @version 1.0
 */
public class EVRP {

  public static void main(String[] args) throws IOException {

    int n = 0;
    int m = 0;
    int u = 0;
    double r = 0;
    double speed = 0;
    double st_customer = 0;
    double q = 0;

    BufferedReader scan = new BufferedReader(new FileReader("tc2c320s24cf0.txt")); // CHANGE SPECIFIED FILE HERE
    // read the variable values
    for(int i = 0; i < 10; i++) {
      String[] temp = scan.readLine().split(" ");
      switch (temp[0]) {
        case "n":
          n = Integer.parseInt(temp[2]);
          break;
        
        case "m":
          m = Integer.parseInt(temp[2]);
          break;

        case "u":
          u = Integer.parseInt(temp[2]);
          break;

        case "r":
          r = Double.parseDouble(temp[2]);
          break;

        case "speed":
          speed = Double.parseDouble(temp[2]);
          break;

        case "st_customer":
          st_customer = Double.parseDouble(temp[2]);
          break;

        case "q":
          q = Double.parseDouble(temp[2]);
          break;
      
        default:
          break;
      }
    }

    scan.readLine();
    scan.readLine(); // the coordinates start here
    scan.readLine();
    // create the coordinates array for the clients, with x and y coordinates
    double[][] points = new double[m][2];
    // create the stations array, with each station type
    ArrayList<Pair<double[], Integer>> stations = new ArrayList<Pair<double[], Integer>>(u);
    // for(int i = 0; i < stations.size(); i++) {
    //   stations.get(i).add( new Pair<>(new double[2], 0) );
    // }

    // create depot array for it's coordinates
    double[] depot = new double[2];
    //
    for(int i = 0; i < n; i++) {

      String[] vertexInfo = scan.readLine().split(" ");
      int numPoint = 0;

      switch (vertexInfo[4]) {
        case "d":
          depot[0] = Double.parseDouble(vertexInfo[2]); // x coordinate
          depot[1] = Double.parseDouble(vertexInfo[3]); // y coordinate
          break;
      
        case "c":
          points[numPoint][0] = Double.parseDouble(vertexInfo[2]);
          points[numPoint][1] = Double.parseDouble(vertexInfo[3]);
          numPoint += 1;
          break;

        case "s":
          double[] coordinates = {Double.parseDouble(vertexInfo[2]), Double.parseDouble(vertexInfo[3])};
          int stationType = Integer.parseInt(vertexInfo[5]);
          stations.add( new Pair<>(coordinates, stationType) );
          break;

        default:
          break;
      }
    }
    scan.readLine();
    scan.readLine(); // l starts here
    scan.readLine();

    double[] chargeTimePerHour = new double[3];

    for(int i = 0; i < 3; i++) {
      String[] temp = scan.readLine().split(" ");
      chargeTimePerHour[i] = Double.parseDouble(temp[3]);
    }
    scan.readLine();
    scan.readLine(); // g starts here
    scan.readLine();

    double[] batteryLevelPerHour = new double[3];

    for(int i = 0; i < 3; i++) {
      String[] temp = scan.readLine().split(" ");
      batteryLevelPerHour[i] = Double.parseDouble(temp[3]);
    }

    // --------------------------------------IT FINISHED READING THE FILE------------------------------------------------

    int k = 20; // define kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
    ImplEkmeans graphDivider = new ImplEkmeans();
    ArrayList<Pair<Graph, Integer>> subGraphsAndStations = new ArrayList<Pair<Graph, Integer>>(k);
    // divide the graph in various subgraphs, each with one station
    subGraphsAndStations = graphDivider.clustersFinales(points, stations, depot, k);

    // calculate the routes
    CoreAlgo routeProcessor = new CoreAlgo();
    ArrayList<ArrayList<Pair<Integer, Double>>> routesAndTimes = routeProcessor.CMAlgo(subGraphsAndStations, q, r, speed, chargeTimePerHour, batteryLevelPerHour, st_customer);

    // for each subgraph print it's route
    for(int i = 0; k < routesAndTimes.size(); k++) {

      System.out.print("Ruta " + i+1 + ": ");

      for(int j = 0; j < routesAndTimes.get(i).size(); j++) {

        int vertex = routesAndTimes.get(i).get(j).getKey();
        double timeTaken = routesAndTimes.get(i).get(j).getValue();

        System.out.println(vertex + "(" + timeTaken + "min), ");
      }
      // go to next line
      System.out.print("\n");
    }
    scan.close();

  }
  
}