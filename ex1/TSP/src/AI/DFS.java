package AI;

import graph.Graph;
import graph.Node;
import graph.Path;
import testing.Options;

public class DFS implements TSPSolver {

	private Graph grafo;
	private int counter = 0;
	private int soluzTrovate=0;

	private int[] minCammino;
	private int minCost=Integer.MAX_VALUE;

	private int[] cammino;
	private int[] tempCosti;

	public DFS(Graph g) {
		grafo = g;
		cammino = new int[g.getSize() + 1];
		tempCosti = new int[g.getSize() + 1];
	}

	public int[] process() {
		
		
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
