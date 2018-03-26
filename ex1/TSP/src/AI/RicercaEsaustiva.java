package AI;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import graph.Graph;
import graph.Node;
import testing.Options;

/**
 * Risolutore del TSP che implementa una ricerca esaustiva sul grafo. Se ne
 * sconsiglia l'uso su grafi troppo ampi.
 */
public class RicercaEsaustiva implements TSPSolver {

	public final static boolean LOG_ON=true;
	public final static boolean VERBOSE=true;
	
	private Graph grafo; // grafo da risolvere
	private int counter = 0; // contatore azioni
	private int soluzTrovate = 0; // contatore soluzioni

	private Stato minSoluzione;
	private int minCost = Integer.MAX_VALUE;
	private boolean used = false;
	private Node[] soluzioneArray;
	private StringBuilder log;
	public RicercaEsaustiva(Graph g) {
		grafo = g;
		if(LOG_ON) log=new StringBuilder("Exhaustive Research built.\n");
	}
	

	public Node[] process() {

		if (used)
			return soluzioneArray;
		
		ForkJoinPool fj=new ForkJoinPool(); //creazione thread paralleli 
		if(LOG_ON) log.append("Exhaustive Research starting.\n");
		
		LinkedList<TSPSolv> listaThread = new LinkedList<>();
		for (Node i : grafo.getNodes()) {
			TSPSolv ts=new TSPSolv(grafo, i);
			listaThread.add(ts);
			fj.execute(ts);
		}
		if(LOG_ON) log.append(listaThread.size()+" threads spawned and executing.\n");
		
		
		while(!listaThread.isEmpty())
		{
			TSPSolv ts= listaThread.pop();
			ts.join();
			
			if(VERBOSE && LOG_ON)
			{
				System.out.println(ts.log);
			}
			counter+=ts.counter;
			soluzTrovate+=ts.soluzTrovate;
			
			if(minCost > ts.minCost)
			{
				minSoluzione=ts.minSoluzione;
				minCost=ts.minCost;
			}
		}
		
		used = true;
		if (minSoluzione == null)
			return null;
		Node[] soluzione = new Node[grafo.getSize() + 1];

		Stato f = minSoluzione;
		for (int i = grafo.getSize(); i >= 0; i--) {
			soluzione[i] = f.getLastNode();
			f = f.getParent();
		}
		soluzioneArray=soluzione;
		return soluzione;

	}


	private class TSPSolv extends RecursiveTask<Integer> {
		
		static final long serialVersionUID = 1L;
		private final Graph g; // Grafo del problema
		private int counter = 0; // contatore azioni
		private int soluzTrovate = 0; // contatore soluzioni
		private Node start; // Nodo partenza del problema
		private StringBuilder log;

		private Stato minSoluzione;
		private int minCost = Integer.MAX_VALUE;

		public TSPSolv(Graph g, Node start) {
			this.g = g;
			this.start = start;
			if(LOG_ON) log=new StringBuilder("Search from vertex:\t"+start.getId()+"\n");
		}

		@Override
		protected Integer compute() {

			LinkedList<Stato> stack = new LinkedList<>();

			// popolo la stack con lo stato root del nodo assegnato
			stack.add(new Stato(start, null, true));

			final int N = g.getSize(); // numero vertici del grafo

			// ora inizio la dfs iterativa, come un buffer LIFO

			while (!stack.isEmpty()) {
				counter++;

				Stato s = stack.removeLast().expand();

				if (s.getDepth() == N) {
					Node l = s.getLastNode();
					if (l.getAdjacentNodes().contains(s.rootNode)) {
						soluzTrovate++;
						Stato sol = new Stato(s.getRootNode(), s, false).expand();
						
						if(LOG_ON) 
						{
							//Log delle soluzioni
							StringBuilder b=new StringBuilder();
							Stato f = sol;
							for (int i = N; i > 0; i--) {
								b.insert(0, ", "+f.getLastNode().getId());
								f = f.getParent();
							}
							b.insert(0, f.getLastNode().getId())
								.append(":\t").append(sol.getCost()).append("\n");
							log.append(b);
						}
						if (minCost > sol.getCost()) {// Se la soluzione è migliore di quella finora trovata
							minCost = sol.getCost();
							minSoluzione = sol;
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
			return minCost;
		}
	}

	/**
	 * Classe per indicare stato/soluzione
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
				costo = 0;
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

			if (!isRoot)
				costo = parent.getCost() + parent.getLastNode().getWeight(lastNode);
			return this;
		}

		public boolean isVisited(Node e) {
			if (visitati == null)
				expand();
			return visitati.contains(e);
		}

		public Stato getParent() {
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

	@Override
	public boolean printSolution(String file) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getIterationCounter() {
		return counter;
	}

	public int getSolutionCounter() {
		return soluzTrovate;
	}

	public int getSolutionCost() {
		return minCost;
	}

	public Node[] getSolution() {
		return soluzioneArray;
	}
	public String getLog()
	{
		if(log!=null) return log.toString();
		return "null";
	}
}
