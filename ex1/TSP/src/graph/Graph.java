package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {

	/**
	 * String that contains the name of the graph 
	 */
	private String name = null;
	private int size;
	private Node[] nodes;
	private int maxWeight=Integer.MIN_VALUE;
	
	
	public Graph(String filename) throws IOException
	{
		try(BufferedReader b=new BufferedReader(new FileReader(filename));){
			size=Integer.parseInt(b.readLine());
			nodes=new Node[size];
			
			for(int i=0;i<size;i++)
			{
				nodes[i]=null;
			}
			
			String l=b.readLine();
			int u,v,w;
			
			//finché il file ha righe
			while(l!=null)
			{
				String[] ls=l.split(",");

				u=Integer.parseInt(ls[0]);
				v=Integer.parseInt(ls[1]);
				w=Integer.parseInt(ls[2]);
				
				//controlli
				if(v>=size || v>=size) continue;
				if(w>maxWeight) maxWeight=w;
				
				//System.out.printf("%d %d %d\n", u, v, w);
				//creo i nodi se non esistono
				if(nodes[u]==null) nodes[u]=new Node(u);
				if(nodes[v]==null) nodes[v]=new Node(v);
				
				//inserisco l'arco
				nodes[u].addEdge(nodes[v], w);
				
				l=b.readLine();
			}
			
		}
		
	}
	
	/**
	 * 	
	 */
	public Graph(String filename,String name) throws IOException
	{
		this(filename);
		this.name = name;
		
	}
	
	/**
	 * create a graph of size "size" with no edges;
	 * @param size
	 */
	public Graph(int size)
	{
		//da implementare
	}
		
	public int getSize() {return size;}
	
	public Node[] getNodes() {return nodes.clone();}
	
	/**
	 * Function that Prints the Graph 
	 * Version 0.1
	 */
	public void printGraph()
	{
		System.out.println("Printing Graph of name: "+this.name);	
		for(int i=0;i<this.size;i++)
		{
			System.out.print("Node n° "+i+" with Id "+this.nodes[i].getId()+" is connected to nodes:");
			for(Node n : this.nodes[i].getAdjacentNodes())
				System.out.println(" "+n.getId()+" with cost "+ this.nodes[i].getWeight(n));
		}
		return;
	}
	
}
