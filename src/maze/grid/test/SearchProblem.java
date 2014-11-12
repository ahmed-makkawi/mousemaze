package maze.grid.test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import maze.grid.test.search.AStarSearch;
import maze.grid.test.search.BreadthFirstSearch;
import maze.grid.test.search.DepthFirstSearch;
import maze.grid.test.search.GreedySearch;
import maze.grid.test.search.IterativeDeepening;
import maze.utilities.Block;
import maze.utilities.Constants;
import maze.utilities.Node;

public class SearchProblem {
	private IQueueingFunction queue;
	private Set<Node> done = new HashSet<Node>(); // To limit tree size, To
													// avoid
													// looping
	private List<Node> visitedNodes;

	public SearchProblem(int type) {

		switch (type) {
		case Constants.SEARCH_BREADTH_FIRST:
			queue = new BreadthFirstSearch();
			break;
		case Constants.SEARCH_DEPTH_FIRST:
			queue = new DepthFirstSearch();
			break;
		case Constants.SEARCH_ITERATIVE_DEEPENING:
			queue = new IterativeDeepening();
			break;
		case Constants.SEARCH_GREEDY_SEARCH:
			queue = new GreedySearch();
			break;
		case Constants.SEARCH_ASTAR_SEARCH:
			queue = new AStarSearch();
			break;
		}
		visitedNodes = new ArrayList<Node>();

	}

	/**
	 * 
	 * @param root
	 *            initial state of the board to start the search problem
	 * @param searchStrategy
	 *            ID of search strategy, check Constants.java
	 * @return List<Nodes> to goal path OR null if no solution exists
	 */
	public List<Node> search(Node root, int searchStrategy) {
		queue.add(root);
		Node current;
		List<Node> currentNodeChildren;
		List<Node> goalPath = new ArrayList<>();

		while (!queue.isEmpty()) {
			try {
				current = queue.poll();
			} catch (EmptyStackException e) { // For IterativeDeepening only,
												// check isEmpty() in
												// IterativeDeepening.java
				queue.add(root); // increments level by 1 in
									// ItereativeDeepening.java
				done.clear();
				continue;
			}
			visitedNodes.add(current);
			if (checkIfGoal(current.getBoard())) {
				goalPath.add(current);
				return current.getAncestors(goalPath);
			}
			if (done.contains(current)) // avoid infinite loops
				continue;
			currentNodeChildren = queue.getChildNodes(current);
			queue.addAll(currentNodeChildren);
		}
		return null;
	}

	public boolean checkIfGoal(Block[][] board) {
		return (board[2][4].getType() == Constants.BLOCK_MOUSE && board[2][5]
				.getType() == Constants.BLOCK_MOUSE);
	}

	public List<Node> getVisitedNodes() {
		return visitedNodes;
	}

	public int getNumberOfVisitedNodes() {
		return visitedNodes.size();
	}
}
/* Algorithm:
 * nodes <- MAKE-Q(MAKE-NODE(INIT-STATE(problem)))
 * loop do If nodes is empty 
 * then return failure 
 * node <- REMOVE-FRONT(nodes) 
 * If GOAL-TEST(problem)(STATE(node)) 
 * then return node nodes <- QING-FUN(nodes,EXPAND(node, OPER(problem)))
 * end
 */