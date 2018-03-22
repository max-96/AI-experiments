package testing;

import java.io.IOException;
import java.util.Arrays;

import AI.DFS;
import graph.Graph;

public class Testing {

	
	public static void main(String[] args) throws IOException {
		test1();
	}
	
	private static void test1() throws IOException
	{
		Graph g=new Graph("input/grafo1");
		DFS dfs=new DFS(g);
		
		System.out.printf("Costo Soluz.:\t%d\nIterazioni:\t%d\nSoluzioni:\t%d\n", dfs.getSolutionCost(),  dfs.getIterationCounter(), dfs.getSolutionCounter());
		System.out.println((Arrays.toString(dfs.process())));
	
	}
	
}
