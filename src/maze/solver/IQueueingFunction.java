package maze.solver;

import java.util.Collection;
import java.util.List;

import maze.utilities.Node;

/**
 * Generic queuing functions.
 *
 */
public interface IQueueingFunction {

	public void add(Node x);

	public void addAll(Collection<? extends Node> c);

	public Node poll();

	public boolean isEmpty();

	public List<Node> getChildNodes(Node parent);
	
}
