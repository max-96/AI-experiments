package AI;


import java.util.List;

import graph.Graph;
import graph.Node;

public class SimAnnealing implements TSPSolver{

	
	private Graph grafo; // grafo da risolvere
	private int maxTemp;

	private int counter = 0; // contatore azioni
	private int soluzTrovate = 0; // contatore soluzioni

	private Soluzione soluzione;
	private int minCost = Integer.MAX_VALUE;
	private boolean used = false;



	/**
	 * 
	 * @param g Grafo su cui risolvere il TSP
	 * @param maxTemp temperatura massima permessa
	 */
	public SimAnnealing(Graph g, int maxTemp) {
		grafo = g;
		this.maxTemp=maxTemp;
		
	}
	
	
	
	
	
	
	@Override
	public Node[] process() {
		
		for(int k=0; k<maxTemp; k++)
		{
			float temp= ((float) k)/maxTemp;
			
			
			
			
			
			
		}
		return null;
		
		
		
		
	}

	@Override
	public int getIterationCounter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSolutionCounter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSolutionCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean printSolution(String file) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private class Soluzione
	{
		private Node[] sol;
		private int costo;
		
		public Soluzione(Node[] sol)
		{
			
		}
		
		public Soluzione getNeighbor(int seed)
		{
			
		}
	}
	
	
}
