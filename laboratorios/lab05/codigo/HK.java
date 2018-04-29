import java.util.*;
import javafx.util.Pair;


public class HK {

  public static int heldKarp(Graph g) {
    Map<Pair<Integer, Set<Integer>>, Integer> costs = new HashMap<>();

    //Todos los sub-conjuntos que contiene el grafo
    List<Set<Integer>> allSets = generateCombination(g.size() - 1);
    for (Set<Integer> set : allSets) {
      for (int i = 1; i < g.size(); i++) {
        if (set.contains(i)) {
          continue;
        }

        Pair<Integer, Set<Integer>> pareja = new Pair<>(i, set);
        int minCost = Integer.MAX_VALUE;
        int minPrev = 0;
        Set<Integer> temp = new HashSet<>(set); //Set temporal para evitar daños a los sets

        for (int prev : set) {
          temp.remove(prev);
          Pair<Integer, Set<Integer>> prevDist = new Pair<>(prev, temp);

          int cost = g.getWeight(prev, i) + costs.get(prevDist); //Analiza la distancia hasta el vertice y la distancia pasando por los que faltan en el conjunto
          if (cost < minCost) {
            minCost = cost;
            minPrev = prev;
          }

          temp.add(prev);
        }

        //Si el set es vacio
        if (set.size() == 0) {
          minCost = g.getWeight(0, i);
        }

        //Añade la pareja con el costo (valor para llegar pasando por el conjunto)
        costs.put(pareja, minCost);
      }
    }

    //Analizamos la ruta mas corta
    Set<Integer> vertices = new HashSet<>();
    for (int i = 1; i < g.size(); i++) {
      vertices.add(i);
    }

    int result = Integer.MAX_VALUE;
    int prev = -1;
    Set<Integer> temp = new HashSet<>(vertices);

    for (int v : vertices) {
      temp.remove(v);
      Pair<Integer, Set<Integer>> prevDist = new Pair<>(v, temp);

      int cost = g.getWeight(v, 0) + costs.get(prevDist);
      if (cost < result) {
        result = cost;
        prev = v;
      }

      temp.add(v);

    }

    return result; //Retornamos el menor costo (Suma de pesos de la ruta)
  }

  //Metodos y clases Auxiliares

  private static List<Set<Integer>> generateCombination(int n) {
    int input[] = new int[n];
    for (int i = 0; i < input.length; i++) {
      input[i] = i + 1;
    }
    List<Set<Integer>> allSets = new ArrayList<>();
    int result[] = new int[input.length];
    generateCombination(input, 0, 0, allSets, result);
    Collections.sort(allSets, new SetSizeComparator());
    return allSets;
  }

  private static void generateCombination(int input[], int start, int pos, List<Set<Integer>> allSets, int result[]) {
    if (pos == input.length) {
      return;
    }
    Set<Integer> set = createSet(result, pos);
    allSets.add(set);
    for (int i = start; i < input.length; i++) {
      result[pos] = input[i];
      generateCombination(input, i + 1, pos + 1, allSets, result);
    }
  }

  private static Set<Integer> createSet(int input[], int pos) {
    if (pos == 0) {
      return new HashSet<>();
    }
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < pos; i++) {
      set.add(input[i]);
    }
    return set;
  }

  private static class SetSizeComparator implements Comparator<Set<Integer>> {
    @Override
    public int compare(Set<Integer> o1, Set<Integer> o2) {
      return o1.size() - o2.size();
    }
  }

}