package maze.grid.test.search;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import maze.grid.test.IQueueingFunction;
import maze.utilities.Node;

public class BreadthFirstSearch implements IQueueingFunction {

	public Queue<Node> queue = new LinkedList<Node>();

	@Override
	public void add(Node x) {
		queue.add(x);
	}

	@Override
	public void addAll(Collection<? extends Node> c) {
		queue.addAll(c);
	}

	@Override
	public Node poll() {
		return queue.poll();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	@Override
	public List<Node> getChildNodes(Node parent) {
		// TODO Auto-generated method stub
		return parent.getChildNodes();
	}
}