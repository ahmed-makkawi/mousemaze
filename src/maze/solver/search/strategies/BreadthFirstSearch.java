package maze.solver.search.strategies;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import maze.solver.search.IQueueingFunction;
import maze.solver.utilities.Node;

/**
 * Implements Breadth First Search Algorithm.
 * @author ahmed
 *
 */
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
		return queue.isEmpty();
	}

	@Override
	public List<Node> getChildNodes(Node parent) {
		return parent.getChildNodes();
	}
}
