#include "graph.h"
#include <iostream>

Graph::Graph(int vertices):
  m_Size(vertices)
{}

int Graph::size() {
  return m_Size;
}
