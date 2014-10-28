package test;

import java.util.ArrayList;
import java.util.List;

import maze.grid.BoardMaker;
import maze.grid.BreadthFirstSearch;
import maze.grid.DepthFirstSearch;
import maze.grid.IterativeDeepening;
import maze.utilities.Node;

public class test {
	public static final int BREADTH_FIRST = 0, DEPTH_FIRST = 1,
			ITERATIVE_DEEPENING = 2;
	public static int type = ITERATIVE_DEEPENING;

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
		}
		for (int i = goalPath.size() - 1; i >= 0; i--)
			System.err.println(goalPath.get(i).printBoard());
	}
}
