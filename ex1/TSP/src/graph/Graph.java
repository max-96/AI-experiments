package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {

	private int size;
	private ArrayList<Node> vertex;
	private Node[] nodes;
	private int[][] weights;
	
	
	public Graph(String filename) throws IOException
	{
		try(BufferedReader b=new BufferedReader(new FileReader(filename));){
			size=Integer.parseInt(b.readLine());
			weights=new int[size][size];
			nodes=new Node[size];
			
			for(int i=0;i<size;i++)
			{
				nodes[i]=null;
				for(int j=0;j<size;j++)
					weights[i][j]=0;
			}
			
			String l=b.readLine();
			while(l!=null)
			{
				String[] ls=l.split(",");
				
				if()
				
			}
			
		}
		
		
		
		
	}
	
	public int getSize() {return size;}
	public int getWeight(Node from, Node to) { return weights[from.getId()][to.getId()];}
	public int getWeight(int from, int to) { return weights[from][to];}
	
	public Node[] getNodes() { return (Node[]) vertex.toArray();}
	
}
