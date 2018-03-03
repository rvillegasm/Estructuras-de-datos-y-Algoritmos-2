#include <iostream>
#include <vector>
#include "graph.h"
#include "digraphAM.h"

int main() {
  DigraphAM g(3);
  g.addArc(0, 1, 1);
  g.addArc(0, 2, 1);

  std::vector<int> succ = g.getSuccessors(0);
  for(int i = 0; i < succ.size(); i++) {
    std::cout << succ[i] << std::endl;
  }
  return 0;
}