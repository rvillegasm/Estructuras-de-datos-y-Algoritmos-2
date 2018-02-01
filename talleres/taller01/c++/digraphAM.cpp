#include "graph.h"
#include "digraphAM.h"
#include <vector>

DigraphAM::DigraphAM(int size) :
  Graph(size)
{
  m_Matrix.reserve(size);
  for(int i = 0; i < m_Matrix.size(); i++) {
    m_Matrix[i].reserve(size);
  }
}

void DigraphAM::addArc(int source, int destination, int weight) {
  m_Matrix[source][destination] = weight;
}

int DigraphAM::getWeight(int source, int destination) const {
  return m_Matrix[source][destination];
}

std::vector<int> DigraphAM::getSuccessors(int vertex) {
  std::vector<int> list;
  for(int j = 0; j < m_Size; i++) {
    if(m_Matrix[vertex][j] != 0) {
      list.push_back(j);
    }
  }
  return list;
}