package test;

import java.util.ArrayList;
import java.util.List;

import maze.grid.AStarSearch;
import maze.grid.BoardMaker;
import maze.grid.BreadthFirstSearch;
import maze.grid.DepthFirstSearch;
import maze.grid.GreedySearch;
import maze.grid.IterativeDeepening;
import maze.utilities.Node;

public class test {
	public static final int BREADTH_FIRST = 0, DEPTH_FIRST = 1,
			ITERATIVE_DEEPENING = 2, GREEDY_SEARCH = 3, ASTAR_SEARCH = 4;
	public static int type = GREEDY_SEARCH;

	public static void main(String[] args) {

		BoardMaker bm = new BoardMaker();
		bm.GenGrid();
		System.out.println(bm.printBoard());

		System.out.println("mNumber of blocks to be initiated= "
				+ bm.numberOfBlocks);
		Node root = new Node(null, bm.getBoard(), 0, 0);
		Object bf;
		List<Node> goalPath = new ArrayList<>();
		switch (type) {
		case BREADTH_FIRST:
			bf = new BreadthFirstSearch();
			goalPath = ((BreadthFirstSearch) bf).search(root);
			System.err.println("Size" + goalPath.size());
			break;
		case DEPTH_FIRST:
			bf = new DepthFirstSearch();
			goalPath = ((DepthFirstSearch) bf).search(root);
			System.err.println("Size" + goalPath.size());
			break;
		case ITERATIVE_DEEPENING:
			bf = new IterativeDeepening();
			goalPath = ((IterativeDeepening) bf).search(root);
			System.err.println("Size" + goalPath.size());
			break;
		case GREEDY_SEARCH:
			bf = new GreedySearch();
			goalPath = ((GreedySearch) bf).search(root);
			System.err.println("Size" + goalPath.size());
			for (int i = goalPath.size() - 1; i >= 0; i--)
				System.err.println("Heuristic= "
						+ goalPath.get(i).heuristicValue);
			break;
		case ASTAR_SEARCH:
			bf = new AStarSearch();
			goalPath = ((AStarSearch) bf).search(root);
			System.err.println("Size" + goalPath.size());
			for (int i = goalPath.size() - 1; i >= 0; i--)
				System.err.println("Cost+Heuristic= "
						+ goalPath.get(i).costHeuristicValue);
			break;
		}
		for (int i = goalPath.size() - 1; i >= 0; i--)
			System.err.println(goalPath.get(i).printBoard());
	}
}
