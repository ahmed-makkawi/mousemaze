package maze.solver;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import maze.searchTrees.AStarSearch;
import maze.searchTrees.BreadthFirstSearch;
import maze.searchTrees.DepthFirstSearch;
import maze.searchTrees.GreedySearch;
import maze.searchTrees.IterativeDeepening;
import maze.utilities.Block;
import maze.utilities.Constants;
import maze.utilities.Node;

/**
 * Generic search class to choose the type of search preferred, search types are
 * stored in Constants class 
 * Algorithm: nodes <- MAKE-Q(MAKE-NODE(INIT-STATE(problem)))
 * loop do If nodes is empty then return failure 
 * node <- REMOVE-FRONT(nodes) If GOAL-TEST(problem)(STATE(node)) then
 * return node 
 * nodes <-QING-FUN(nodes,EXPAND(node, OPER(problem))) 
 * end.
 *
 */
public class SearchProblem {
    private IQueuingFunction queue;
    private Set<Node> finishedNodes = new HashSet<Node>(); // To limit tree
							   // size, To
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
		finishedNodes.clear();
		// System.err.println("Repeating iteration with more depth");
		continue;
	    }
	    visitedNodes.add(current);
	    if (checkIfGoal(current.getBoard())) {
		goalPath.add(current);
		return current.getAncestors(goalPath);
	    }
	    if (finishedNodes.contains(current)) // avoid infinite loops
		continue;
	    finishedNodes.add(current);
	    currentNodeChildren = queue.getChildNodes(current);
	    queue.addAll(currentNodeChildren);

	}
	return null;
    }

    /**
     * @param board
     * @return true of the mouse reached the goal
     */
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
/*
 * Algorithm: nodes <- MAKE-Q(MAKE-NODE(INIT-STATE(problem))) loop do If nodes
 * is empty then return failure node <- REMOVE-FRONT(nodes) If
 * GOAL-TEST(problem)(STATE(node)) then return node nodes <-
 * QING-FUN(nodes,EXPAND(node, OPER(problem))) end
 */