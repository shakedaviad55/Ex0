package ex0;



import java.util.LinkedList;
import java.util.List;


/**
 * Graph Algorithms Department contains those methods:
 * Deep copy of a graph 
 * Connectivity of graph
 * Shortest path distance
 * Shortest path
 * (In this class the BFS algorithm is used as help function)
 * @author Shaked Aviad
 *
 */

public class Graph_Algo  implements graph_algorithms {

	private static final String NOT_VISITED = "white", VISITED = "green", FINISH = "black";
	private Graph_DS algo;

	/**
	 * Default constructor
	 * @return new Graph
	 */	
	public Graph_Algo() {
		this(	new Graph_DS());
	}
	/**
	 * constructor of a specific graph g
	 * @param graph
	 * @return new Graph of  specific graph g
	 */	
	public Graph_Algo(graph g) {init(g);}
	/**
	 * Initializes an algorithm graph
	 */
	@Override
	public void init(graph g) {
		algo=(Graph_DS) g;
	}
	/**
	 * deep copy:
	 * @return deep copy of this graph
	 */
	@Override
	public graph copy() {return copy(algo);}
	/**
	 * Returns true if and only if (iff) there is a valid path from EVERY node to each
	 * other node. NOTE: assume undirectional graph.
	 * @return
	 */
	@Override
	public boolean isConnected() {
		if (algo == null)
			return false;
		if(algo.nodeSize() == 0 ||algo.nodeSize()==1)
			return true;

		algo.getV().forEach(node->{
			node.setInfo(NOT_VISITED);
			node.setTag(Integer.MAX_VALUE);		
		});

		NodeData n=(NodeData) algo.getV().iterator().next();
		isConnected(n.getKey());
		for(node_data node:algo.getV()) {
			if(node.getTag()==Integer.MAX_VALUE||node.getInfo()!=FINISH)
				return false;	
		}
		return true;
	}
	/**
	 * returns the length of the shortest path between src and dest
	 * By using the BFS algorithm (An explanation of BFS is located next to the BFS function)
	 * Note: if no such path --> returns -1
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */

	@Override
	public int shortestPathDist(int src, int dest) {
		if(src==dest)return 0;
		return BFS(src,dest);
	}
	/**
	 * returns the the shortest path between src and dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * Note if no such path --> returns null;
	 * Returns Null between vertex and itself
	 * First updates the minimum number of steps per vertex
	 * If there is a valid path between src & dest it is sent to a help function which returns the short path
	 *( An explanation of this function is located next to it)
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		LinkedList<node_data>  path=new LinkedList<node_data>();
		if(src==dest) {
			path.add(algo.getNode(src));
			return path;
		}
		if(BFS(src,dest)>0) {
			path.add(algo.getNode(dest));
			return  shortestPath(path,dest,src);
		}
		return null;

	}
	/**
	 * Represents this graph as String
	 * @return String
	 */
	public String toString() {
		return	algo.toString();
	}

	///////////////// PRIVATE METHOD ///////////////////
	/**
	 * Goes over each vertex of the graph and  deep copies it into the graph
	 * @param g
	 * @return new graph
	 */
	private Graph_DS copy(graph g) {
		Graph_DS copyGraph=new Graph_DS();	
		g.getV().forEach(node->{
			copyGraph.addNode(new NodeData(node));
		});	
		copyGraph.setEdge(g.edgeSize());
		return copyGraph;
	}
	/**
	 * Goes through all the vertices of this graph
	 *from the first vertex to the last and in each iteration changes the info of that vertex 
	 *if it failed to reach a certain vertex 
	 *the main function returns false, else true
	 * @param first node key of this graph
	 */
	private void isConnected (int key) {
		LinkedList<node_data> list = new LinkedList<node_data>();
		algo.getNode(key).setInfo(VISITED);
		algo.getNode(key).setTag(0);
		list.add(algo.getNode(key));

		while(!list.isEmpty()) {
			node_data currNode=list.getFirst();
			list.remove(list.getFirst());
			for(node_data neighbors: currNode.getNi()) {
				if(neighbors.getInfo()==NOT_VISITED) {
					neighbors.setInfo(VISITED);
					neighbors.setTag(currNode.getTag()+1);
					list.add(neighbors);
				}
			}
			currNode.setInfo(FINISH);
		}
	}
	/**
	 * BFS Algorithm
	 *see:https://www.youtube.com/watch?v=oDqjPvD54Ss
	 *Each vertex enters the list 
	 *and in each iteration it updates the minimum number of steps to the vertex 
	 *and deletes this vertex from the list 
	 *and moves on to the next element of the queue until it reaches the target vertex
	 * @param src
	 * @param dest
	 * @return The minimum number of steps from start node to destination node
	 */
	private int BFS (int s ,int d) {
		node_data src=algo.getNode(s);
		node_data dest=algo.getNode(d);

		LinkedList<node_data> list = new LinkedList<node_data>();

		if (src == null || dest == null) {
			return -1;	
		}
		algo.getV().forEach(node->{
			node.setInfo(NOT_VISITED);
			node.setTag(Integer.MAX_VALUE);
		});

		src.setInfo(VISITED);
		src.setTag(0);
		list.add(src);

		while(!list.isEmpty()) {
			node_data currNode=list.getFirst();
			list.remove(list.getFirst());

			if(currNode.getTag()==Integer.MAX_VALUE  || currNode.getKey()==d) {
				if(currNode.getTag()==Integer.MAX_VALUE)return -1;
				return currNode.getTag();
			}
			currNode.getNi().forEach(neighbors->{
				if(neighbors.getInfo()==NOT_VISITED) {
					neighbors.setInfo(VISITED);
					neighbors.setTag(currNode.getTag()+1);
					list.add( neighbors);
				}

			});
			currNode.setInfo(VISITED);

		}
		return-1;
	}
	/**
	 * After being tested and indeed there is a path
	 * Starting from the end vertex (key) to the beginning vertex (src)
	 *In each iteration it enters a vertex that has the minimum number of steps
	 *And  choose the next vertex in the same approach
	 *Until you reach to the vertex we began with
	 * @param path
	 * @param key
	 * @param src
	 * @return LinkedList Of the shortest path
	 */
	private LinkedList<node_data> shortestPath(LinkedList<node_data> path,int key,int src) {

		LinkedList<node_data> list = new LinkedList<node_data>();
		int val =Integer.MAX_VALUE;
		int id=key;
		list.add(algo.getNode(key));

		while(!list.isEmpty()) {
			node_data curNode=list.getFirst();
			list.remove(list.getFirst());
			for(node_data neighbors:curNode.getNi() ) {
				if(neighbors.getTag()<val) {
					val=neighbors.getTag();
					id=neighbors.getKey();
				}
				if(id==src) {
					path.addFirst(algo.getNode(id));
					return path;
				}
			}
			path.addFirst(algo.getNode(id));
			list.add(algo.getNode(id));
		}
		return path;
	}
}
