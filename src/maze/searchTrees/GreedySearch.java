package maze.searchTrees;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

import maze.solver.IQueuingFunction;
import maze.utilities.Node;

/**
 * Implements the Greedy Search Algorithm.
 * 
 * @author Gelly
 *
 */
public class GreedySearch implements IQueuingFunction {

	public PriorityQueue<Node> queue = new PriorityQueue<Node>();

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
		return parent.getChildNodesWithHeuristics();
	}
}
