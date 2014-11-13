package maze.searchTrees;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

import maze.solver.IQueueingFunction;
import maze.utilities.Node;

/**
 * Implements Depth First Search Algorithm.
 * @author Omar
 *
 */
public class DepthFirstSearch implements IQueueingFunction {

	public Stack<Node> stack = new Stack<Node>();

	@Override
	public void add(Node x) {
		stack.add(x);
	}

	@Override
	public void addAll(Collection<? extends Node> c) {
		stack.addAll(c);
	}

	@Override
	public Node poll() {
		return stack.pop();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public List<Node> getChildNodes(Node parent) {
		return parent.getChildNodes();
	}
}
