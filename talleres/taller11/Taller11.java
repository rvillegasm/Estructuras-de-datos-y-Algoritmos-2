import java.lang.Math;

/**
 * Taller11
 */
public class Taller11 {

  public int heldKarp(Graph g) {
    int[][] path = new int[g.size()][Math.pow(2, g.size())];
    int[][] father = new int[g.size()][g.size()];
    

    //Todos los sub-conjuntos que contiene el grafo
    List<Set<Integer>> allSets = generateCombination(g.size() - 1);
  }

    

  private List<Set<Integer>> generateCombination(int n) {
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

  private void generateCombination(int input[], int start, int pos, List<Set<Integer>> allSets, int result[]) {
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

}