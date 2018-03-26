package graph;


/**
 *  Class that generates Graph of the type specified (chosen from TypeOfGraph)
 */
public class GraphGenerator {

	public GraphGenerator(TypeOfGraph type,int size)
	{
		Graph g = new Graph(size);
		switch(type)
		{
			case COMPLETE_GRAPH:
				completeGraph(g);
				break;
			
			case HAMILTONIAN_GRAPH:
				hamiltonianGraph(g);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Method to build a complete graph
	 * @param g
	 */
	private void completeGraph(Graph g)
	{
		//Da implementare
		
	}
	/**
	 * Method to build a Hamiltonian Graph
	 * @param g
	 */
	private void hamiltonianGraph(Graph g)
	{
		//da implementare
	}
}
