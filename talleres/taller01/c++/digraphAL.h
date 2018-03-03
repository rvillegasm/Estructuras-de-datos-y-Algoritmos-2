#pragma once

#include "graph.h"
#include <vector>
#include <list>
#include <utility>

/*
 * Digraph implementation using Adyacensy lists
 * @author Rafael Villegas M
 */

class DigraphAL : public Graph {
private:
  /* data */

public:

  std::vector<std::list<std::pair<int,int>>> m_List;

  DigraphAL(int size);
  

  void addArc(int source, int destination, int weight);
  int getWeight(int source, int destination) const;
  std::vector<int> getSuccessors(int vertex);
};
