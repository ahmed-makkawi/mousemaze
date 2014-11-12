package maze.grid.searchTrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import maze.utilities.Block;
import maze.utilities.Constants;
import maze.utilities.Node;

public class GreedySearch {

	public PriorityQueue<Node> queue = new PriorityQueue<Node>();

	public Set<Node> done = new HashSet<Node>(); // To limit tree size,
													// To avoid looping

	public List<Node> search(Node root) {
		queue.add(root);
		Node current;
		List<Node> currentNodeChildren;
		ArrayList<Node> goalPath = new ArrayList<>();
		while ((current = queue.poll()) != null) {
			// System.out.println(current.printBoard());
			if (checkIfGoal(current.getBoard())) {
				goalPath.add(current);
				return current.getAncestors(goalPath);
			}
			if (done.contains(current)) // avoid infinite loops
				continue;
			currentNodeChildren = current.getChildNodesWithHeuristics();
			queue.addAll(currentNodeChildren);
			// System.out.println("Queue size: " + queue.size());
			done.add(current);
		}
		System.out.println("No solution exists!");
		return goalPath;
	}

	public boolean checkIfGoal(Block[][] board) {
		return (board[2][4].getType() == Constants.BLOCK_MOUSE && board[2][5]
				.getType() == Constants.BLOCK_MOUSE);
	}
}
