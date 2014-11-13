package maze.solver;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import maze.grid.BoardMaker;
import maze.utilities.Block;
import maze.utilities.Constants;
import maze.utilities.Node;


public class MazeSolver {

	public static void main(String[] args) {
		BoardMaker bm = new BoardMaker();
		bm.GenGrid();

		List<String> strategies = new ArrayList<String>();
		strategies.add("g2");
		strategies.add("as2");

		compareAlgorithms(bm.getBoard(), strategies);

		// MiM(bm.getBoard(), "as1", true);

	}

	/**
	 * Compare algorithms
	 * 
	 * @param grid
	 * @param strategies
	 */
	public static void compareAlgorithms(Block[][] grid, List<String> strategies) {
		for (String type : strategies) {
			MiM(grid, type, false);
		}
	}

	/**
	 * 
	 * @param grid
	 *            generated from GenGrid method
	 * @param strategy contains the type of search that will be performed
	 * @param visualize
	 * @return a list of nodes that build the path to the goal if it exists
	 */
	public static List<Object> MiM(Block[][] grid, String strategy,
			boolean visualize) {
		List<Object> result = new ArrayList<Object>();
		List<Node> goalPath;
		Node root = new Node(null, grid, 0, 0);
		System.out.println("Board:");
		System.out.println(root);
		int type = getStrategyType(strategy);
		SearchProblem sp = new SearchProblem(type);

		long startTime = System.currentTimeMillis();
		sp.search(root, type);

		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);

		long finalTime = System.currentTimeMillis();
		long totalTime = finalTime - startTime;
		String timeElapsed = myFormat.format(totalTime);
		// ================
		// Search
		// ================
		goalPath = sp.search(root, type);
		if (goalPath == null) {
			System.err.println("No Solution Exists!");
			System.err.println("Time Elapsed: " + timeElapsed
					+ "\nVisited Nodes: " + sp.getNumberOfVisitedNodes());
			return null;
		}

		result.add(goalPath);
		result.add(timeElapsed);
		result.add(sp.getNumberOfVisitedNodes());
		if (visualize) {
			List<Node> visitedNodes = sp.getVisitedNodes();
			System.out.println("Visited Nodes: ");
			for (int i = 0; i < 20; i++) {
				System.out.println(visitedNodes.get(i).costHeuristicValue);
				System.out.println(visitedNodes.get(i));
			}
			
		}

		System.err.println("Goal Path:");
		for (int i = goalPath.size() - 1; i >= 0; i--) {
			// System.err.println(goalPath.get(i).costHeuristicValue);
			System.err.println(goalPath.get(i));
		}

		System.err.println("Time Elapsed: " + timeElapsed + "\nVisited Nodes: "
				+ sp.getNumberOfVisitedNodes() + "\nPathToGoal: "
				+ goalPath.size());

		return result;
	}

	/**
	 * @param strategy
	 * @return the integer representing the search algorithm which will be used
	 */
	public static int getStrategyType(String strategy) {
		switch (strategy.trim().toLowerCase()) {
		case "bf":
			return Constants.SEARCH_BREADTH_FIRST;
		case "df":
			return Constants.SEARCH_DEPTH_FIRST;
		case "id":
			return Constants.SEARCH_ITERATIVE_DEEPENING;
		case "gr1":
			setHeurestic(Constants.HEURISTIC_1);
			return Constants.SEARCH_GREEDY_SEARCH;
		case "gr2":
			setHeurestic(Constants.HEURISTIC_2);
			return Constants.SEARCH_GREEDY_SEARCH;
		case "as1":
			setHeurestic(Constants.HEURISTIC_1);
			return Constants.SEARCH_ASTAR_SEARCH;
		case "as2":
			setHeurestic(Constants.HEURISTIC_2);
			return Constants.SEARCH_ASTAR_SEARCH;
		default:
			return Constants.SEARCH_ASTAR_SEARCH;

		}
	}

	public static void setHeurestic(int heuristic) {
		Constants.HEURISTIC_CHOSEN = heuristic;
	}

}