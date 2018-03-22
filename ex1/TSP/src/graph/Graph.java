package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {

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
				
				System.out.printf("%d %d %d\n", u, v, w);
				//creo i nodi se non esistono
				if(nodes[u]==null) nodes[u]=new Node(u);
				if(nodes[v]==null) nodes[v]=new Node(v);
				
				//inserisco l'arco
				nodes[u].addEdge(nodes[v], w);
				
				l=b.readLine();
			}
			
		}
		
		
		
		
	}
	
	public int getSize() {return size;}
	
	public Node[] getNodes() {return nodes.clone();}
	
}
