package maze.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import maze.utilities.Constants;
import maze.utilities.Node;

public class BreadthFirstSearch {

	public Queue<Node> queue = new LinkedList<Node>();
	public Set<Node> done = new HashSet<Node>(); // To limit tree size,
													// To avoid looping

	public List<Node> breadthFirstSearch(Node root) {
		queue.add(root);
		Node current;
		List<Node> currentNodeChildren;
		ArrayList<Node> goalPath = new ArrayList<>();
		while ((current = queue.poll()) != null) {
			// System.out.println(current.printBoard());
			System.out.println("Node.board");
			System.out.println(current.printBoard());
			if (checkIfGoal(current.getBoard())) {
				goalPath.add(current);
				return current.getAncestors(goalPath);
			}
			if (done.contains(current)) // avoid infinite loops
				continue;

			currentNodeChildren = current.getChildNodes();
			queue.addAll(currentNodeChildren);
			done.add(current);
		}
		return goalPath;
	}

	public boolean checkIfGoal(Block[][] board) {
		return (board[2][4].getType() == Constants.BLOCK_MOUSE && board[2][5]
				.getType() == Constants.BLOCK_MOUSE);
	}
}
