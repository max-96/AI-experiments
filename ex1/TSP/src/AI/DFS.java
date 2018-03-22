package AI;

import java.util.HashSet;
import java.util.LinkedList;

import graph.Graph;
import graph.Node;
import testing.Options;

public class DFS implements TSPSolver {

	private Graph grafo; // grafo da risolvere

	private int counter = 0; // contatore azioni
	private int soluzTrovate = 0; // contatore soluzioni

	private Stato minSoluzione;
	private int minCost = Integer.MAX_VALUE;
	private boolean used = false;



	public DFS(Graph g) {
		grafo = g;
	}

	public Node[] process() {
		
		if(used) return null;
		
		// stack dove salvo gli stati
		LinkedList<Stato> stack = new LinkedList<>();

		// popolo la stack con gli stati root di tutti i nodi
		for (Node n : grafo.getNodes())
			stack.add(new Stato(n, null, true));

		int N = grafo.getSize(); // numero vertici del grafo

		// ora inizio la dfs iterativa, come un buffer LIFO

		while (!stack.isEmpty()) {
			if (Options.ITER_COUNTER_ON)
				counter++;

			Stato s = stack.removeLast().expand();

			if (s.getDepth() == N) {
				Node l = s.getLastNode();
				if (l.getAdjacentNodes().contains(s.rootNode)) {
					
					if (Options.SOLUTION_COUNTER_ON)
						soluzTrovate++;
					
					Stato sol=new Stato(s.getRootNode(), s, false).expand();
					if(minCost > sol.getCost())
					{//Se la soluzione è migliore di quella finora trovata
						minCost=sol.getCost();
						minSoluzione=sol;
						
					}
					
				}
			} else {
				Node e = s.getLastNode();
				for (Node i : e.getAdjacentNodes()) {
					if (!s.isVisited(i)) 
						stack.add(new Stato(i, s, false));

					
				}

			}

		}
		
		used=true;
		if(minSoluzione==null) return null;
		Node[] soluzione=new Node[N+1];
		
		Stato f=minSoluzione;
		for(int i=N;i>=0;i--)
		{
			 soluzione[i]=f.getLastNode();
			 f=f.getParent();
		}
		return soluzione;

	}

	/**
	 * Classe per indicare stato
	 * 
	 * @author Max
	 *
	 */
	private class Stato {

		private Node lastNode;
		private Node rootNode;
		private int depth; // profondità
		private Stato parent;
		private boolean isRoot = false;
		private HashSet<Node> visitati;

		private int costo = -1;

		/**
		 * 
		 * @param k
		 *            numero di nodi attraversati
		 * @param cammino
		 *            array dei nodi attraversati
		 * @param p
		 *            stato parent se esiste
		 */
		public Stato(Node last, Stato p, boolean isRoot) {
			lastNode = last;
			if (isRoot) {
				this.isRoot = true;
				rootNode = last;
				visitati = new HashSet<>();
				parent = null;
				depth = 1;
				costo=0;
			} else {
				parent = p;
				rootNode = p.rootNode;
				depth = p.depth + 1;
			}
		}

		public Stato expand() {
			if (isRoot)
				visitati = new HashSet<>();
			else
				visitati = new HashSet<>(parent.visitati);
			visitati.add(lastNode);

			if(!isRoot) costo = parent.getCost() + parent.getLastNode().getWeight(lastNode);
			return this;
		}

		public boolean isVisited(Node e) {
			if (visitati == null)
				expand();
			return visitati.contains(e);
		}

		public Stato getParent()
		{
			return parent;
		}
		public Node getLastNode() {
			return lastNode;
		}
		public Node getRootNode() {
			return rootNode;
		}
		public int getCost() {
			if (costo >= 0)
				return costo;
			costo = parent.getCost() + parent.lastNode.getWeight(lastNode);
			return costo;
		}
		public int getDepth() {
			return depth;
		}
		
	}

	public int getIterationCounter()
	{
		return counter;
	}
	public int getSolutionCounter()
	{
		return soluzTrovate;
	}
	public int getSolutionCost()
	{
		return minCost;
	}



}
