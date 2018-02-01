#include "graph.h"
#include "digraphAL.h"
#include <vector>
#include <list>
#include <utility>

DigraphAL::DigraphAL(int size) :
  Graph(size)
{
  for(int i = 0; i < size; i++) {
    m_List[i].emplace_front(std::make_pair(0,0));
  }
}

void DigraphAL::addArc(int source, int destination, int weight) {
  m_List[source].emplace_back(std::make_pair(destination, weight));
}

int DigraphAL::getWeight(int source, int destination) const {
  for(std::pair<int,int> p : m_List[source]) {
    if(p.first == destination) {
      return p.second; // returns the value
    }
  }
  return 0;
}

std::vector<int> DigraphAL::getSuccessors(int vertex) { // TODO: check if it can be const or not
  std::vector<int> neighborsList;
  for(std::pair<int,int> p : m_List[vertex]) {
    neighborsList.push_back(p.first);  // TODO: push_bakc vs emplace_back
  }
  return neighborsList;
}
