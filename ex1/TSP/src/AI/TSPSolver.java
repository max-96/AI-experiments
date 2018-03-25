package AI;

import graph.Node;

public interface TSPSolver {

	public Node[] process();
	public int getIterationCounter();
	public int getSolutionCounter();
	public int getSolutionCost();
	public Node[] getSolution();
	public boolean printSolution(String file);
}
