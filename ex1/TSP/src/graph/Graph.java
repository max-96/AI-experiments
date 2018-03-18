package graph;

import java.util.ArrayList;

public class Graph {

	private int size;
	private ArrayList<Node> vertex;
	private int[][] weights;
	
	
	
	
	public int getSize() {return size;}
	public int getWeight(Node from, Node to) { return weights[from.getId()][to.getId()];}
	public int getWeight(int from, int to) { return weights[from][to];}
	
	public Node[] getNodes() { return (Node[]) vertex.toArray();}
	
}
