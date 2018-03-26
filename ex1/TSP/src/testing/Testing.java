package testing;

import java.io.IOException;
import java.util.Arrays;

import AI.RicercaEsaustiva;
import graph.Graph;

public class Testing {

	
	public static void main(String[] args) throws IOException {
		test1();
	}
	
	private static void test1() throws IOException
	{
		Graph g=new Graph("input/grafo1");
<<<<<<< HEAD
		g.printGraph();
		DFS dfs=new DFS(g);
		System.out.println((Arrays.toString(dfs.process())));
=======
		RicercaEsaustiva dfs=new RicercaEsaustiva(g);
		dfs.process();
		System.out.printf("Costo Soluz.:\t%d\nIterazioni:\t%d\nSoluzioni:\t%d\n", dfs.getSolutionCost(),  dfs.getIterationCounter(), dfs.getSolutionCounter());
>>>>>>> branch 'master' of https://github.com/max-96/AI-experiments.git
		
		System.out.println((Arrays.toString(dfs.getSolution())));
		System.out.println(dfs.getLog());
		
	
	}
	
}
