main: main.o graph.o digraphAM.o
	g++ -g -o  main main.o graph.o digraphAM.o

main.o: main.cpp graph.h digraphAM.h
	g++ -g -c  main.cpp graph.cpp digraphAM.cpp

graph.o: graph.h
	g++ -g -c  graph.cpp g

digraphAM.o: graph.h digraphAM.h
	g++ -g -c  graph.cpp digraphAM.cpp