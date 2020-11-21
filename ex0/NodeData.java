package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents the information of the graph vertex 
 * @author Shaked Aviad
 *
 */
public class NodeData  implements node_data {
	private int key;
	private String info;
	private int tag;	
	private static int counter=0;
	private HashMap<Integer, node_data>  neighbors;
	
	/**
	 * Default constructor
	 * @return new NodeData
	 */
	
	public NodeData() {
		this.key=counter++;
		this.info="";
		this.tag=0;	
		this.neighbors=new HashMap<Integer, node_data>();
		
	}
	/**
	 * copy constructor
	 * @param node_data
	 * @return new NodeData with the same variables
	 */
	public NodeData(node_data n) {
		this.info=n.getInfo();
		this.key=n.getKey();
		this.tag=n.getTag();
		this.neighbors=new HashMap<Integer, node_data>();
		n.getNi().forEach(x->{
		neighbors.put(x.getKey(),x);
		});
	}
	/**
	 * @return this key
	 */
	@Override
	public int getKey() {return  key;}
	/**
	 * @return All  the neighbors of this node
	 */
	@Override
	public Collection<node_data> getNi() {return neighbors.values();}
	/**
	 * @param unique key
	 * @return true iff this node contains the key .else false
	 */
	@Override
	public boolean hasNi(int key) {
		//if(this.getKey()==key)return true;
		return neighbors.containsKey(key);
		}
	/**
	 * Adds a new neighbor to this node
	 * @param node_data
	 * @return
	 */
	@Override
	public void addNi(node_data t) {neighbors.put(t.getKey(),t);}
	/**
	 * Deletes the adjacent node from the current  node
	 * @param node_data
	 */
	@Override
	public void removeNode(node_data node) {neighbors.remove(node.getKey());}
	/**
	 * @return this Info
	 */
	@Override
	public String getInfo() {return this.info;}
	/**
	 * Adds the information to the node
	 * @param String information
	 * @return
	 */
	@Override
	public void setInfo(String s) {this.info=s;}
	/**
	 * @return this Tag
	 */
	@Override
	public int getTag() {return this.tag;}
	/**
	 * Adds the Tag to the node
	 * @param Integer tag
	 * @return
	 */
	@Override
	public void setTag(int t) {this.tag=t;}
	/**
	 * Represents the node as a string
	 * @return
	 */
	public String toString() {
		return "{"+"key:"+key +",info:"+info+",tag:"+tag+",neighbors:"+neighbors.keySet()+"}";					
	}
}
