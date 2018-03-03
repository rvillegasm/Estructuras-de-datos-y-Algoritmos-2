#pragma once

#include "graph.h"
#include <vector>
#include <array>
#include <memory>

/*
 * Digraph implementation using Adyacensy Matrix
 * @author Rafael Villegas M
 */

//template<class S>
class DigraphAM : public Graph {
public:
  //std::unique_ptr<std::array<std::array<int,S>,S>> m_Matrix;
  std::vector<std::vector<int>> m_Matrix;

  DigraphAM(int size);
    //m_Matrix = std::make_unique<std::array<std::array<int,size>,size>>;
  

  void addArc(int source, int destination, int weight);
  int getWeight(int source, int destination) const;
  std::vector<int> getSuccessors(int vertex);
  
};