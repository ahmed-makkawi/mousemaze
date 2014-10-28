package maze.grid;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import maze.utilities.Constants;
import maze.utilities.Node;

public class IterativeDeepening {

	public Stack<Node> stack = new Stack<Node>();
	public Set<Node> done = new HashSet<Node>(); // To limit tree size,
													// To avoid looping

	public List<Node> search(Node root) {
		Node current;
		List<Node> currentNodeChildren;
		ArrayList<Node> goalPath = new ArrayList<>();
		int iterLevel = 0;
		int maxLevel = 100;
		while (true) {
			stack.removeAllElements();
			stack.add(root);
			done.clear();
			if (iterLevel >= maxLevel) {
				System.out.println("No solution");
				return null;
			}

			try {
				while ((current = stack.pop()) != null) {
					if (current.depthCost > iterLevel)
						continue;
					// System.out.println(current.printBoard());
					if (checkIfGoal(current.getBoard())) {
						goalPath.add(current);
						return current.getAncestors(goalPath);
					}
					if (done.contains(current)) { // avoid infinite loops
						System.out.println("Nodes duplicate"
								+ current.toString());
						continue;
					}
					currentNodeChildren = current.getChildNodes();
					stack.addAll(currentNodeChildren);
					System.out.println("stack size: " + stack.size());
					System.out.println(current);

					done.add(current);
				}
			} catch (EmptyStackException e) {
				System.out.println("Continue iterating");
				// return goalPath;
			}
			System.out.println("inside big loop " + iterLevel);
			iterLevel++;
		}
	}

	public boolean checkIfGoal(Block[][] board) {
		return (board[2][4].getType() == Constants.BLOCK_MOUSE && board[2][5]
				.getType() == Constants.BLOCK_MOUSE);
	}
}
