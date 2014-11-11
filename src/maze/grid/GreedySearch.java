package maze.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import maze.utilities.Constants;
import maze.utilities.Node;

public class GreedySearch {

	public Queue<Node> queue0 = new LinkedList<Node>();
	public Queue<Node> queue1 = new LinkedList<Node>();

	public Set<Node> done = new HashSet<Node>(); // To limit tree size,
													// To avoid looping

	public List<Node> search(Node root) {
		queue0.add(root);
		Node current;
		List<List<Node>> currentNodeChildren;
		ArrayList<Node> goalPath = new ArrayList<>();
		while (true) {
			if ((current = queue0.poll()) == null)
				if ((current = queue1.poll()) == null)
					break;
			// System.out.println(current.printBoard());
			if (checkIfGoal(current.getBoard())) {
				goalPath.add(current);
				return current.getAncestors(goalPath);
			}
			if (done.contains(current)) // avoid infinite loops
				continue;
			currentNodeChildren = current.getChildNodesWithHeuristics();
			queue0.addAll(currentNodeChildren.get(0));
			queue1.addAll(currentNodeChildren.get(1));

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
