package graph;


import java.util.HashMap;
import java.util.Set;

public class Node {
	
	private int id;
	private HashMap<Node, Integer> archi;
	/**
	 * 
	 * @param id
	 */
	public Node(int id)
	{
		this.id=id;
		archi=new HashMap<>();
	}
	
	
	/**
	 * Aggiunge un arco al nodo.
	 * 
	 * @param u nodo a cui è collegato
	 * @param weight peso dell'arco
	 */
	public void addEdge(Node u, int weight)
	{
		archi.put(u, weight);
	}
	
	/**
	 * Restituisce l'insieme degli adiacenti
	 * @return
	 */
	public Set<Node> getAdjacentNodes()
	{
		return archi.keySet();
	}
	
	/**
	 * restituisce il peso dell'arco fra questo nodo e il nodo x. Restituisce -1 se non c'è alcun arco.
	 * @param x
	 * @return il peso dell'arco, o -1 se non c'è alcun arco.
	 */
	public int getWeight(Node x)
	{
		return archi.get(x).intValue();
	}
	
	public int getId() { return id;}
}
