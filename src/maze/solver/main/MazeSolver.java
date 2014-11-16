package maze.solver.main;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import maze.solver.board.BoardMaker;
import maze.solver.search.SearchProblem;
import maze.solver.utilities.Block;
import maze.solver.utilities.Constants;
import maze.solver.utilities.Node;

public class MazeSolver {

	/*
	 * Change to desired search strategy
	 */
	public static final String searchStrategy = "AS1";
	public static final boolean visualize = true;

	public static void main(String[] args) {
		// =======================================
		// Generate the board
		// =======================================

		BoardMaker bm = new BoardMaker();
		bm.GenGrid();

		// ======================================
		// Insert the algorithms to compare and
		// uncomment compareAlgorithms
		// ======================================

		List<String> strategies = new ArrayList<String>();
		strategies.add("bf");
		strategies.add("df");
		strategies.add("id");
		strategies.add("gr1");
		strategies.add("gr2");
		strategies.add("as1");
		strategies.add("as2");

		// =====================================
		// Uncomment to compare all algorithms in
		// arraylist strategies
		// =====================================

		// compareAlgorithms(bm.getBoard(), strategies);

		// =====================================
		// Perform search on generated board
		// =====================================

		MiM(bm.getBoard(), searchStrategy, visualize);

	}

	/**
	 * Compare algorithms
	 * 
	 * @param grid
	 *            to use in all strategies
	 * @param strategies
	 *            to compare
	 */
	public static void compareAlgorithms(Block[][] grid, List<String> strategies) {
		for (String type : strategies) {
			MiM(grid, type, false);
		}
	}

	/**
	 * Runs the search strategy on the given grid
	 * 
	 * @param grid
	 *            generated from GenGrid method
	 * @param strategy
	 *            contains the type of search that will be performed on grid
	 * @param visualize
	 *            prints the visited nodes and path to goal if one exists
	 * @return a list of nodes that build the path to the goal if one exists
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
		// ===========================
		// Search
		// ===========================
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

		System.err.println("Strategy: " + strategy.toUpperCase());

		if (visualize) {
			List<Node> visitedNodes = sp.getVisitedNodes();
			System.out.println("Visited Nodes: ");
			for (int i = 0; i < 20; i++) {
				// System.out.println(visitedNodes.get(i).costHeuristicValue);
				System.out.println(visitedNodes.get(i));
			}

			System.err.println("Goal Path:");
			for (int i = goalPath.size() - 1; i >= 0; i--) {
				// System.err.println(goalPath.get(i).costHeuristicValue);
				System.err.println(goalPath.get(i));
			}
		}

		System.err.println("Time Elapsed: " + timeElapsed + "\nVisited Nodes: "
				+ sp.getNumberOfVisitedNodes() + "\nDepth of Goal: "
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
