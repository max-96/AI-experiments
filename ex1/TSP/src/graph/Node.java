package graph;

import java.util.ArrayList;

public class Node {
	
	private int id;
	private ArrayList<Node> adj=new ArrayList<>();
	
	public Node[] getAdjacentNodes()
	{
		return (Node[]) adj.toArray();
	}
	
	public int getId() { return id;}
}
