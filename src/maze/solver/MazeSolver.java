package maze.solver;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import maze.grid.BoardMaker;
import maze.grid.searchTrees.AStarSearch;
import maze.grid.searchTrees.BreadthFirstSearch;
import maze.grid.searchTrees.DepthFirstSearch;
import maze.grid.searchTrees.GreedySearch;
import maze.grid.searchTrees.IterativeDeepening;
import maze.utilities.Block;
import maze.utilities.Node;

public class MazeSolver {
	public static final int BREADTH_FIRST = 0, DEPTH_FIRST = 1,
			ITERATIVE_DEEPENING = 2, GREEDY_SEARCH = 3, ASTAR_SEARCH = 4;

	private static int type;

	public static ArrayList<Object> MiM(Block[][] grid, String strategy,
			boolean visualize) {

		return null;
	}

	public static void main(String[] args) {

		BoardMaker bm = new BoardMaker();
		bm.GenGrid();
		Scanner scan = new Scanner(System.in);
		System.out
				.println("Please Select the search tree method you want to "
						+ "solve the problem with by writing one of the following"
						+ " numbers : \n 0 = Breadth First \n 1 = Depth First \n "
						+ "2 = Iterative Deepening \n 3 = Greedy Search \n 4 = A* Search \n");
		try {

			type = scan.nextInt();
			if (type > -1 && type < 5) {
				System.out.println(bm.printBoard());
				System.out.println("Number of blocks to be initiated = "
						+ bm.numberOfBlocks);
				Node root = new Node(null, bm.getBoard(), 0, 0);
				Object bf;
				List<Node> goalPath = new ArrayList<>();
				long startTime = System.currentTimeMillis();
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
								+ goalPath.get(i).costHeuristicValue);
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

				NumberFormat myFormat = NumberFormat.getInstance();
				myFormat.setGroupingUsed(true);

				long finalTime = System.currentTimeMillis();
				long totalTime = finalTime - startTime;

				System.out.println("Time taken to solve the maze = "
						+ myFormat.format(totalTime) + " Milliseconds \n");
			} else
				System.err
						.println("Your input is not listed in the search tree options");
		} catch (Exception e) {
			System.err.println("Undefined input");
		} finally {
			scan.close();
		}

	}

}
