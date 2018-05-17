import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;

import javafx.util.Pair;

/**
 * CMAlgo
 */
public class CoreAlgo {

  public ArrayList<ArrayList<Pair<Integer, Double>>> CMAlgo(
                                                            ArrayList<Pair<Graph, Integer>> graphsAndStations, 
                                                            double batteryCapacity, 
                                                            double consumptionRate, 
                                                            double carSpeed, 
                                                            double[] chargeTimePerHour, 
                                                            double[] batteryLevelPerHour,
                                                            double timePerClient
                                                            ) {
    //create an array of graphs
    ArrayList<Graph> graphSet = new ArrayList<Graph>(graphsAndStations.size());
    for(Pair<Graph, Integer> pair : graphsAndStations) {
      graphSet.add(pair.getKey());
    }
    // el resultado contiene arreglos de arreglos de parejas, donde el key es el nodo y el value es el tiempo que se demora hacia el
    ArrayList<ArrayList<Pair<Integer, Double>>> resultingPaths = new ArrayList<ArrayList<Pair<Integer, Double>>>(graphSet.size());
    // fill the result
    // for(int i = 0; i < resultingPaths.size(); i++) {
      
    // }

    // para cada subgrafo k
    for(int k = 0; k < graphSet.size(); k++) {
      // fill result
      resultingPaths.add(k, new ArrayList<Pair<Integer, Double>>());
      // añadir la pareja inicial
      resultingPaths.get(k).add( new Pair<Integer,Double>(0, 0.0));

      double currentCharge = batteryCapacity;

      int currentVertex = 0;

      // arreglo de visitados
      boolean[] visited = new boolean[graphSet.get(k).size()]; 
      
      while(true) {
        //se marca como visitado el actual
        visited[currentVertex] = true;

        ArrayList<Integer> Neighbours = graphSet.get(k).getSuccessors(currentVertex); // vecinos

        int closestN = Integer.MAX_VALUE; // closest neighbour
        double minDistance = Double.MAX_VALUE; // minimum distance

        // get closest neighbour that has not been visited, excluding the station
        for(int v : Neighbours) {  
          if(graphSet.get(k).getWeight(currentVertex, v) < minDistance && !visited[v] && v != graphSet.get(k).size()-1) {
            closestN = v;
          }
        }

        // if a closest neighbour was not found, everyone has been visited and you can exit the loop 
        if(closestN == Integer.MAX_VALUE) {
          break;
        }

        // check if it is posible to go to the closest neighbour and to the station, if not then charge
        double safeDistance = graphSet.get(k).getWeight(currentVertex, closestN) + distanceToStation(graphSet.get(k), closestN);
        if(consumeEnergy(safeDistance, consumptionRate, currentCharge) > 0) {

          double distanceToClosest = graphSet.get(k).getWeight(currentVertex, closestN);
          // consume energy
          currentCharge = consumeEnergy(distanceToClosest, consumptionRate, currentCharge);
          // time taken from current vertex to the next one
          double timeTaken = timePerClient + (distanceToClosest / carSpeed);
          // move to the next vertex and add to result
          currentVertex = closestN;
          resultingPaths.get(k).add( new Pair<Integer,Double>(currentVertex, timeTaken) );

        }
        else { // go and charge, and then go to the next vertex

          int station = graphSet.get(k).size()-1;
          // move to station
          double distanceToStation = distanceToStation(graphSet.get(k), currentVertex);
          // consume energy
          currentCharge = consumeEnergy(distanceToStation, consumptionRate, currentCharge);
          // time taken from current vertex to station
          double timeTaken = distanceToStation / carSpeed;
          // move to station and add to result, then charge and move to the next vertex and add to result
          int stationType = graphsAndStations.get(k).getValue();
          resultingPaths.get(k).add( new Pair<Integer,Double>(station, timeTaken + chargeTime(currentCharge, batteryCapacity, chargeTimePerHour, batteryLevelPerHour, stationType)) );
          // re-fill battery
          currentCharge = batteryCapacity;
          double distanceToClosest = graphSet.get(k).getWeight(station, closestN);
          currentCharge = consumeEnergy(distanceToClosest, consumptionRate, currentCharge);
          timeTaken = timePerClient + (distanceToClosest / carSpeed);
          currentVertex = closestN;
          resultingPaths.get(k).add( new Pair<Integer,Double>(currentVertex, timeTaken) );

        }
      }
      // go to the depot using dijkstra
      ArrayList<Integer> backPath = dijkstra(graphSet.get(k), currentVertex, 0);
      
      for(int i = 0; i < backPath.size()-1; i++) {
        // get the distance from one of the nodes in the path found by dijkstra to the next one
        double distanceToNext = graphSet.get(k).getWeight(backPath.get(i), backPath.get(i+1));
        // chacks if it is posible to go to the next vertex and to the station, if not, then charge
        double safeDistance = distanceToNext + distanceToStation(graphSet.get(k), backPath.get(i+1));
        if(consumeEnergy(safeDistance, consumptionRate, currentCharge) > 0) {
          // consume energy
          currentCharge = consumeEnergy(distanceToNext, consumptionRate, currentCharge);
          // time taken from one vertex to the next
          double timeTaken = distanceToNext / carSpeed;
          // add to result
          resultingPaths.get(k).add( new Pair<Integer,Double>(backPath.get(i+1), timeTaken) );
        }
        else {

          int station = graphSet.get(k).size()-1;
          // move to station
          double distanceToStation = distanceToStation(graphSet.get(k), backPath.get(i));
          // consume energy
          currentCharge = consumeEnergy(distanceToStation, consumptionRate, currentCharge);
          // time taken from current vertex to station
          double timeTaken = distanceToStation / carSpeed;
          // move to station and add to result, then charge and move to the next vertex and add to result
          int stationType = graphsAndStations.get(k).getValue();
          resultingPaths.get(k).add( new Pair<Integer,Double>(station, timeTaken + chargeTime(currentCharge, batteryCapacity, chargeTimePerHour, batteryLevelPerHour, stationType)) );
          // re-fill battery
          currentCharge = batteryCapacity;
          double distanceToClosest = graphSet.get(k).getWeight(station, backPath.get(i+1));
          currentCharge = consumeEnergy(distanceToClosest, consumptionRate, currentCharge);
          timeTaken = distanceToClosest / carSpeed;
          resultingPaths.get(k).add( new Pair<Integer,Double>(backPath.get(i+1), timeTaken) );
        }
      }
    }
    return resultingPaths;
  } 

