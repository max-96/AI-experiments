package AI;

import java.util.HashSet;
import java.util.LinkedList;

import graph.Graph;
import graph.Node;
import graph.Path;
import testing.Options;

public class DFS implements TSPSolver {

	private Graph grafo; //grafo da risolvere
	
	
	private int counter = 0; //contatore azioni
	private int soluzTrovate=0; //contatore soluzioni

	private int[] minCammino;
	private int minCost=Integer.MAX_VALUE;
	private boolean used=false;

	private int[] cammino;
	private int[] tempCosti;

	public DFS(Graph g) {
		grafo = g;
		cammino = new int[g.getSize() + 1];
		tempCosti = new int[g.getSize() + 1];
	}

	
	public Node[] process()
	{
		//stack dove salvo gli stati
		LinkedList<Stato> stack;
		
		//popolo la stack con gli stati root di tutti i nodi
		for(Node n: grafo.getNodes())
			stack.add(new Stato(n, null, true));
		
		//ora inizio la dfs iterativa, come un buffer LIFO
		
		while(!stack.isEmpty())
		{
			if(Options.COUNTER_ON)
				counter++;
			
			Stato s=stack.removeLast();
			
			
			if(s.getDepth() == grafo.getSize())
			{
				//Mossa obbligata, deve tornare allo stato iniziale
				
			}
			else
			{
				Node e=s.getLastNode();
				for(Node i: e.getAdjacentNodes())
				{
					if(!s.isVisited(i))
					{
						Stato t=new Stato(i, s, false);
						t.getCost();
						stack.add(t);
						
					}
				}
				
			}
			
			
			
			
		}
			
		
		
		
		
	}
	
	/**
	 * Classe per indicare stato
	 * @author Max
	 *
	 */
	private class Stato{
		
		private Node lastNode;
		private Node rootNode;
		private int depth; //profondità
		private Stato parent;
		private boolean isRoot=false;
		private HashSet<Node> visitati;
		
		
		private int costo=-1;
		/**
		 * 
		 * @param k numero di nodi attraversati
		 * @param cammino array dei nodi attraversati
		 * @param p stato parent se esiste
		 */
		public Stato(Node last, Stato p, boolean isRoot)
		{
			lastNode=last;
			if(isRoot)
			{
				this.isRoot=true;
				rootNode=last;
				visitati=new HashSet<>();
				parent=null;
				depth=1;
			}
			else
			{
				parent=p;
				rootNode=p.rootNode;
				visitati=new HashSet<>(p.visitati);
				depth=p.depth+1;
			}
			visitati.add(last);
		}
		
		
		
		public boolean isVisited(Node e)
		{
			return visitati.contains(e);
		}
		public Node getLastNode() {
			return lastNode;
		}
		
		/**
		 * Restituisce il costo del cammino fino a lastNode
		 * @return
		 */
		public int getCost()
		{
			if(costo>=0) return costo;
			
			costo= parent.getCost() + parent.getLastNode().getWeight(lastNode);
			return costo;
		}
		
		public int getDepth()
		{
			return depth;
		}
		
	}
	
	
	public int[] process1() {
		if(used) return null;
		used=true;
		
		for (Node n : grafo.getNodes()) {
			if(Options.COUNTER_ON) counter++;
			cammino[0] = n.getId();
			tempCosti[0] = 0;
			dfs(1, n);
		}

		//return new Path(minCammino);
		return minCammino;
	}

	/**
	 * k è il numero di vertici già inseriti nel cammino
	 * @param k il numero di vertici già inseriti nel cammino
	 * @param u	il vertice padre
	 */
	private void dfs(int k, Node u) {
		if(Options.COUNTER_ON) counter++;
		if (k <= grafo.getSize()) {
			for (Node v : u.getAdjacentNodes()) {
				
				boolean f=false;
				
				for(int i=0;i<k;i++)
					if(v.getId()==cammino[i]) {f=true; break;}

				if(f) continue;
				
				cammino[k]=u.getId();
				tempCosti[k]=tempCosti[k-1] + u.getWeight(v);
				dfs(k + 1, v);
			}
		}
		else {
			for(Node v: u.getAdjacentNodes())
			{
				if(v.getId()==cammino[0])
				{
					soluzTrovate++;
					
					tempCosti[k]= tempCosti[k-1] + u.getWeight(v);
					cammino[k]=cammino[0];
					if(minCost>tempCosti[k]) {
						for(int i=0;i<grafo.getSize()+1;i++)
							minCammino[i]=cammino[i];
						
						minCost=tempCosti[k];
						
					}
					
					
					break;
				}
			}
					
		}
		
	}

}
