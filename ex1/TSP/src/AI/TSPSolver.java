package AI;

import graph.Node;

public interface TSPSolver {

	public Node[] process();
	public int getIterationCounter();
	public int getSolutionCounter();
	public int getSolutionCost();
}
