#pragma once

#include <vector>

/*
 * Abstract class used for the implementation of Digraphs
 * @author Rafael Villegas M
 */

class Graph {
protected:

  int m_Size;

public:

  Graph(int vertices);
  

  virtual void addArc(int source, int destination, int weight) = 0;
  virtual int getWeight(int source, int destination) const = 0;
  virtual std::vector<int> getSuccessors(int vertex) = 0;

  int size();
};
