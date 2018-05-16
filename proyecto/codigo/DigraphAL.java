import java.util.ArrayList;
import javafx.util.Pair;
import java.util.LinkedList;

/**
 * Esta clase es una implementación de un digrafo usando listas de adyacencia
 * 
 * @author Mauricio Toro 
 * @version 1
 */
public class DigraphAL extends Graph {

  ArrayList<LinkedList<Pair<Integer, Double>>> lista;

  public DigraphAL(int size) {
    super(size);
    lista = new ArrayList<LinkedList<Pair<Integer, Double>>>();
    for (int i = 0; i < size; i++) {
      lista.add(new LinkedList<Pair<Integer, Double>>());
    }
  }

  public void addArc(int source, int destination, double weight) {
    lista.get(source).add(new Pair(destination, weight));
  }

  public double getWeight(int source, int destination) {
    for (Pair<Integer, Double> pareja : lista.get(source))
      if (pareja.getKey() == destination) {
        return pareja.getValue();
      }
    return 0;
  }

  public ArrayList<Integer> getSuccessors(int vertice) {
    ArrayList<Integer> listaVecinos = new ArrayList<Integer>();
    for (Pair<Integer, Double> pareja : lista.get(vertice)) {
      listaVecinos.add(pareja.getKey());
    }
    return listaVecinos;
  }
}