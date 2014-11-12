package maze.grid.test.search;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

import maze.grid.test.IQueueingFunction;
import maze.utilities.Node;

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
		// TODO Auto-generated method stub
		return stack.pop();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return stack.isEmpty();
	}

	@Override
	public List<Node> getChildNodes(Node parent) {
		// TODO Auto-generated method stub
		return parent.getChildNodes();
	}
}
