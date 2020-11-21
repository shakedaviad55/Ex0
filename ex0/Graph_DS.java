package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents an unweighted and undirectional graph
 * @author shaked
 *
 */
public class Graph_DS   implements graph {
	private  int edges=0;
	private   int modeCount=1;
	private HashMap<Integer, node_data>  graph;

	/**
	 * Default constructor
	 * @return new Graph
	 */	
	public  Graph_DS() {
		graph=new  HashMap<Integer, node_data>();
	}
	/**
	 * constructor who copies the nodes
	 * @param Collection<node_data> nodes
	 * @return new Graph with nodes
	 */
	public Graph_DS(Collection<node_data> nodes) {
		graph=new  HashMap<Integer, node_data>();
		nodes.forEach(node->{
			graph.put(node.getKey(), node);
		});
	}
	/**
	 * Returns the node by the specific key
	 *If no exist returns null
	 *@param 	specific key
	 *@return node_data
	 */
	@Override
	public node_data getNode(int key) {
		node_data n1=graph.get(key);
		if(n1==null) {	
			//throw new RuntimeException("Node cannot be null"+key);
			return null;
		}		
		return graph.get(key);
	}
	/**
	 * Checks if there is an edge between the two nodes
	 * @param specific key1,specific key2
	 * @return true if exists else false
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {
		if(node1==node2||getNode(node1)==null||getNode(node2)==null)
			return false;
		return this.getNode(node1).hasNi(node2);
	}
	/**
	 * Adds a node to the graph
	 * @param node_data
	 * @return
	 */
	@Override
	public void addNode(node_data n) {
		if(n==null)return;
		graph.put(n.getKey(), n);	
		modeCount++;
		}
	/**
	 * Connect between two nodes
	 * @param specific key1,specific key2
	 */
	@Override
	public void connect(int node1, int node2) {
		if(node1!=node2&&this.getNode(node1)!=null&&this.getNode(node2)!=null) {
			if(!this.getNode(node1).hasNi(node2)) {
				this.getNode(node1).addNi(this.getNode(node2));
				this.getNode(node2).addNi(this.getNode(node1));
				edges++;modeCount++;
			}
		}
	}
	/**
	 * Returns all nodes of this graph
	 *
	 */
	@Override
	public Collection<node_data> getV() {return graph.values();}
	/**
	 * Returns all neighbors of this node
	 * @param  specific key
	 * @return all neighbors
	 */
	@Override
	public Collection<node_data> getV(int node_id) {return this.getNode(node_id).getNi();}
	/**
	 *Deletes the node of this graph and all the neighbors of this node
	 *@param   specific key
	 *@return
	 */
	@Override
	public node_data removeNode(int key) {

		if(this.getNode(key)!=null) {
			graph temp=new Graph_DS(this.getV(key));
			temp.getV().forEach(node->{
				if(node.getKey()!=key) 
					removeEdge(key, node.getKey());
			});
		}	
		modeCount++;
		return graph.remove(key);
	}
	/**
	 * Deletes the connection between two nodes (edge)
	 * @param specific key1,specific key2
	 * @return
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		if(this.getNode(node1)==null||this.getNode(node2)==null)return;
		if(this.getNode(node1).hasNi(node2)) {
			this.getNode(node1).removeNode(this.getNode(node2));
			this.getNode(node2).removeNode(this.getNode(node1));
			edges--;	modeCount++;
		}

	}
	/**
	 * Returns the node's numbers of this graph
	 */
	@Override
	public int nodeSize() {return graph.size();}
	/**
	 * Returns the edge's numbers of this graph
	 */
	@Override
	public int edgeSize() {return edges;}
	/**
	 * Updates the number of edges of this graph
	 * @param e
	 */
	public void setEdge(int e) {
		this.edges=e;
	}
	/**
	 * Returns the amount of changes made in this graph
	 * @return
	 */
	@Override
	public int getMC() {return modeCount;}
	/**
	 * Represents this graph as String
	 * @return String
	 */
	public String toString() {
		return getV().toString();
	}

}
