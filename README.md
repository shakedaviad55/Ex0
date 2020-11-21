This project is assignment number 0 in OOP at Ariel University.

This project deals with the realization of an unweighted and undirectional graph that knows how to supports 
more than a million vertices and more than 10 million edges.

This project's main data structure is HashMap because of it's running time O(1).

The project has 3 different classes:
-------------------------------------------------------------------------------------

	NodeData class: represents the vertices of the graph.
		In order to initialize a node, we use a defaultive constructor which in it we get a unique key.
		or in a constructor which gets a node data.
		
	
	Graph_DS class: which represents the graph.
		In order to initialize a graph, we use a defaultive constructor, and in order
		to add verices we can use the method addNode that gets a node data.
		In order to connect an edge we use the method connect() that gets two different node datas.
	

	Graph_Algo class: represents the graph's algorithms.
		In order to initialize graph algorithms, we use a defaultive constructor, 
		or with a constructor which gets a graph, or with the init method.
		In this class we use the BFS algorithm in order to execute some of the algorithms.
		In the algorithm department there are a number of algorithms which include:
		
		copy - which makes a deep copy of a graph.
		isConnected - which checks if the graph is connected by edges with all nodes.
		shortestPathDist - which gets two unique keys for src and dest and returns the shortest distance between
		them, only if it exists.
		shortestPath - Gets two unique keys for src and dest and returns a ordered list route 
		from src to dest only if it exists.
		
 
 
	You can print any of the classes with a toString method.

	give me comments or improvements regarding the implementation.
	Hope you enjoy, share this project! :)