  // gets the distance between a source vertex and the station
  private static double distanceToStation(Graph g, int source) {
    double distance = g.getWeight(source, g.size()-1);
    return distance;
  }

  private static double consumeEnergy(double distance, double consumptionRate, double currentCharge) {
    double newCharge = currentCharge;
    newCharge -= distance * consumptionRate;
    return newCharge;
  }

  private static double chargeTime(double currentCharge, double batteryCapacity, double[] chargeTimePerHour, double[] batteryLevelPerHour, int stationType) {
    double needed = batteryCapacity - currentCharge;
    double timesToCharge = needed / batteryLevelPerHour[stationType];
    double time = timesToCharge * chargeTimePerHour[stationType];
    return time;
  }


  private static int minVertex(HashSet<Integer> q, int[] dist) {
		int min = Integer.MAX_VALUE;
		for(int vertex : q) {
			if(dist[vertex] < min) {
				min = vertex;
			}
		}
		return min;
	}

	private static ArrayList<Integer> getPath(int[] dist, int[] prev, int start, int target) {
		Stack<Integer> st = new Stack<Integer>();
		int u = target;
		while (u != start) {
			st.push(u);
			u = prev[u];
		}
		st.push(u);
    ArrayList<Integer> path = new ArrayList<>(st.size());
		while(!st.isEmpty()) {
			path.add(st.pop());
    }
    return path;
	}

	/** camino mas corto entre dos veritces implementado con dijktra (camino de costo minimo) */
	private static ArrayList<Integer> dijkstra(Graph g, int start, int target) {
		
		int[] dist = new int[g.size()];
		int[] prev = new int[g.size()];
		HashSet<Integer> q = new HashSet<Integer>(g.size());

		for(int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			q.add(i);
		}

		dist[start] = 0;

		while(!q.isEmpty()) {

			int u = minVertex(q, dist);

			q.remove(u);
			if(u == target) {
				ArrayList<Integer> path = getPath(dist, prev, start, target);
				return path;
			}

			ArrayList<Integer> successors = g.getSuccessors(u);
			for(int v : successors) {
				double alt = dist[u] + g.getWeight(u, v);
				if(alt < dist[v]) {
					dist[v] = (int) alt; // Machetaso
					prev[v] = u;
				}
			}
		}
		return null;

	}
  
}